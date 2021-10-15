package edu.kpdteti.backend.model.request.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublicationRequest {

    @NotBlank
    private String publicationId;

    @NotBlank
    private String publicationTitle;

    @NotBlank
    private String publicationDate;

    @NotBlank
    private String publicationPublisher;

    @NotBlank
    private String publicationDescription;

    private List<String> otherAuthors;

}
