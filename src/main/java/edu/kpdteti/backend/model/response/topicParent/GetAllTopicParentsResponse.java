package edu.kpdteti.backend.model.response.topicParent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTopicParentsResponse {

    private String topicParentId;
    private String topicParentName;
}
