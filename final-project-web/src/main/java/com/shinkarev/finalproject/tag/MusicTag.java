package com.shinkarev.finalproject.tag;

import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class MusicTag extends TagSupport {
    private static final String MUSIC_TAG = "Pavel Shinkarev: final project";
    private static final long serialVersionID = 1L;

    public int doStartTag() {
        try {
            pageContext.getOut().print(MUSIC_TAG);
        } catch (IOException e) {
            System.out.println("fuck"); // todo
        }
        return SKIP_BODY;
    }
}
