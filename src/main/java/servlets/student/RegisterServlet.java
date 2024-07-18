package servlets.student;

import daos.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.*;
import servlets.Utils;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/student/register")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Utils.isRegisterTime()) {
            request.setAttribute("isRegisterTime", false);
            request.getRequestDispatcher("/student/template?contentPage=register.jsp").forward(request, response);
        }

        else {
            request.setAttribute("isRegisterTime", true);
            HttpSession session = request.getSession();
            Integer studentId = (Integer) session.getAttribute("id");

            List<Course> results = new CourseDAO().getCourseCurrentSemesterOfStudent(studentId);
            Table resultTable = new Table();
            resultTable.addRows(results);
            resultTable.setFieldProperties(new Course());
            resultTable.addAction(new Action("get", "1", "delete", "Huỷ học phần", "btn-primary", "Thêm học phần", null, "/student/register", true));
            request.setAttribute("resultTable", resultTable);

            List<Course> courses = new CourseDAO().getCourseCurrentSemesterNotOfStudent(studentId);
            Table courseTable = new Table();
            courseTable.addRows(courses);
            courseTable.setFieldProperties(new Course());
            courseTable.addAction(new Action("get", "2", "post", "Đăng kí học phần", "btn-primary", "Thêm học phần", null, "/student/register", true));
            request.setAttribute("courseTable", courseTable);

            Semester currentSemester = new CurrentSemesterDAO().findById(1).getSemesterBySemesterId();
            request.setAttribute("currentSemester", currentSemester);

            RegisterTime registerTime = new RegisterTimeDAO().findById(1);
            request.setAttribute("registerTime", registerTime);

            request.getRequestDispatcher("/student/template?contentPage=register.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!Utils.isRegisterTime()) {
            return;
        }

        String method = request.getParameter("_method");

        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("id");

        if (method.equals("post")) {
            Integer courseId = Integer.parseInt(request.getParameter("id"));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Long numCourse = new RegisterDAO().getNumberOfCourseOfStudentInCurrentSemester(studentId);
            if (numCourse == 5) {
                Utils.setErrorMessage(request, " Bạn chỉ có thể đăng ký tối đa 5 học phần");
                response.sendRedirect("/student/register");
                return;
            }

            new RegisterDAO().insert(new Register(studentId, courseId, timestamp));

            Utils.setSuccessMessage(request, "Đăng kí học phần thành công");

            response.sendRedirect("/student/register");
        }

        if (method.equals("delete")) {
            Integer courseId = Integer.parseInt(request.getParameter("id"));
            new RegisterDAO().delete(new RegisterPK(studentId, courseId));

            Utils.setSuccessMessage(request, "Huỷ học phần thành công");

            response.sendRedirect("/student/register");
        }
    }
}
