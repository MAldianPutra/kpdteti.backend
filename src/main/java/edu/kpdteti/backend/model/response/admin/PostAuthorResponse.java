package edu.kpdteti.backend.model.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAuthorResponse {

    private String authorId;
    private String authorName;
    private String authorEmail;
    private String authorFaculty;
    private String authorUniversity;

}
