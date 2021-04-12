package edu.kpdteti.backend.model.response.topic;

import edu.kpdteti.backend.entity.dto.TopicParentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTopicResponse {

    private String topicId;
    private String topicName;
    private TopicParentDto topicParentDto;

}
