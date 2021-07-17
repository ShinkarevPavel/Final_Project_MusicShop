package com.shinkarev.finalproject.controller;

import com.shinkarev.finalproject.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {


    @Override
    public void init() throws ServletException {
//        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CommandName> commandName = getCommandName(request);
        if (commandName.isPresent()) {
            Router router = commandName.get().getCommand().execute(request);
            request.setAttribute(ParamName.REFERER_PARAM, commandName.get().name());
            if (router.getRouterType() == Router.RouterType.REDIRECT) {
                response.sendRedirect(router.getPagePath());
            } else {
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            }
        } else {
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }

    private Optional<CommandName> getCommandName(HttpServletRequest request) {
        String name = request.getParameter(ParamName.COMMAND_PARAM);
        CommandName commandName;
        commandName = CommandName.valueOf(name.toUpperCase());
        return Optional.of(commandName);
    }
    @Override
    public void destroy() {
//        super.destroy();
    }
}
