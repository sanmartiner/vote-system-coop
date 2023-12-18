package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voting_session", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "topics_id", referencedColumnName = "id" ),
            @JoinColumn(name = "topics_title", referencedColumnName = "title")
    })
    private Topics topicsId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
