//package com.shinkarev.finalproject.web;//package com.shinkarev.finalproject.web;
//
//import com.shinkarev.musicshop.dao.impl.UserDaoImpl;
//import com.shinkarev.musicshop.entity.User;
//import com.shinkarev.musicshop.exception.DaoException;
//import com.shinkarev.musicshop.util.PasswordHashGenerator;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet(name = "loginServlet", value = "/login")
//public class LoginServlet extends HttpServlet {
//
//
//    public void init() throws ServletException {
//
//    }
//
//
//    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        String registration = req.getParameter("reg");
//        UserDaoImpl userDao = new UserDaoImpl();
//        if (registration != null) {
//            req.getRequestDispatcher("/pages/registration.jsp").forward(req, resp);
//        }
//
//        try {
//            Optional<User> currentUser = userDao.findUserByLoginAndPassword(login, password);
//            if (currentUser.isPresent()) {
//                if (password.equals(userDao.getUserPasswordByLogin(login))) {
//                    User user = currentUser.get();
//                    req.setAttribute("user", user);
//                    switch (user.getRole()) {
//                        case ADMIN:
//                            req.getRequestDispatcher("/pages/show_all_users.jsp").forward(req, resp);
//                            break;
//                        case CLIENT:
//                            break;
//                        case GUEST:
//                            break;
//                    }
//                } else {
//                    req.setAttribute("login", login);
//                    req.setAttribute("error", "incorrect password");
//                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
//                }
//            } else {
//                req.setAttribute("login", login);
//                req.getRequestDispatcher("/pages/usernotfound.jsp").forward(req, resp);
//            }
//        } catch (DaoException ex) {
//            // TODO
//        }
//    }
//
//    public void destroy() {
//
//    }
//}
