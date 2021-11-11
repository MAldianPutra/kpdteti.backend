package edu.kpdteti.backend.model.request.publication;

import edu.kpdteti.backend.entity.Author;
import edu.kpdteti.backend.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPublicationRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String publicationTitle;

    @NotBlank
    private String publicationDate;

    @NotBlank
    private String publicationPublisher;

    @NotBlank
    private String publicationDescription;

    private List<String> otherAuthors;
    private List<String> authorIds;
    private List<String> topicIds;

    @NotNull
    private Boolean savePDF;

}
