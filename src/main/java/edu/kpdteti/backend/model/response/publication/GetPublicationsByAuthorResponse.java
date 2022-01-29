package edu.kpdteti.backend.model.response.publication;

import edu.kpdteti.backend.entity.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPublicationsByAuthorResponse {

    private String publicationId;
    private String publicationTitle;
    private String publicationDate;
    private String publicationPublisher;
    private String publicationAbstract;
    private String publicationKeyword;
    private List<String> otherAuthors;
    private List<TopicDto> topicDto;
    private Integer numberOfPage;

}
