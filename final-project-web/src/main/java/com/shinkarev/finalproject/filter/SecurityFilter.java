package com.shinkarev.finalproject.filter;

import com.shinkarev.finalproject.command.CommandProvider;
import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.EnumSet;

import static com.shinkarev.finalproject.command.ParamName.ERRORS_ON_ERROR_PAGE;
import static com.shinkarev.musicshop.entity.UserRoleType.ANONYMOUS;

@WebFilter("/controller")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute(ParamName.USER);
        UserRoleType userRole;
        userRole = user != null ? user.getRole() : ANONYMOUS;
        EnumSet<UserRoleType> userRoles = CommandProvider.getInstance().getCommandAccessLevel(request);
        if (!userRoles.contains(userRole)) {
            request.setAttribute(ERRORS_ON_ERROR_PAGE, "You should login or register");
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
