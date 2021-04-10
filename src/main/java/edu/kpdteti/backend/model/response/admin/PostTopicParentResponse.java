package edu.kpdteti.backend.model.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTopicParentResponse {

    private Long topicParentId;
    private String topicParentName;
    private LocalDateTime topicParentCreatedAt;

}
