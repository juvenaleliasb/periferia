package com.secuenciaDNA.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity
@ToString
@Table(name = "adn", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dna"})
})
public class Adn {

    @Id
    @Column(nullable = false, name = "dna")
    private String dna;

    private boolean mutant;
}
