package com.shinkarev.finalproject.web;

import com.shinkarev.finalproject.validator.UserValidator;
import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import com.shinkarev.musicshop.entity.UserStatusType;
import com.shinkarev.musicshop.exception.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {


    public void init() {

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("here");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String checkPassword = req.getParameter("checkPass");
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String name = req.getParameter("name");
        String surename = req.getParameter("surename");
        Map<String, Boolean> service = new HashMap<>();
        service.put(login, false);
        service.put(password, false);
        service.put(email, false);
        service.put(nickname, false);
        service.put(name, false);
        service.put(surename, false);

        if (login.matches(UserValidator.LOGIN.getRegExp())) { // + check unique in db
            service.put(login, true);
            req.setAttribute(UserValidator.LOGIN.getFieldName(), login);
        } else {
            req.setAttribute("loginError", UserValidator.LOGIN.getMessage());
        }
        if (password.matches(UserValidator.PASSWORD.getRegExp())) {
            if (password.equals(checkPassword)) {
                service.put(password, true);
                req.setAttribute("password", password);
            } else {
                req.setAttribute("SecondPasswordError", "passwords are differ"); // TODO message does not come to jsp
            }
        } else {
            req.setAttribute("PasswordError", "password is not correct"); // TODO message dose not come to jsp
        }
        if (email.matches(UserValidator.EMAIL.getRegExp())) {
            service.put(email, true);
            req.setAttribute("email", email);
        }
        if (nickname.matches(UserValidator.NICKNAME.getRegExp())) {
            service.put(nickname, true);
            req.setAttribute("nickname", nickname);
        }
        if (name != null) {
            service.put(name, true);
            req.setAttribute("name", name);
        }
        if (surename != null) {
            service.put(surename, true);
            req.setAttribute("surename", surename);
        }
        if (!service.containsValue(false)) {
            System.out.println("ok");
            req.getRequestDispatcher("/pages/registration_is_done.jsp").forward(req, resp);
            // TODO will sent request on user email for registration confirming
            User user = new User(login, email, nickname, name, surename, UserStatusType.ACTIVE, UserRoleType.CLIENT);
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                userDao.addUser(user, password);
            } catch (DaoException e) {
                System.out.println("fuck"); // TODO
            }
        } else {
            req.getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
        }
    }

    public void destroy() {

    }
}
