package com.coop.votingsystem.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class ResultoResponseDTO {
    private Long topicId;
    private Long totalVote;
    private Long votesYes;
    private Long votesNo;
}
