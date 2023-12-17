package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "vote_session", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private long topicsId;
    private Date startDate;
    private Date endDate;
}
