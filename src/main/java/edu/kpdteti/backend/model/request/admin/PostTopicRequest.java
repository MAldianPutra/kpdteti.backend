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
public class PostTopicRequest {

    @NotBlank
    private String topicName;

    @NotBlank
    private String topicParentId;

}
