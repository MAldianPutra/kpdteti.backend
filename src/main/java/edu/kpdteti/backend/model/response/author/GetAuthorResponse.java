package edu.kpdteti.backend.model.response.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAuthorResponse {

    private Long authorId;
    private String authorName;
    private String authorEmail;
    private String authorFaculty;
    private String authorUniversity;

}
