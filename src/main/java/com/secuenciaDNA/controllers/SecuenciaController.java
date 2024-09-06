package com.secuenciaDNA.controllers;

import com.secuenciaDNA.dto.DnaDTO;
import com.secuenciaDNA.models.Adn;
import com.secuenciaDNA.services.SecuenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping()
public class SecuenciaController {

    private final SecuenciaService secuenciaService;

    @Operation(summary = "Almacenamiento de ADN analizado",description = "This service add a new DNA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "200 response", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Long.class))))})
    @PostMapping("/mutants/mutant")
    public ResponseEntity<?> checkMutants(@RequestBody final DnaDTO dnaDTO){
        if (secuenciaService.saveDNA(dnaDTO)) {
            return ResponseEntity.ok(Boolean.TRUE);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    /*public Mono<ResponseEntity<?>> checkMutants(@RequestBody final DnaDTO dnaDTO) {
        return secuenciaService.saveDNA(dnaDTO)
                .map(saved -> {
                    if (saved) {
                        return ResponseEntity.ok(Boolean.TRUE);
                    } else {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                });
    }*/

    @Operation(summary = "Datos estadísticos", description = "Población analizada", tags = { "store" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Adn.class))),
            @ApiResponse(responseCode = "405", description = "Invalid input", content = @Content)
    })
    @GetMapping("/mutants/stats")
    public ResponseEntity<?> getDnaStats(){
        return ResponseEntity.ok(secuenciaService.getAdnStats());
    }
}
