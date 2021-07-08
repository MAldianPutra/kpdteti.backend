package edu.kpdteti.backend.enums;

public enum IdGeneratorEnum {

    USER("usr-"),
    ADMIN("adm-"),
    AUTHOR("aut-"),
    PUBLICATION("pub-"),
    TOPIC("top-"),
    TOPIC_PARENT("par-");

    private String idGeneratorEnum;

    IdGeneratorEnum(String idGeneratorEnum) {
        this.idGeneratorEnum = idGeneratorEnum;
    }

    public String getIdGeneratorEnum() {
        return idGeneratorEnum;
    }
}
