package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topics", schema = "votingsystem")  // Add schema if needed
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Topics {

    @EmbeddedId
    private TopicsEntityId topicsEntityId;

    @Column(name = "description")
    private String description;

    @Column(name = "voting_date")
    private LocalDateTime votingDate;

}


