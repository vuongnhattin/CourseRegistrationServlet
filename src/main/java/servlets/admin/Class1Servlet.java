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
import servlets.Utils;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/class")
public class Class1Servlet extends HttpServlet {
    Class1DAO class1DAO;
    StudyDAO studyDAO;
    StudentDAO studentDAO;
    AccountDAO accountDAO;

    public void init() throws ServletException {
        class1DAO = new Class1DAO();
        studyDAO = new StudyDAO();
        studentDAO = new StudentDAO();
        accountDAO = new AccountDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Class1> classes = class1DAO.findAll();

        Table table = new Table();

        table.addRows(classes);
        table.setFieldProperties(new Class1());
        table.addAction(new Action("add", "1", "post", "Thêm lớp học", "btn-primary", "Thêm một lớp học ", null, "/admin/class", false));
        table.addAction(new Action("update", "2", "put", "Sửa thông tin lớp", "btn-primary", "Sửa thông tin môn học", null, "/admin/class", true));
        table.addAction(new Action("delete", "3", "delete", "Xoá lớp học", "btn-danger", "Xoá môn học", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/class", true));
        table.addAction(new Action("get", "4", "get", "Xem chi tiết lớp học", "btn-primary", null, null, "/admin/class-detail", true));
        request.setAttribute("table", table);

        request.getRequestDispatcher("/admin/template?contentPage=class.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method.equals("post")) {
            String name = request.getParameter("name");
            class1DAO.insert(new Class1(null, name, null, null, null, null));

            Utils.setSuccessMessage(request, "Thêm lớp học mới thành công");

            response.sendRedirect("/admin/class");
        } else if (method.equals("put")) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            String name = request.getParameter("name");
            class1DAO.update(new Class1(id, name, null, null, null, null));

            Utils.setSuccessMessage(request, "Sửa thông tin lớp học thành công");

            response.sendRedirect("/admin/class");
        } else if (method.equals("delete")) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            class1DAO.delete(id);

            Utils.setSuccessMessage(request, "Xoá lớp học thành công");

            response.sendRedirect("/admin/class");
        }
    }
}
