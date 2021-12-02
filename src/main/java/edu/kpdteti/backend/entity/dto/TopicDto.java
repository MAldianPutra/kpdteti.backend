package edu.kpdteti.backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private String topicId;
    private String topicParentId;
    private String topicName;
    private Integer topicLabel;

}
