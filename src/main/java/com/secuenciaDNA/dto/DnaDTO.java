package com.secuenciaDNA.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DnaDTO {

    @JsonProperty("dna")
    private String[] dna;
}
