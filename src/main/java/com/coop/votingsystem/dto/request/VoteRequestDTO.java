package com.coop.votingsystem.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class VoteRequestDTO {
    private Long topicsId;
    private Long associateID;
    private String vote;
}
