package servlets.admin;

import daos.CurrentSemesterDAO;
import daos.SemesterDAO;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Admin;
import models.CurrentSemester;
import models.Semester;
import servlets.Utils;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/admin/semester")
public class SemesterServlet extends HttpServlet {
    private SemesterDAO semesterDAO;
    private CurrentSemesterDAO currentSemesterDAO;

    public void init() {
        semesterDAO = new SemesterDAO();
        currentSemesterDAO = new CurrentSemesterDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Semester> semesters = semesterDAO.findAll();

        Table table = new Table();

        table.addRows(semesters);
        table.setFieldProperties(new Semester());
        table.addAction(new Action("add", "1", "post", "Thêm", "btn-primary", "Thêm học kỳ", null, "/admin/semester", false));
        table.addAction(new Action("get", "2", "put", "Đặt làm học kỳ hiện tại", "btn-primary", null,null, "/admin/semester", true));
        table.addAction(new Action("delete", "3", "delete", "Xoá", "btn-danger", "Xoá tài học kỳ", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/semester", true));

        request.setAttribute("table", table);

        CurrentSemester currentSemester = currentSemesterDAO.findById(1);
        Semester semester = currentSemester.getSemesterBySemesterId();
        request.setAttribute("currentSemester", semester);

        request.getRequestDispatcher("/admin/template?contentPage=semester.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method.equals("post")) {
            String name = request.getParameter("name");
            String year = request.getParameter("year");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");

            try {
                semesterDAO.insert(new Semester(null, name, Integer.valueOf(year), Date.valueOf(startDate), Date.valueOf(endDate)));
            } catch (PersistenceException e) {
                if (e.getCause() instanceof SQLException) {
                    SQLException sqlException = (SQLException) e.getCause();

                    if (sqlException.getSQLState().equals("23000") && sqlException.getErrorCode() == 2627) {
                        Utils.setErrorMessage(request, "Một năm học không thể có hai học kỳ trùng tên nhau");

                        response.sendRedirect("/admin/semester");
                        return;
                    }
                }
                response.sendError(500, e.getMessage());
            }

            Utils.setSuccessMessage(request, "Thêm học kỳ mới thành công");

            response.sendRedirect("/admin/semester");
        }

        if (method.equals("put")) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            //currentSemesterDAO.delete(1);
            currentSemesterDAO.update(new CurrentSemester(1, id));

            Utils.setSuccessMessage(request, "Đặt học kỳ hiện tại thành công");

            response.sendRedirect("/admin/semester");
        }

        if (method.equals("delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));

            CurrentSemester currentSemester = currentSemesterDAO.findById(1);
            if (Objects.equals(currentSemester.getSemesterId(), id)) {
                Utils.setErrorMessage(request, "Không thể xoá học kỳ hiện tại");
                response.sendRedirect("/admin/semester");
                return;
            }

            semesterDAO.delete(id);

            Utils.setSuccessMessage(request, "Xoá thành công");

            response.sendRedirect("/admin/semester");
        }
    }
}
