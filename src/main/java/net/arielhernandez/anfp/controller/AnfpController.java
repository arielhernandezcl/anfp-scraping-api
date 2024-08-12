package net.arielhernandez.anfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.arielhernandez.anfp.service.AnfpScraping;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/anfp")
public class AnfpController {

    @Autowired
    private AnfpScraping anfpScrapingService;

    @GetMapping("/estadisticas/campeonato-itau")
    public List<Map<String, String>> scrapeData() {
        return anfpScrapingService.campeonatoPrimera();
    }

    @GetMapping("/programacion/{numeroFecha}")
    public ResponseEntity<List<Map<String, String>>> programacionPrimeraDivision(@PathVariable int numeroFecha) {
        try {
            List<Map<String, String>> programacion = anfpScrapingService.programacionPrimeraDivision(numeroFecha);
            return ResponseEntity.ok(programacion);
        } catch (Exception e) {
            List<Map<String, String>> error = List.of(Map.of("error", e.getMessage()));
            return ResponseEntity.internalServerError().body(error);
        }
    }
}