package servlets.admin;

import daos.AccountDAO;
import daos.AdminDAO;
import daos.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Admin;
import models.Student;
import servlets.Utils;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet({"/admin/account",
        "/admin/account/admin",
        "/admin/account/admin/password",
        "/admin/account/student",
        "/admin/account/student/password"
})
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AccountDAO accountDAO;
    private AdminDAO adminDAO;
    private StudentDAO studentDAO;

    public void init() {
        accountDAO = new AccountDAO();
        adminDAO = new AdminDAO();
        studentDAO = new StudentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = adminDAO.findAll();

        Table adminTable = new Table();

        adminTable.addRows(admins);
        adminTable.setFieldProperties(new Admin());
        adminTable.addAction(new Action("add", "1", "post", "Thêm", "btn-primary", "Thêm giáo vụ", "Mật khẩu mặc định là <strong>123</strong>", "/admin/account/admin", false));
        adminTable.addAction(new Action("update", "2", "put", "Sửa", "btn-primary", "Sửa thông tin giáo vụ", null, "/admin/account/admin", true));
        adminTable.addAction(new Action("delete", "3", "delete", "Xoá", "btn-danger", "Xoá tài khoản giáo vụ", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/account/admin", true));
        adminTable.addAction(new Action("delete", "4", "put", "Đặt lại mật khẩu", "btn-danger", "Đặt lại mật khẩu", "Mật khẩu mới sẽ là <strong>123</strong>", "/admin/account/admin/password", true));

        request.setAttribute("adminTable", adminTable);

        List<Student> students = studentDAO.findAll();

        Table studentTable = new Table();

        studentTable.addRows(students);
        studentTable.setFieldProperties(new Student());
        studentTable.addAction(new Action("add", "a", "post", "Thêm", "btn-primary", "Thêm sinh viên", null, "/admin/account/student", false));
        studentTable.addAction(new Action("update", "b", "put", "Sửa", "btn-primary", "Sửa thông tin sinh viên", null, "/admin/account/student", true));
        studentTable.addAction(new Action("delete", "c", "delete", "Xoá", "btn-danger", "Xoá tài khoản sinh viên", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/account/student", true));
        studentTable.addAction(new Action("delete", "d", "put", "Đặt lại mật khẩu", "btn-danger", "Đặt lại mật khẩu", "Mật khẩu mới sẽ là <strong>123</strong>", "/admin/account/student/password", true));

        request.setAttribute("studentTable", studentTable);

        request.getRequestDispatcher("/admin/template?contentPage=account.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/admin/account/admin")) {
            String method = request.getParameter("_method");

            if (method.equals("put")) {
                Utils.updateAdmin(request, response);

                response.sendRedirect("/admin/account");
            } else if (method.equals("post")) {
                String username = request.getParameter("username");
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                String birthday = request.getParameter("birthday");

                HttpSession session = request.getSession();

                if (accountDAO.findById(username) != null) {
                    session.setAttribute("message", "Tài khoản đã có người sử dụng");
                    session.setAttribute("messageType", "danger");
                    response.sendRedirect("/admin/account");
                    return;
                }


                accountDAO.insert(new Account(username, "123", "admin"));
                adminDAO.insert(new Admin(null, username, name, gender, Date.valueOf(birthday)));


                session.setAttribute("message", "Thêm giáo vụ mới thành công");
                session.setAttribute("messageType", "success");

                response.sendRedirect("/admin/account");
            } else if (method.equals("delete")) {
                String username = request.getParameter("username");
                accountDAO.delete(username);

                HttpSession session = request.getSession();
                session.setAttribute("message", "Xoá tài khoản thành công");
                session.setAttribute("messageType", "success");

                response.sendRedirect("/admin/account");
            }
        } else if (path.equals("/admin/account/admin/password")) {
            String method = request.getParameter("_method");

            if (method.equals("put")) {
                String username = request.getParameter("username");
                Account account = accountDAO.findById(username);
                account.setPassword("123");
                accountDAO.update(account);

                HttpSession session = request.getSession();
                session.setAttribute("message", "Đặt lại mật khẩu thành công");
                session.setAttribute("messageType", "success");

                response.sendRedirect("/admin/account");
            }
        }

        // Student ----------------------------------------
        if (path.equals("/admin/account/student")) {
            String method = request.getParameter("_method");

            if (method.equals("put")) {
                Utils.updateStudent(request, response);

                response.sendRedirect("/admin/account");
            } else if (method.equals("post")) {
                String username = request.getParameter("username");
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                String birthday = request.getParameter("birthday");
                String department = request.getParameter("department");

                HttpSession session = request.getSession();

                if (accountDAO.findById(username) != null) {
                    session.setAttribute("message", "Tài khoản đã có người sử dụng");
                    session.setAttribute("messageType", "danger");
                    response.sendRedirect("/admin/account");
                    return;
                }

                accountDAO.insert(new Account(username, "123", "student"));
                studentDAO.insert(new Student(null, username, name, gender, Date.valueOf(birthday), department));


                session.setAttribute("message", "Thêm sinh viên mới thành công");
                session.setAttribute("messageType", "success");

                response.sendRedirect("/admin/account");
            } else if (method.equals("delete")) {
                String username = request.getParameter("username");
                accountDAO.delete(username);

                HttpSession session = request.getSession();
                session.setAttribute("message", "Xoá tài khoản thành công");
                session.setAttribute("messageType", "success");

                response.sendRedirect("/admin/account");
            }
        } else if (path.equals("/admin/account/student/password")) {
            String method = request.getParameter("_method");

            if (method.equals("put")) {
                String username = request.getParameter("username");
                Account account = accountDAO.findById(username);
                account.setPassword("123");
                accountDAO.update(account);

                HttpSession session = request.getSession();
                session.setAttribute("message", "Đặt lại mật khẩu thành công");
                session.setAttribute("messageType", "success");

                response.sendRedirect("/admin/account");
            }
        }
    }
}
