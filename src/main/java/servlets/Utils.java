package servlets;

import daos.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;
import models.Admin;
import models.Student;
import utils.HTMLTable.IField;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static AccountDAO accountDAO = new AccountDAO();
    private static AdminDAO adminDAO = new AdminDAO();
    private static StudentDAO studentDAO = new StudentDAO();

    public static String getRelativeURL(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        return requestURI + (queryString == null ? "" : "?" + queryString);
    }

    public static void updateAdmin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");

        Account account = accountDAO.findById(username);
        Admin admin = account.getAdmin();

        admin.setName(name);
        admin.setBirthday(Date.valueOf(birthday));
        admin.setGender(gender);

        adminDAO.update(admin);

        HttpSession session = request.getSession();
        session.setAttribute("message", "Thay đổi thông tin thành công");
        session.setAttribute("messageType", "success");
    }

    public static void updateStudent(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        String department = request.getParameter("department");

        Account account = accountDAO.findById(username);
        Student student = account.getStudent();

        student.setName(name);
        student.setBirthday(Date.valueOf(birthday));
        student.setGender(gender);
        student.setDepartment(department);

        studentDAO.update(student);

        HttpSession session = request.getSession();
        session.setAttribute("message", "Thay đổi thông tin thành công");
        session.setAttribute("messageType", "success");
    }

    public static <T extends AbstractDAO> void add(T dao, IField model, HttpServletRequest request, HttpServletResponse response, String duplicatedMsg, String successMeg, String redirectUrl) throws IOException {
        List<String> fields = new ArrayList<String>();
        for (String field : model.getFieldVariables()) {
            fields.add(request.getParameter(field));
        }

        HttpSession session = request.getSession();

        if (dao.findById(fields.get(0)) != null) {
            session.setAttribute("message", duplicatedMsg);
            session.setAttribute("messageType", "danger");
            response.sendRedirect(redirectUrl);
            return;
        }
    }

    public static void setSuccessMessage(HttpServletRequest request, String msg) {
        HttpSession session = request.getSession();
        session.setAttribute("message", msg);
        session.setAttribute("messageType", "success");
    }

    public static void setErrorMessage(HttpServletRequest request, String msg) {
        HttpSession session = request.getSession();
        session.setAttribute("message", msg);
        session.setAttribute("messageType", "danger");
    }

    public static Boolean isRegisterTime() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp start = new RegisterTimeDAO().findById(1).getStartTime();
        Timestamp end = new RegisterTimeDAO().findById(1).getEndTime();
        return (now.after(start) && now.before(end));
    }
}
