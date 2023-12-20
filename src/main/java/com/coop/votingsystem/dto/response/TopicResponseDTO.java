package com.coop.votingsystem.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class TopicResponseDTO {
    private Long id;
    private String title;
    private LocalDateTime votingDate;
    private String description;

}
