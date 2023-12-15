package com.coop.votingsystem.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class TopicsResponseDTO {
    private String title;
    private Date voting_date;
    private String description;

}
