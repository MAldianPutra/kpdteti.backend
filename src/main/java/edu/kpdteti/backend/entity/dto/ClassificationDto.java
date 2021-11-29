package edu.kpdteti.backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationDto {

    // Change this preprocessing later
    private String preprocessing;

    private Map<String, ?> predictProbability;
    private List<Integer> predictResults;

}
