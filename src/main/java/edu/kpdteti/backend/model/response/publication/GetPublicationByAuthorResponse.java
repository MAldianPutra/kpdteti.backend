package edu.kpdteti.backend.model.response.publication;

import edu.kpdteti.backend.model.dto.PublicationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPublicationByAuthorResponse {

    private List<PublicationDto> publicationDtos;

}
