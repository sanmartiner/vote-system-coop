package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "topics", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Topics {

    @EmbeddedId
    private TopicsEntityId id;

    @Column(name = "description")
    private String description;

    @Column(name="voting_date")
    private Date votingDate;

}



