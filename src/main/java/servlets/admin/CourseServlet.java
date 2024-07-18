package servlets.admin;

import daos.CourseDAO;
import daos.CurrentSemesterDAO;
import daos.RegisterTimeDAO;
import daos.SemesterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Course;
import models.CurrentSemester;
import models.RegisterTime;
import models.Semester;
import servlets.Utils;
import utils.HTMLTable.Action;
import utils.HTMLTable.Table;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/admin/course")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RegisterTimeDAO registerTimeDAO;

    public void init() {
        registerTimeDAO = new RegisterTimeDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RegisterTime> registerTime = registerTimeDAO.findAll();
        request.setAttribute("registerTime", registerTime);

        Table registerTimeTable = new Table();
        registerTimeTable.addRows(registerTime);
        registerTimeTable.setFieldProperties(new RegisterTime());
        registerTimeTable.addAction(new Action("update", "1", "post", "Tạo kì đăng ký học phần", "btn-primary", "Tạo kì đăng ký học phần", "Kỳ được tạo sẽ thay thế cho kỳ hiện tại", "/admin/course?type=1", false));
        registerTimeTable.addAction(new Action("delete", "2", "delete", "Xoá kì đăng ký học phần", "btn-danger", "Xoá kì đăng ký học phần", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/course?type=1", true));

        request.setAttribute("registerTimeTable", registerTimeTable);


        List<Course> courses = new CourseDAO().getCourseCurrentSemester();
        Table courseTable = new Table();
        courseTable.addRows(courses);
        courseTable.setFieldProperties(new Course());
        courseTable.addAction(new Action("add", "3", "post", "Thêm học phần", "btn-primary", "Thêm học phần", null, "/admin/course?type=2", false));
        courseTable.addAction(new Action("delete", "4", "delete", "Xoá học phần", "btn-danger", "Xoá học phần", "Bạn chắc chắn chứ? Hành động này không thể hoàn tác", "/admin/course?type=2", true));

        request.setAttribute("courseTable", courseTable);


        Semester currentSemester = new CurrentSemesterDAO().findById(1).getSemesterBySemesterId();
        request.setAttribute("currentSemester", currentSemester);

        request.getRequestDispatcher("/admin/template?contentPage=course.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String method = request.getParameter("_method");
        String type = request.getParameter("type");

        if (type.equals("1")) {
            if (method.equals("post")) {
                String startTime_ = request.getParameter("startTime").concat(":00");
                String endTime_ = request.getParameter("endTime").concat(":00");

                Timestamp startTime = Timestamp.valueOf(startTime_.replace("T", " "));
                Timestamp endTime = Timestamp.valueOf(endTime_.replace("T", " "));

                registerTimeDAO.update(new RegisterTime(1, startTime, endTime));

                Utils.setSuccessMessage(request, "Tạo kì đăng ký học phần thành công");

                response.sendRedirect("/admin/course");
            }

            if (method.equals("delete")) {
                registerTimeDAO.delete(1);

                Utils.setSuccessMessage(request, "Xoá kì đăng ký học phần thành công");

                response.sendRedirect("/admin/course");
            }
        }

        if (type.equals("2")) {
            if (method.equals("post")) {
                String subjectIdName = request.getParameter("subjectIdName");
                String teacherIdName = request.getParameter("teacherIdName");
                String room = request.getParameter("room");
                String day = request.getParameter("day");
                String time = request.getParameter("time");
                String maxSlots = request.getParameter("maxSlots");

                String subjectId = subjectIdName.split(" - ")[0];
                String teacherId = teacherIdName.split(" - ")[0];

                Integer currentSemester = new CurrentSemesterDAO().findById(1).getSemesterId();
                new CourseDAO().insert(new Course(null, currentSemester, subjectId, Integer.parseInt(teacherId), room, day, time, Integer.parseInt(maxSlots)));

                Utils.setSuccessMessage(request, "Thêm học phần thành công");

                response.sendRedirect("/admin/course");
            }

            if (method.equals("delete")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                new CourseDAO().delete(id);

                Utils.setSuccessMessage(request, "Xoá học phần thành công");

                response.sendRedirect("/admin/course");
            }
        }
    }
}
