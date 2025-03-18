package sparkweb.web;

import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import sparkweb.tables.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/*
@WebServlet(name = "TablesListServlet", value = "/tableslist")
public class TablesListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建服务实例
        IUserService bncService = new UserServiceImpl();
        IUserService bpcService = new UserServiceImpl();
        IUserService nppcService = new UserServiceImpl();
        IUserService nrpcService = new UserServiceImpl();
        IUserService nrcService = new UserServiceImpl();
        IUserService pncService = new UserServiceImpl();
        IUserService prcService = new UserServiceImpl();

        // 调用服务，查询所有图书
        List<BncData> bnclist = bncService.bncqueryAll();
        List<BpcData> bpclist = bpcService.bpcqueryAll();
        List<NppcData> nppclist = nppcService.nppcqueryAll();
        List<NrpcData> nrpclist = nrpcService.nrpcqueryAll();
        List<NrcData> nrclist = nrcService.nrcqueryAll();
        List<PncData> pnclist = pncService.pncqueryAll();
        List<PrcData> prclist = prcService.prcqueryAll();

        // 将查询结果添加到request域对象中
        request.setAttribute("bnclist", bnclist);
        request.setAttribute("bpclist", bpclist);
        request.setAttribute("nppclist", nppclist);
        request.setAttribute("nrpclist", nrpclist);
        request.setAttribute("nrclist", nrclist);
        request.setAttribute("pnclist", pnclist);
        request.setAttribute("prclist", prclist);

        // 转发JSP页面
        request.getRequestDispatcher("/WEB-INF/pages/tableslist.jsp").forward(request, response);
    }
}*/
