package com.coop.votingsystem.model.Entitiy;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vote", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long associateId;
    private Boolean vote;
}
