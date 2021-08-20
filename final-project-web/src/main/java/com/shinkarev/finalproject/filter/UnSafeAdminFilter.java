package com.shinkarev.finalproject.filter;

import com.shinkarev.finalproject.command.PageName;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.shinkarev.finalproject.command.ParamName.METHOD_POST;
import static com.shinkarev.finalproject.command.ParamName.USER;

@WebFilter("/pages/*")
public class UnSafeAdminFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute(USER);
        if (user == null || user.getRole() != UserRoleType.ADMIN) {
            logger.warn("Unsafe page opening detected. Redirecting to main page from {}", ((HttpServletRequest) servletRequest).getRequestURI());
            servletRequest.getRequestDispatcher(PageName.MAIN_PAGE).forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}