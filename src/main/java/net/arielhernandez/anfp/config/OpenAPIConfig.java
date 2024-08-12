package net.arielhernandez.anfp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${anfp.openapi.dev-url}")
    private String devUrl;
  
    @Value("${anfp.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI AmfpAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Test Production environment");

        Contact contact = new Contact();
        contact.setEmail("hola@arielhernandez.net");
        contact.setName("Ariel Hernandez");
        contact.setUrl("https://www.arielhernandez.net");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
            .title("ANFP Scraping API")
            .version("0.2")
            .contact(contact)
            .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
    
}
