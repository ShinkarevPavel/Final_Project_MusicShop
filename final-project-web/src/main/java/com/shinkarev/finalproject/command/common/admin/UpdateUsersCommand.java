package com.shinkarev.finalproject.command.common.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateUsersCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        System.out.println(email);
        System.out.println(role);
        System.out.println(status);
        return null;
    }
}
