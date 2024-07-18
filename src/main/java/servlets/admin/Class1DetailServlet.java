package servlets.admin;

import daos.AccountDAO;
import daos.Class1DAO;
import daos.StudentDAO;
import daos.StudyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Class1;
import models.Student;
import models.Study;
import models.StudyPK;
import servlets.Utils;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/class-detail")
public class Class1DetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Class1DAO class1DAO;
    private StudentDAO studentDAO;
    private StudyDAO studyDAO;

    public void init() {
        class1DAO = new Class1DAO();
        studentDAO = new StudentDAO();
        studyDAO = new StudyDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));

        List<Student> studentsInClass = class1DAO.getStudentsInClassById(id);

        Table table1 = new Table();

        table1.addRows(studentsInClass);
        table1.setFieldProperties(new Student());
        table1.addAction(new Action("get", "1", "delete", "Xoá sinh viên khỏi lớp", "btn-danger", null, null, String.format("/admin/class-detail?id=%d", id), true));

        request.setAttribute("table1", table1);

        List<Student> studentsNotInClass = class1DAO.getStudentsNotInClassById(id);

        Table table2 = new Table();

        table2.addRows(studentsNotInClass);
        table2.setFieldProperties(new Student());
        table2.addAction(new Action("get", "2", "post", "Thêm sinh viên vào lớp", "btn-primary", null, null, String.format("/admin/class-detail?id=%d", id), true));

        request.setAttribute("table2", table2);

        Class1 class1 = class1DAO.findById(id);
        request.setAttribute("className", class1.getName());

        request.getRequestDispatcher("/admin/template?contentPage=class-detail.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("_method");
        Integer classId = Integer.parseInt(request.getParameter("id"));

        if (method.equals("post")) {
            String username = request.getParameter("username");
            Integer studentId = new AccountDAO().getIdByUsername(username);

            studyDAO.insert(new Study(studentId, classId));

            Utils.setSuccessMessage(request, "Thêm sinh viên vào lớp thành công\n");

            response.sendRedirect(String.format("/admin/class-detail?id=%d", classId));
        }

        if (method.equals("delete")) {
            String username = request.getParameter("username");
            Integer studentId = new AccountDAO().getIdByUsername(username);

            studyDAO.delete(new StudyPK(studentId, classId));

            Utils.setSuccessMessage(request, "Xoá sinh viên khỏi lớp thành công\n");

            response.sendRedirect(String.format("/admin/class-detail?id=%d", classId));
        }
    }
}
