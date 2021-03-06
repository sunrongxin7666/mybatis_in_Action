package sun.demo.mybatis.servlet;

import sun.demo.mybatis.bean.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import sun.demo.mybatis.entity.Page;
import sun.demo.mybatis.service.ListService;


public class ListServlet extends HttpServlet {
    private String BACK_JSP = "/WEB-INF/jsp/back/";

    /**
     * 查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //设置编码 支持中文
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //获取页面传值参数
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        String currentPage = req.getParameter("currentPage");
        //创建分页
        Page page = new Page();
        Pattern pattern = Pattern.compile("[0-9]{1,9}");
        if(currentPage == null||!pattern.matcher(currentPage).matches()){//
            page.setCurrentPage(1);
        } else {
            page.setCurrentPage(Integer.valueOf(currentPage));
        }

        //记录传值，让页面有显示
        req.setAttribute("command",command);
        req.setAttribute("description",description);
        List<Message> list = new ListService().queryMessageList(command,description,page);
        req.setAttribute("msgList",list);
        req.setAttribute("page",page);

        //跳转回页面
        req.getRequestDispatcher(BACK_JSP + "list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
