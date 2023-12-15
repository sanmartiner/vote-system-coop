package com.coop.votingsystem.model.Entitiy;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Description;

import java.util.Date;

@Entity
@Table(name = "topics", schema = "votingsystem")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TopicsEntityId id;
    private String description;

}



