package com.shinkarev.finalproject.command;

/**
 * The {@link Router} class contains two fields:
 * pagePath
 * routeType
 * that are used with by controller to find out where and how
 * request and response should be processed after the controller.
 */

public class Router {
    public enum RouterType {
        FORWARD, REDIRECT
    }

    private String pagePath = PageName.INDEX_PAGE;
    private RouterType routerType = RouterType.FORWARD;
    private Integer errorCode = null;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public boolean hasError() {
        return errorCode != null;
    }
}
