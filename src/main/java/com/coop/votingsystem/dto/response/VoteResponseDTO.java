package com.coop.votingsystem.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class VoteResponseDTO {
    private Long associateId;
    private Long topicId;
    private String topicTitle;
    private String vote;

}
