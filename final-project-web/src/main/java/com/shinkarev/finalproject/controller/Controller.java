package com.shinkarev.finalproject.controller;

import com.shinkarev.finalproject.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * The {@link Controller} class is a main HttpServlet.
 * Overrides doPost and doGet methods by calling
 * the own method processRequest(request, response).
 */

@MultipartConfig(fileSizeThreshold = 1200 * 1024)
@WebServlet(name = "controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * This method is called by doGet and doPost methods.
     * Gets the command from {@link CommandProvider} and if it present
     * call execute method that return router.
     * Sets response to the error page if ServiceException occurs or continue work program
     *
     *
     * @param request object that contains request that was created by client
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException if the request could not be handled.
     * @throws IOException if detected  input or output errors during the servlet handles the request.
     */

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CommandName> commandName = CommandProvider.getInstance().getCommandName(request);
        if (commandName.isPresent()) {
            Router router = commandName.get().getCommand().execute(request);
            request.setAttribute(ParamName.REFERER_PARAM, commandName.get().name());
            if (router.hasError()) {
                response.sendError(router.getErrorCode());
            } else if (router.getRouterType() == Router.RouterType.REDIRECT) {
                response.sendRedirect(router.getPagePath());
            } else {
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            }
        } else {
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
