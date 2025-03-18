package sparkweb.web;

import sparkweb.domain.User;
import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        /*乱码问题的解决*/
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html; charset=utf8");

        // 获取用户提交的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "," + password);

        // 服务器检验用户的输入
        // 访问数据库==》查询username和password对应的用户
        IUserService userService = new UserServiceImpl();
        // 接口引用 = new 实现类的对象
        User user = userService.login(username, password);
        if (user == null) {
            try {
                response.getWriter().write("failure"); // 登录失败，返回失败响应给前端
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 根据用户的身份进行对应的跳转
            if (user.getRole() == 1) {
                System.out.println("当前登录的管理员");
            } else {
                System.out.println("当前登录的读者");
            }
            try {
                response.sendRedirect("/test/index.html"); // 登录成功，返回成功响应给前端
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
