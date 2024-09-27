package br.com.vroc.starwars.application.logging;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class KoinLogger {

    private final Logger logger;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private KoinLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static KoinLogger getLogger(Class<?> clazz) {
        return new KoinLogger(clazz);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void info(String message, Object data) {
        addDataToMDC(data);
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Throwable cause) {
        logger.error(message, cause);
    }

    public void error(String message, Throwable cause, Object data) {
        addDataToMDC(data);
        logger.error(message, cause);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void debug(String message, Object data) {
        addDataToMDC(data);
        logger.debug(message);
    }

    private void addDataToMDC(Object data) {
        Map<String, Object> dataMap = objectToMap(data);
        putToData(dataMap);
    }

    private void putToData(Map<String, Object> newData) {
        Map<String, Object> data = getData();
        data.putAll(newData);

        newData.forEach((key, value) -> MDC.put("data" + key, value.toString()));
    }

    private Map<String, Object> getData() {
        String dataJson = MDC.get("data");
        if (dataJson != null) {
            try {
                return objectMapper.readValue(dataJson, new TypeReference<>() {
                });
            } catch (Exception e) {
                logger.error("Error reading data from MDC", e);
            }
        }
        return new HashMap<>();
    }

    private Map<String, Object> objectToMap(Object data) {
        try {
            return objectMapper.convertValue(data, new TypeReference<>() {
            });
        } catch (IllegalArgumentException e) {
            logger.error("Error converting object to map", e);
            return Map.of();
        }
    }

}
