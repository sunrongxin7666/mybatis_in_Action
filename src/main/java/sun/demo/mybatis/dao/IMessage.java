package sun.demo.mybatis.dao;

import sun.demo.mybatis.bean.Message;

import java.util.List;
import java.util.Map;

/**
 * 接口的报名就是配置文件中命名空间
 */
public interface IMessage {
    /**
     * 方法名就是配置文件中的ID，输入输出的类型需要完善；
     * @param
     * @return
     */
    public List<Message> queryMessageList(Map map);

    public List<Message> queryMessageListByMsg(Message message);

    /**
     * 根据查询条件查询消息列表的条数
     */
    public int count(Message message);

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(Map<String,Object> parameter);
}
