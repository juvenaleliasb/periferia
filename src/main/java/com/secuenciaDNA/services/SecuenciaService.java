package com.secuenciaDNA.services;

import com.secuenciaDNA.dto.DnaDTO;
import com.secuenciaDNA.models.Stats;

import java.util.Optional;

public interface SecuenciaService {
    public boolean saveDNA(DnaDTO dna);

    public Optional<Stats> getAdnStats();
}
