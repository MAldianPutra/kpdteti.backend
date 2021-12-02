package edu.kpdteti.backend.model.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PopulateTopicResponse {

    private String topicId;
    private String topicName;
    private Integer topicLabel;

}
