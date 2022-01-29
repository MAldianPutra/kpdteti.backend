package edu.kpdteti.backend.model.response.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAuthorsNameResponse {

    private String authorId;
    private String authorName;

}
