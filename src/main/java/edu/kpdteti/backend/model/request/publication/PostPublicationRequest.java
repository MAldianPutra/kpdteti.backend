package edu.kpdteti.backend.model.request.publication;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Topic;
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

    private String userId;
    private String publicationTitle;
    private String publicationDate;
    private String publicationPublisher;
    private String publicationDescription;
    private List<String> otherAuthors;
    private List<Author> authors;
    private List<Topic> topics;
    private Boolean savePDF;


}
