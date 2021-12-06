package edu.kpdteti.backend.enums;

public enum SearchTypeEnum {

    TITLE("title"),
    TOPIC("topic"),
    AUTHOR("author");

    private String searchTypeEnum;

    SearchTypeEnum(String searchTypeEnum) {
        this.searchTypeEnum = searchTypeEnum;
    }

    public String getSearchTypeEnum() {
        return searchTypeEnum;
    }
}
