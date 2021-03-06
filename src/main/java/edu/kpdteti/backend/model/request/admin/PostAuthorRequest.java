package edu.kpdteti.backend.model.request.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAuthorRequest {

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;

    @NotBlank
    private String authorFaculty;

    @NotBlank
    private String authorUniversity;

}
