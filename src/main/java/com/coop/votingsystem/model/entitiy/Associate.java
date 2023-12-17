package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "associate", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
}
