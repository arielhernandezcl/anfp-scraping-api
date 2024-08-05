package net.arielhernandez.anfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.arielhernandez.anfp.service.AnfpScraping;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estadisticas")
public class AnfpController {

    @Autowired
    private AnfpScraping anfpScrapingService;

    @GetMapping("/campeonato-itau")
    public List<Map<String, String>> scrapeData() {
        return anfpScrapingService.campeonatoPrimera();
    }
}