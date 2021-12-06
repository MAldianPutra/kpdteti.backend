package edu.kpdteti.backend.model.request.publication;

import edu.kpdteti.backend.enums.SearchTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchPublicationRequest {

    String searchKey;
    SearchTypeEnum searchTypeEnum;

}
