package com.coop.votingsystem.model.requestmodel;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
@Value
@ToString
@SuperBuilder
@NoArgsConstructor(force = true)
public class TopicRequestModel {

   String title;
   String description;
   LocalDateTime votingDate;

}
