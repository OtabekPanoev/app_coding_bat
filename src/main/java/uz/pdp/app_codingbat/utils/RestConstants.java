package uz.pdp.app_codingbat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestConstants {

    ObjectMapper objectMapper = new ObjectMapper();

    String BEARER_TOKEN = "Bearer ";

    String AUTHENTICATION_HEADER = "Authorization";
}
