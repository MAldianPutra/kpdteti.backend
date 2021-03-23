package edu.kpdteti.backend.model.response.topic;

import edu.kpdteti.backend.model.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTopicByTopicParentResponse {

    List<TopicDto> topicDtos;

}
