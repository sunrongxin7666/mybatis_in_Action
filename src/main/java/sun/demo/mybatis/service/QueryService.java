package sun.demo.mybatis.service;


import sun.demo.mybatis.bean.Command;
import sun.demo.mybatis.bean.CommandContent;
import sun.demo.mybatis.bean.Message;
import sun.demo.mybatis.dao.CommandDao;
import sun.demo.mybatis.dao.MsgDao;
import sun.demo.mybatis.util.Iconst;

import java.util.List;
import java.util.Random;

/**
 * 查询相关的业务功能
 */
public class QueryService {
	public List<Message> queryMessageList(String command, String description) {
		MsgDao messageDao = new MsgDao();
		return messageDao.queryMessageList(command, description);
	}
	
	/**
	 * 通过指令查询自动回复的内容
	 * @param command 指令
	 * @return 自动回复的内容
	 */
	public String queryByCommand_old(String command) {
		MsgDao messageDao = new MsgDao();
		List<Message> messageList;
		if(Iconst.HELP_COMMAND.equals(command)) {
			messageList = messageDao.queryMessageList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i = 0; i < messageList.size(); i++) {
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + messageList.get(i).getCommand() + "]可以查看" + messageList.get(i).getDescription());
			}
			return result.toString();
		}
		messageList = messageDao.queryMessageList(command, null);
		if(messageList.size() > 0) {
			return messageList.get(0).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}

	public String queryByCommand(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		if(Iconst.HELP_COMMAND.equals(command)) {
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i = 0; i < commandList.size(); i++) {
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		commandList = commandDao.queryCommandList(command, null);
		if(commandList.size() > 0) {
			List<CommandContent> contentList = commandList.get(0).getContentList();
			int i = new Random().nextInt(contentList.size());
			return contentList.get(i).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
}
