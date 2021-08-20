package com.shinkarev.finalproject.command;

public interface PageName {

    String INDEX_PAGE = "/index.jsp";
    String REGISTRATION_PAGE = "/pages/common/registration.jsp";
    String REGISTRATION_IS_DONE = "/pages/client/registration_is_done.jsp";
    String ADMIN_PAGE = "/pages/admin/admin.jsp";
    String LOGIN_PAGE = "/pages/common/login.jsp";
    String SHOW_ALL_USERS = "/pages/admin/show_all_users.jsp";
    String ERROR_PAGE = "/pages/common/error_page.jsp";
    String USER_INFO_PAGE = "/pages/admin/user_info.jsp";
    String FIND_USER = "/pages/admin/find_user.jsp";
    String ADD_USER = "/pages/admin/add_user.jsp";
    String ADD_INSTRUMENT = "/pages/admin/add_instrument.jsp";
    String SHOW_INSTRUMENTS = "/pages/admin/show_instruments.jsp";
    String CLIENT_SHOW_INSTRUMENT_PAGE = "/pages/common/client_show_instruments.jsp";
    String CLIENT_SHOW_INSTRUMENT_DETAILS = "/pages/common/show_instrument_details.jsp";
    String CLIENT_BUCKET_PAGE = "/pages/client/cart_page.jsp";
    String ORDER_PAGE = "/pages/client/order_page.jsp";
    String CABINET_PAGE = "/pages/client/cabinet_page.jsp";
    String CHANGE_PASSWORD_PAGE = "/pages/client/change_password_page.jsp";
    String EDIT_PROFILE_PAGE = "/pages/client/edit_profile_page.jsp";
    String SHOW_ALL_ORDERS = "/pages/admin/show_all_orders.jsp";
    String UPDATE_INSTRUMENT_PAGE = "/pages/admin/update_instrument.jsp";
    String MAIN_PAGE = "/pages/common/main_page.jsp";
    String BLOCKED_PAGE = "/pages/admin/blocked_page.jsp";
    String FORGOT_PASSWORD_PAGE = "/pages/common/forgot_password.jsp";
    String FORGOT_PASSWORD_CHANGE = "/pages/common/enter_new_password.jsp";
    String REDIRECT_REGISTRATION_IS_DONE = "controller?command=to_registration_is_done_page_command";
    String REDIRECT_CABINET_PAGE = "controller?command=to_cabinet_page_command";
    String REDIRECT_LOGIN_PAGE = "controller?command=to_login_page_command";
    String REDIRECT_ORDER_PAGE = "controller?command=order_processing_command";
}
