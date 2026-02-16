package com.example.projeto_turismo.domains;

import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private int currentPage;
    private long totalElement;
    private int totalPage;

    public PageResponse(List<T> content, int currentPage, long totalElement, int totalPage) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalElement = totalElement;
        this.totalPage = totalPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
