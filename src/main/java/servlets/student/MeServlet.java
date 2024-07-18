package servlets.student;

import daos.AccountDAO;
import daos.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Student;
import servlets.Utils;

import java.io.IOException;

@WebServlet({"/student/me", "/student/me/reset-password", "/student/me/change-password", "/student/me/update"})
public class MeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;
    private StudentDAO studentDAO;

    public void init() {
        accountDAO = new AccountDAO();
        studentDAO = new StudentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/student/me")) {
            getMe(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/student/me")) {
            doGet(request, response);
        }

        else if (path.equals("/student/me/reset-password")) {
            postResetPassword(request, response);
        }

        else if (path.equals("/student/me/change-password")) {
            postChangePassword(request, response);
        }

        else if (path.equals("/student/me/update")) {
            Utils.updateStudent(request, response);
            response.sendRedirect("/student/me");
        }

        doGet(request, response);
    }

    private void getMe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Account account = accountDAO.findById(username);
        Student me = account.getStudent();

        request.setAttribute("me", me);

        request.getRequestDispatcher("/student/template?contentPage=me.jsp").forward(request, response);
    }

    private void postResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Account account = accountDAO.findById(username);
        account.setPassword("123");
        accountDAO.update(account);

        session.setAttribute("message", "Đặt lại mật khẩu thành công");
        session.setAttribute("messageType", "success");

        response.sendRedirect("/student/me");
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
            response.sendRedirect("/student/me");
        }
        else if (!newPassword.equals(renewPassword)) {
            session.setAttribute("message", "Mật khẩu mới không trùng khớp");
            session.setAttribute("messageType", "danger");
            response.sendRedirect("/student/me");
        }
        else {
            session.setAttribute("message", "Đổi mật khẩu thành công");
            session.setAttribute("messageType", "success");
            account.setPassword(newPassword);
            accountDAO.update(account);
            response.sendRedirect("/student/me");
        }
    }
}
