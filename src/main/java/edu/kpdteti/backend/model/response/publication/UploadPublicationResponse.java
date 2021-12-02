package edu.kpdteti.backend.model.response.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadPublicationResponse {

    private String message;
    private String publicationId;
    private String publicationPath;

}
