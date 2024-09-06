package com.secuenciaDNA.services;

import com.secuenciaDNA.dto.DnaDTO;
import com.secuenciaDNA.models.Stats;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface SecuenciaService {
    public boolean saveDNA(DnaDTO dna);
    //public Mono<Boolean> saveDNA(DnaDTO dna);

    public Optional<Stats> getAdnStats();
}
