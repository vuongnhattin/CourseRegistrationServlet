package servlets.admin;

import daos.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Admin;
import models.Subject;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/subject")
public class SubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubjectDAO subjectDAO;

    public void init() {
        subjectDAO = new SubjectDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subject> subjects = subjectDAO.findAll();

        Table table = new Table();

        table.addRows(subjects);
        table.setFieldProperties(new Subject());
        table.addAction(new Action("add", "1", "post", "Thêm", "btn-primary", "Thêm môn học", null, "/admin/subject", false));
        table.addAction(new Action("update", "2", "put", "Sửa", "btn-primary", "Sửa thông tin môn học",null, "/admin/subject", true));
        table.addAction(new Action("delete", "3", "delete", "Xoá", "btn-danger", "Xoá môn học", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/subject", true));
        request.setAttribute("table", table);

        request.getRequestDispatcher("/admin/template?contentPage=subject.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method.equals("post")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            Integer credits = Integer.valueOf(request.getParameter("credits"));

            HttpSession session = request.getSession();

            if (subjectDAO.findById(id) != null) {
                session.setAttribute("message", "Mã môn học đã tồn tại");
                session.setAttribute("messageType", "danger");
                response.sendRedirect("/admin/subject");
                return;
            }

            try {
                subjectDAO.insert(new Subject(id, name, credits));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("message", "Thêm môn học mới thành công");
            session.setAttribute("messageType", "success");

            response.sendRedirect("/admin/subject");
        }

        if (method.equals("put")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            Integer credits = Integer.valueOf(request.getParameter("credits"));

            /*Subject subject = subjectDAO.findById(id);

            subject.setName(name);
            subject.setCredits(credits);*/

            subjectDAO.update(new Subject(id, name, credits));

            HttpSession session = request.getSession();
            session.setAttribute("message", "Cập nhật thành công");
            session.setAttribute("messageType", "success");

            response.sendRedirect("/admin/subject");
        }

        if (method.equals("delete")) {
            String id = request.getParameter("id");
            subjectDAO.delete(id);

            HttpSession session = request.getSession();
            session.setAttribute("message", "Xoá thành công");
            session.setAttribute("messageType", "success");

            response.sendRedirect("/admin/subject");
        }
    }
}