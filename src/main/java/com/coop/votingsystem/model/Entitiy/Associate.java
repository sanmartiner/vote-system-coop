package com.coop.votingsystem.model.Entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private long id;
    private String name;
    private String email;
}
