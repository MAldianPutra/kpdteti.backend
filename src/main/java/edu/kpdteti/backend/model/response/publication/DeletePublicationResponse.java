package edu.kpdteti.backend.model.response.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeletePublicationResponse {

    private Long publicationId;
    private String publicationTitle;

}
