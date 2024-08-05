package net.arielhernandez.anfp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
}