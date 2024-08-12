package net.arielhernandez.anfp.components;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpsEnforcerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getScheme().equals("http")) {
            String httpsURL = "https://" + httpRequest.getServerName() + httpRequest.getRequestURI();
            if (httpRequest.getQueryString() != null) {
                httpsURL += "?" + httpRequest.getQueryString();
            }
            httpResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            httpResponse.setHeader("Location", httpsURL);
        } else {
            chain.doFilter(request, response);
        }
    }
}
