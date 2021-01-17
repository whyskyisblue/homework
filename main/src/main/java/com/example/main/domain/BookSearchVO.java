package com.example.main.domain;

public class BookSearchVO {

    private Integer page;

    private String field;

    private Boolean asc;

    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Boolean getAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String concatQs(String url) {
        String qs = "page=" + (page == null ? "" : page) +
                "&field=" + (field == null ? "" : field) +
                "&asc=" + (asc == null ? "" : asc) +
                "&keyword=" + (keyword == null ? "" : keyword);

        if (url.contains("?"))
            return url + "&" + qs;
        else
            return url + "?" + qs;
    }

}
