package uz.pdp.app_codingbat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;


public class CommonUtils {
    public static Base64.Decoder getBase64Decoder = Base64.getDecoder();
    public static ObjectMapper objectMapper = new ObjectMapper();



}
