//package com.shinkarev.finalproject.filter;
//
//import jakarta.servlet.*;
//
//import java.io.IOException;
//import java.util.Locale;
//
//public class LocaleFilter implements Filter {
//    private static final String EN_LANG = "en";
//    private static final String US_COUNTRY = "US";
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        Locale locale = new Locale(EN_LANG, US_COUNTRY);
//        response.setLocale(locale);
//        chain.doFilter(request, response);
//    }
//}
