package edu.kpdteti.backend.model.request.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTopicRequest {

    private String topicName;
    private Long topicParentId;

}
