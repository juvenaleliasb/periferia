package com.secuenciaDNA.services;

import com.secuenciaDNA.dto.DnaDTO;
import com.secuenciaDNA.models.Adn;
import com.secuenciaDNA.models.Stats;
import com.secuenciaDNA.repositories.AdnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecuenciaServiceImpl implements SecuenciaService{

    private final AdnRepository adnRepository;

    public boolean checkMutants(DnaDTO dna) {
        return hasFourConsecutive(dna.getDna());
    }

    @Override
    public boolean saveDNA(DnaDTO dna) {
    //public Mono<Boolean> saveDNA(DnaDTO dna) {
        boolean isMutant = checkMutants(dna); //hasFourConsecutive(dna);
        Adn adn = Adn.builder()
                .mutant(isMutant)
                .dna(String.join(",", dna.getDna()))
                .build();
        adnRepository.save(adn);

        //return Mono.just(isMutant);
        return isMutant;
    }

    @Override
    public Optional<Stats> getAdnStats() {
        return adnRepository.getAdnStats();
    }

    public static boolean hasFourConsecutive(String[] dna) {
        int n = dna.length;
        char[][] matrix = new char[n][n];

        // Convert the array of strings to a matrix
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        // Check horizontal, vertical and diagonal sequences
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (checkSequence(matrix, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkSequence(char[][] matrix, int row, int col) {
        int n = matrix.length;

        // Check horizontally
        if (col <= n - 4) {
            if (checkDirection(matrix, row, col, 0, 1)) return true;
        }

        // Check vertically
        if (row <= n - 4) {
            if (checkDirection(matrix, row, col, 1, 0)) return true;
        }

        // Check diagonally (down-right)
        if (row <= n - 4 && col <= n - 4) {
            if (checkDirection(matrix, row, col, 1, 1)) return true;
        }

        // Check diagonally (up-right)
        if (row >= 3 && col <= n - 4) {
            if (checkDirection(matrix, row, col, -1, 1)) return true;
        }

        return false;
    }

    private static boolean checkDirection(char[][] matrix, int startRow, int startCol, int rowDir, int colDir) {
        char firstChar = matrix[startRow][startCol];
        for (int i = 1; i < 4; i++) {
            int row = startRow + i * rowDir;
            int col = startCol + i * colDir;
            if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length ||
                    matrix[row][col] != firstChar) {
                return false;
            }
        }
        return true;
    }
}
