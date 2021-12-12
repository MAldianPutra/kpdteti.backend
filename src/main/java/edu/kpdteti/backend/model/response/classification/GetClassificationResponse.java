package edu.kpdteti.backend.model.response.classification;

import edu.kpdteti.backend.entity.dto.ClassificationReportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetClassificationResponse {

    private Map<String, ?> predictProbability;
    private List<Integer> predictResults;
    private ClassificationReportDto classificationReport;
    private String message;

}
