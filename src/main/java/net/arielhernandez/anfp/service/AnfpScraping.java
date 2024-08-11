package net.arielhernandez.anfp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class AnfpScraping {

    public List<Map<String, String>> campeonatoPrimera() {
        String url = "https://campeonatochileno.cl/estadisticas/campeonato-itau";
        List<Map<String, String>> allData = new ArrayList<>();
        List<String> headers = List.of("Pos", "Club", "PTS", "PJ", "PG", "PE", "PP", "GF", "GC", "DIF");
        
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select("table").first();
            
            if (table != null) {
                for (Element row : table.select("tr")) {
                    List<Element> cells = row.select("td");
                    if (cells.size() >= headers.size()) {
                        Map<String, String> rowData = new LinkedHashMap<>();
                        for (int i = 0; i < headers.size(); i++) {
                            rowData.put(headers.get(i), cells.get(i).text());
                        }
                        allData.add(rowData);
                    }
                }
            } else {
                System.out.println("No se encontró la tabla en la página web.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return allData;
    }

    public List<Map<String, String>> programacionPrimeraDivision(int numeroFecha) {
        List<Map<String, String>> listaPartidos = new ArrayList<>();
        String baseUrl = "https://campeonatochileno.cl/programacion/";
        String url = baseUrl + numeroFecha + "/campeonato-itau";
    
        try {
            Document doc = Jsoup.connect(url).get();
            Elements dateElements = doc.select(".accordion__pane > div");
            Elements matchElements = doc.select("li[id^=fixture_]");
    
            int dateIndex = 0;
            String currentDate = "";
    
            for (Element matchElement : matchElements) {
                while (dateIndex < dateElements.size()) {
                    Element dateElement = dateElements.get(dateIndex);
                    if (matchElement.previousElementSibling() != null && matchElement.previousElementSibling().equals(dateElement)) {
                        currentDate = dateElement.text().trim();
                        dateIndex++;
                        break;
                    } else if (matchElement.previousElementSibling() == null) {
                        dateIndex++;
                        break;
                    }
                    dateIndex++;
                }
    
                Map<String, String> partido = new HashMap<>();
                partido.put("fecha", currentDate);
                partido.put("equipoLocal", matchElement.select("span.font-base.text-right").text().trim());
                partido.put("equipoVisitante", matchElement.select("span.font-base.text-left").text().trim());
                partido.put("hora", matchElement.select("span.text-center.w-16 span.text-xs").text().trim());
                partido.put("estadio", matchElement.select("span.w-64.ml-5 span.my-auto.w-64").text().trim());
                partido.put("logoEquipoLocal", matchElement.select("a.intro-y:first-of-type img").attr("src"));
                partido.put("logoEquipoVisitante", matchElement.select("a.intro-y:last-of-type img").attr("src"));
                listaPartidos.add(partido);
            }
    
            return listaPartidos;
    
        } catch (IOException e) {
            List<Map<String, String>> error = new ArrayList<>();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", "Error al conectar con la página: " + e.getMessage());
            error.add(errorMap);
            return error;
        }
    }

}