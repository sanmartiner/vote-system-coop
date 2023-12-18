package com.coop.votingsystem.model.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 5, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;


}
