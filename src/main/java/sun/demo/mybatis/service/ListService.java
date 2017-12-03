package sun.demo.mybatis.service;

import sun.demo.mybatis.bean.Message;
import sun.demo.mybatis.dao.MsgDao;
import sun.demo.mybatis.entity.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListService {
    public List<Message> queryMessageList(String command, String description, Page page) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        MsgDao msgDao = new MsgDao();
        // 根据条件查询条数
        int totalNumber = msgDao.count(message);
        // 组织分页查询参数
        page.setTotalNumber(totalNumber);
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("message", message);
        parameter.put("page", page);
        // 分页查询并返回结果
        return msgDao.queryMessageList(parameter);
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(String command,String description,Page page) {
        Map<String,Object> parameter = new HashMap<String, Object>();
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        parameter.put("message", message);
        parameter.put("page", page);
        MsgDao messageDao = new MsgDao();
        // 分页查询并返回结果
        return messageDao.queryMessageListByPage(parameter);
    }
}
