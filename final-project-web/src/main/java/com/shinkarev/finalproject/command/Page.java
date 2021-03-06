package com.shinkarev.finalproject.command;

public class Page {
    private final int totalCount;
    private final int currentPage;
    private final int pageSize;

    public Page(int totalCount, int currentPage, int pageSize) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isFirstPage() {
        return currentPage == 1;
    }

    public boolean isLastPage() {
        return currentPage == pageCount();
    }

    public int pageCount() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}
