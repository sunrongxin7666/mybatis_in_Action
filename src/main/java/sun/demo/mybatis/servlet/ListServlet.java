package sun.demo.mybatis.servlet;

import sun.demo.mybatis.bean.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

/**
 *
 */
public class ListServlet extends HttpServlet {
    private String BACK_JSP = "/WEB-INF/jsp/back/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        try {
            String command = req.getParameter("command");
            String description = req.getParameter("description");
            req.setAttribute("command",command);
            req.setAttribute("description",description);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mybatis_demo?characterEncoding=utf8",
                    "root", "123456");
            StringBuilder sql = new StringBuilder("select ID, COMMAND, DESCRIPTION,CONTENT FROM message where 1=1");

            List<String> paramList = new ArrayList<String>();

            if(!Strings.isNullOrEmpty(command)){
                sql.append(" and COMMAND=?");
                paramList.add(command);
            }
            if(!Strings.isNullOrEmpty(description)){
                sql.append(" and DESCRIPTION like '%' ? '%'");
                paramList.add(description);
            }
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i+1,paramList.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Message> list = new ArrayList<Message>();
            while(resultSet.next()){
                Message message = new Message();
                list.add(message);
                message.setId(resultSet.getString("ID"));
                message.setCommand(resultSet.getString("COMMAND"));
                message.setContent(resultSet.getString("CONTENT"));
                message.setDescription(resultSet.getString("DESCRIPTION"));
            }
            System.out.println("msglist.size="+list.size());
            req.setAttribute("msgList",list);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher(BACK_JSP + "list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
