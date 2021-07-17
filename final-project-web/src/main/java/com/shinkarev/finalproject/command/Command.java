package com.shinkarev.finalproject.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);
}
