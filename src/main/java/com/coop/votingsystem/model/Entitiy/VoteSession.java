package com.coop.votingsystem.model.Entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "vote_session", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VoteSession {
    @Id
    private long id;
    private long topics_id;
    private Date start_date;
    private Date end_date;
}
