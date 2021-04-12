package edu.kpdteti.backend.model.response.admin;

import edu.kpdteti.backend.entity.dto.TopicParentDto;
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

    private String topicId;
    private String topicName;
    private TopicParentDto topicParentDto;

}
