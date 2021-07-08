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
public class UpdatePublicationRequest {

    private String publicationId;
    private String publicationTitle;
    private String publicationDate;
    private String publicationPublisher;
    private String publicationDescription;
    private List<String> otherAuthors;

}
