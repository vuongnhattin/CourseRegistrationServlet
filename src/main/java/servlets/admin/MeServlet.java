package servlets.admin;

import daos.AccountDAO;
import daos.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Admin;
import servlets.Utils;

import java.io.IOException;

@WebServlet({"/admin/me", "/admin/me/reset-password", "/admin/me/change-password", "/admin/me/update"})
public class MeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;
    private AdminDAO adminDAO;

    public void init() {
        accountDAO = new AccountDAO();
        adminDAO = new AdminDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/admin/me")) {
            getMe(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/admin/me")) {
            doGet(request, response);
        }

        else if (path.equals("/admin/me/reset-password")) {
            postResetPassword(request, response);
        }

        else if (path.equals("/admin/me/change-password")) {
            postChangePassword(request, response);
        }

        else if (path.equals("/admin/me/update")) {
            Utils.updateAdmin(request, response);
            response.sendRedirect("/admin/me");
        }

        doGet(request, response);
    }

    private void getMe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Account account = accountDAO.findById(username);
        Admin me = account.getAdmin();

        request.setAttribute("me", me);

        request.getRequestDispatcher("/admin/template?contentPage=me.jsp").forward(request, response);
    }

    private void postResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Account account = accountDAO.findById(username);
        account.setPassword("123");
        accountDAO.update(account);

        session.setAttribute("message", "Đặt lại mật khẩu thành công");
        session.setAttribute("messageType", "success");

        response.sendRedirect("/admin/me");
    }

    private void postChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String renewPassword = request.getParameter("renew-password");

        HttpSession session = request.getSession();
        Account account = accountDAO.findById(session.getAttribute("username").toString());

        if (!oldPassword.equals(account.getPassword())) {
            session.setAttribute("message", "Mật khẩu cũ không đúng");
            session.setAttribute("messageType", "danger");
            response.sendRedirect("/admin/me");
        }
        else if (!newPassword.equals(renewPassword)) {
            session.setAttribute("message", "Mật khẩu mới không trùng khớp");
            session.setAttribute("messageType", "danger");
            response.sendRedirect("/admin/me");
        }
        else {
            session.setAttribute("message", "Đổi mật khẩu thành công");
            session.setAttribute("messageType", "success");
            account.setPassword(newPassword);
            accountDAO.update(account);
            response.sendRedirect("/admin/me");
        }
    }
}
