package edu.kpdteti.backend.model.request.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPublicationRequest {

    private Long userId;
    private String publicationTitle;
    private String publicationDate;
    private String publicationPublisher;
    private String publicationDescription;
    private String otherAuthor;
    private List<Long> topicIds;
    private List<Long> authorIds;


}
