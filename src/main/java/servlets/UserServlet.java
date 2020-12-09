package servlets;

import models.User;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8), true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (req.getParameter("name") == null
                && req.getParameter("age") == null
                && req.getParameter("isMarried") == null) {
            out.print(userService.getUsers());
        } else {
            out.print(userService.getUser(
                    new User(
                            req.getParameter("name"),
                            Integer.parseInt(req.getParameter("age")),
                            Boolean.parseBoolean(req.getParameter("isMarried"))
                    )));
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8), true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        if (req.getParameter("name") == null
                && req.getParameter("age") == null
                && req.getParameter("isMarried") == null) {
            out.print("Введены не все данные");
        } else {
            userService.putUser(
                    new User(
                            req.getParameter("name"),
                            Integer.parseInt(req.getParameter("age")),
                            Boolean.parseBoolean(req.getParameter("isMarried"))
                    ));
            out.print("Добавление прошло успешно");
        }
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8), true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        if (req.getParameter("oldName") == null
                && req.getParameter("oldAge") == null
                && req.getParameter("oldIsMarried") == null
                && req.getParameter("newName") == null
                && req.getParameter("newAge") == null
                && req.getParameter("newIsMarried") == null) {
            out.print("Введены не все данные");
        } else {
            userService.updateUser(
                    new User(
                            req.getParameter("oldName"),
                            Integer.parseInt(req.getParameter("oldAge")),
                            Boolean.parseBoolean(req.getParameter("oldIsMarried"))),
                    new User(
                            req.getParameter("newName"),
                            Integer.parseInt(req.getParameter("newAge")),
                            Boolean.parseBoolean(req.getParameter("newIsMarried"))
                    ));
            out.print("Изменение прошло успешно");
        }
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8), true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        if (req.getParameter("name") == null
                && req.getParameter("age") == null
                && req.getParameter("isMarried") == null) {
            userService.deleteUsers();
            out.print("Удаление всех пользователей прошло успешно");
        } else {
            userService.deleteUser(
                    new User(
                            req.getParameter("name"),
                            Integer.parseInt(req.getParameter("age")),
                            Boolean.parseBoolean(req.getParameter("isMarried"))
                    ));
            out.print("Удаление пользователя прошло успешно");
        }
        out.flush();
    }
}
