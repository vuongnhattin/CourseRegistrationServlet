package servlets.student;

import daos.CourseDAO;
import daos.CurrentSemesterDAO;
import daos.SemesterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Course;
import models.Semester;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student/result")
public class ResultServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer studentId = (Integer)session.getAttribute("id");

        String semesterName = request.getParameter("semesterName");
        String semesterYear_ = request.getParameter("semesterYear");
        Integer semesterYear = null;
        if (semesterName == null || semesterYear_ == null) {
            semesterName = new CurrentSemesterDAO().getCurrentSemesterName();
            semesterYear = new CurrentSemesterDAO().getCurrentSemesterYear();
        }
        else {
            semesterYear = Integer.valueOf(semesterYear_);
        }

        Semester semester = new SemesterDAO().findSemesterByNameAndYear(semesterName, semesterYear);

        request.setAttribute("semesterName", semesterName);
        request.setAttribute("semesterYear", semesterYear);

        List<Course> courses = new ArrayList<>();

        if (semester != null) {
            courses = new CourseDAO().getCourseByStudentAndSemester(studentId, semester.getId());
        }

        Table table = new Table();
        table.addRows(courses);
        table.setFieldProperties(new Course());

        request.setAttribute("table", table);

        request.getRequestDispatcher("/student/template?contentPage=result.jsp").forward(request, response);
    }
}
