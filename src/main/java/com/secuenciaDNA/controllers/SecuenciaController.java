package com.secuenciaDNA.controllers;

import com.secuenciaDNA.dto.DnaDTO;
import com.secuenciaDNA.services.SecuenciaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mutants")//
public class SecuenciaController {

    private final SecuenciaService secuenciaService;

    @PostMapping("/mutant")
    public ResponseEntity<?> checkMutants(@RequestBody final DnaDTO dnaDTO){
        if (secuenciaService.saveDNA(dnaDTO)) {
            return ResponseEntity.ok(Boolean.TRUE);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getDnaStats(){
        return ResponseEntity.ok(secuenciaService.getAdnStats());
    }
}
