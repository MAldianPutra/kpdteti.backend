package edu.kpdteti.backend.model.response.topicParent;

import edu.kpdteti.backend.model.dto.TopicParentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTopicParentResponse {

    private List<TopicParentDto> topicParentDtos;

}
