package servlets.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student/template")
public class TemplateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contentPage = request.getParameter("contentPage");
        request.setAttribute("contentPage", contentPage);
        request.getRequestDispatcher("/WEB-INF/student/template.jsp").forward(request, response);
    }
}
