package edu.kpdteti.backend.model.request.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAuthorRequest {

    private String authorName;
    private String authorEmail;
    private String authorFaculty;
    private String authorUniversity;

}
