package com.shinkarev.finalproject.command.common.admin;

import com.shinkarev.finalproject.command.Command;
import com.shinkarev.finalproject.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class UserInfoCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        System.out.println("I am here");
        System.out.println(request.getParameter("userId"));

//        request.setAttribute("roles", List.of("ADMIN", "USER"));
//        request.setAttribute("statuses", List.of("ACTIVE", "BLOCKED"));
        return null;
    }
}
