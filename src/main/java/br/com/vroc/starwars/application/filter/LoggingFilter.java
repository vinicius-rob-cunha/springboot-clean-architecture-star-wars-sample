package br.com.vroc.starwars.application.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {

    private static final String TRACKING_ID_HEADER = "trackingId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String trackingId = httpRequest.getHeader(TRACKING_ID_HEADER);

            if (trackingId == null || trackingId.isEmpty()) {
                trackingId = UUID.randomUUID().toString();
            }

            MDC.put(TRACKING_ID_HEADER, trackingId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

}