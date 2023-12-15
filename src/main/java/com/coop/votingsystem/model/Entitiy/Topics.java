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
    private Long id;
    private String title;
    private String description;
    private Date voting_date;
}



