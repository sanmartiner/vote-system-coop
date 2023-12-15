package com.coop.votingsystem.model.Entitiy;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TopicsEntityId implements Serializable {
    private String title;
    private Date voting_date;
}
