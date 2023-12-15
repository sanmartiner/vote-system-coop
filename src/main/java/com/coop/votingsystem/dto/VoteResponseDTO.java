package com.coop.votingsystem.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class VoteResponseDTO {
    private String associateId;
    private String vote;

}
