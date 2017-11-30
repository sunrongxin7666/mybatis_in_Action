package sun.demo.mybatis.dao;

import sun.demo.mybatis.bean.Message;

import java.util.List;

/**
 * 接口的报名就是配置文件中命名空间
 */
public interface IMessage {
    /**
     * 方法名就是配置文件中的ID，输入输出的类型需要完善；
     * @param msg
     * @return
     */
    public List<Message> queryMessageList(Message msg);
}
