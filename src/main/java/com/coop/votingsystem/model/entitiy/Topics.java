package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.*;
import lombok.*;

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

    private String description;

}



