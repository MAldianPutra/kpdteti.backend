package edu.kpdteti.backend.model.response.admin;

import edu.kpdteti.backend.entity.TopicParent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTopicResponse {

    private Long topicId;
    private String topicName;
    private LocalDateTime topicCreatedAt;
    private LocalDateTime topicLastUpdated;
    private TopicParent topicParent;

}
