package sun.demo.mybatis.servlet;

import sun.demo.mybatis.service.ManageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOneServlet extends HttpServlet {
    private String BACK_JSP = "/WEB-INF/jsp/back/";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //获取页面传值参数
        String id = req.getParameter("id");
        new ManageService().deleteOne(id);

        //跳转回页面
        req.getRequestDispatcher("list.action").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
