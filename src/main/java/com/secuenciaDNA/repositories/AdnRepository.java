package com.secuenciaDNA.repositories;

import com.secuenciaDNA.models.Adn;
import com.secuenciaDNA.models.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdnRepository  extends JpaRepository<Adn, Long> {

    @Query(value = "SELECT " +
            "COUNT(*) AS checks, " +
            "SUM(CASE WHEN mutant = TRUE THEN 1 ELSE 0 END) AS mutants, " +
            "(SUM(CASE WHEN mutant = TRUE THEN 1 ELSE 0 END) / COUNT(*)) AS percent " +
            "FROM adn",
            nativeQuery = true)

    public Optional<Stats> getAdnStats();

}
