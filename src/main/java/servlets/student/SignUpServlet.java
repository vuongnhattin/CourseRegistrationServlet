package servlets.student;

import daos.AccountDAO;
import daos.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Account;
import models.Student;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;
    private StudentDAO studentDAO;

    public void init() {
        accountDAO = new AccountDAO();
        studentDAO = new StudentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/sign-up.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("re-password");
        String fullName = request.getParameter("fullName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String department = request.getParameter("department");

        if (accountDAO.findById(username) != null) {
            request.setAttribute("error", "Tài khoản đã có người sử dụng");
            request.getRequestDispatcher("WEB-INF/sign-up.jsp").forward(request, response);
            return;
        }

        if (!password.equals(repeatPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp nhau");
            request.getRequestDispatcher("WEB-INF/sign-up.jsp").forward(request, response);
            return;
        }

        try {
            accountDAO.insert(new Account(username, password, "student"));
            studentDAO.insert(new Student(null, username, fullName, sex, Date.valueOf(birthday), department));

            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
