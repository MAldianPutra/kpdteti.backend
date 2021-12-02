package edu.kpdteti.backend.entity;

import edu.kpdteti.backend.entity.dto.ClassificationReportDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Classification.COLLECTION_NAME)
public class Classification {

    public static final String COLLECTION_NAME = "classification";
    public static final String CLASSIFICATION_PROBABILITY = "predictProbability";
    public static final String CLASSIFICATION_RESULTS = "predictResults";
    public static final String CLASSIFICATION_REPORT = "report";
    public static final String CREATED_AT = "createdAt";
    public static final String LAST_UPDATED = "lastUpdated";

    @Id
    private String classificationId;

    @Field(value = CLASSIFICATION_PROBABILITY)
    private Map<String, ?> predictProbability;

    @Field(value = CLASSIFICATION_RESULTS)
    private List<Integer> predictResults;

    @Field(value = CLASSIFICATION_REPORT)
    private ClassificationReportDto classificationReport;

    @Field(value = CREATED_AT)
    private LocalDateTime classificationCreatedAt;

    @Field(value = LAST_UPDATED)
    private LocalDateTime classificationLastUpdated;

}
