package edu.kpdteti.backend.model.response.topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTopicsByParentResponse {

    private String topicId;
    private String topicName;
    private Integer topicLabel;

}
