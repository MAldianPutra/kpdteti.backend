package edu.kpdteti.backend.model.response.publication;

import edu.kpdteti.backend.entity.dto.AuthorDto;
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
public class SearchPublicationResponse {

    private String publicationId;
    private String publicationTitle;
    private List<AuthorDto> authorDto;
    private List<TopicDto> topicDto;

}
