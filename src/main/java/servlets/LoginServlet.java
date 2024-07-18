package servlets;

import daos.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Account;

import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;

    public void init() {
        accountDAO = new AccountDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("type") != null) {
            HttpSession session = request.getSession();
            response.sendRedirect(String.format("/%s/home", session.getAttribute("type")));
            return;
        }

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    private boolean isAuthenticated(String username, String password) {
        Account account = accountDAO.findById(username);
        if (account != null && account.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (isAuthenticated(username, password)) {
            HttpSession session = request.getSession();
            Account account = accountDAO.findById(username);

            Integer id = accountDAO.getIdByUsername(username);

            session.setAttribute("id", id);
            session.setAttribute("username", username);
            session.setAttribute("type", account.getType());

            // Expire in 30 minutes
            session.setMaxInactiveInterval(30 * 60);
            Cookie userNameCookie = new Cookie("username", username);
            userNameCookie.setMaxAge(60 * 60);
            response.addCookie(userNameCookie);

            String redirect = (String) session.getAttribute("redirect");

            if (redirect != null) {
                response.sendRedirect(redirect);
            }
            else {
                response.sendRedirect(String.format("/%s/home", account.getType()));
            }
        }
        else {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu sai");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
