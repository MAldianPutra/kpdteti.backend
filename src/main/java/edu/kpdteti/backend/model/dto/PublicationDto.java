package edu.kpdteti.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {

    private Long publicationId;
    private String publicationTitle;
    private String publicationPublisher;
    private String publicationDescription;
    private String otherAuthor;
    private List<AuthorDto> authorDtos;

}
