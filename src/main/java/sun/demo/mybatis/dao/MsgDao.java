package sun.demo.mybatis.dao;

import com.google.common.base.Strings;
import org.apache.ibatis.session.SqlSession;
import sun.demo.mybatis.bean.Message;
import sun.demo.mybatis.db.DbAccess;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MsgDao {
    public List<Message> qtryueryMessageListByJDBC(String command,String description) {
        ArrayList<Message> list = new ArrayList<Message>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis_demo?characterEncoding=utf8",
                    "root", "123456");
            StringBuilder sql = new StringBuilder("select ID, COMMAND, DESCRIPTION,CONTENT FROM message where 1=1");

            List<String> paramList = new ArrayList<String>();

            if (!Strings.isNullOrEmpty(command)) {
                sql.append(" and COMMAND=?");
                paramList.add(command);
            }
            if (!Strings.isNullOrEmpty(description)) {
                sql.append(" and DESCRIPTION like '%' ? '%'");
                paramList.add(description);
            }
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i + 1, paramList.get(i));
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Message message = new Message();
                list.add(message);
                message.setId(resultSet.getString("ID"));
                message.setCommand(resultSet.getString("COMMAND"));
                message.setContent(resultSet.getString("CONTENT"));
                message.setDescription(resultSet.getString("DESCRIPTION"));
            }
            System.out.println("msglist.size=" + list.size());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Message> queryMessageList(String command, String description) {
        DbAccess dbAccess = new DbAccess();
        SqlSession sqlSession = null;
        List<Message> list = new ArrayList<Message>();
        try {
            sqlSession = dbAccess.getSqlSession();
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            list = sqlSession.selectList("Message.queryMessageList",message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
        return list;
    }

    public void deleteOne(int id){
        DbAccess dbAccess = new DbAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteOne",id);
            sqlSession.commit();//及时提交
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null)
                sqlSession.close();
        }

    }

    public void deleteBatch(List<Integer> idList){
        DbAccess dbAccess = new DbAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteBatch",idList);
            sqlSession.commit();//及时提交
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
    }

    public static void main(String[] args) {
        MsgDao msgDao = new MsgDao();
        List<Message> list = msgDao.queryMessageList("查看", "");
        System.out.println(list.size());
    }
}
