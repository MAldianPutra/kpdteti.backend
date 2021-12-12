package edu.kpdteti.backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationReportDto {

    private String concatText;
    private String symbolRemovedText;
    private String lemmatizedText;

}
