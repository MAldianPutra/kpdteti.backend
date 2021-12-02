package edu.kpdteti.backend.model.request.publication;

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
    private String publicationAbstract;

    private String publicationKeyword;
    private String publicationPublisher;
    private String publicationDate;
    private List<String> otherAuthors;
    private List<String> authorIds;

}
