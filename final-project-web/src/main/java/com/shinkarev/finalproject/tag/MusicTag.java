package com.shinkarev.finalproject.tag;

import com.shinkarev.finalproject.command.ParamName;
import com.shinkarev.musicshop.entity.User;
import com.shinkarev.musicshop.entity.UserRoleType;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;


public class MusicTag extends TagSupport {

    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(ParamName.USER);
        if (user != null && user.getRole() == UserRoleType.ADMIN) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
