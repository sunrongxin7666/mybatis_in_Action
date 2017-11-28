package sun.demo.mybatis.service;

import sun.demo.mybatis.bean.Message;
import sun.demo.mybatis.dao.MsgDao;

import java.util.List;

public class ListService {
    public List<Message> qtryueryMessageList(String command, String description) {
        MsgDao msgDao = new MsgDao();
        return msgDao.queryMessageList(command,description);
    }
}
