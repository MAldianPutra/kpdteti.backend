package edu.kpdteti.backend.model.response.publication;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.dto.AuthorDto;
import edu.kpdteti.backend.entity.dto.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPublicationsByTopicOrParentResponse {

    private String publicationId;
    private String publicationTitle;
    private String publicationDate;
    private String publicationDescription;
    private String publicationPath;
    private List<String> otherAuthors;
    private List<AuthorDto> authorDto;

}
