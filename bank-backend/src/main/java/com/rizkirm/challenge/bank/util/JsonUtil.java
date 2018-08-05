package com.rizkirm.challenge.bank.util;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class JsonUtil {

    public static <T> ResponseEntity<T> getJsonResponse(T src) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<T>(src, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> getJsonResponse(T src, HttpStatus status) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<T>(src, headers, status);
    }

    public static <T> ResponseEntity<T> getJsonSHttptatus(HttpStatus status) {

        return new ResponseEntity<T>(status);

    }

    public static <T> ResponseEntity<List<T>> defaultJsonResponse(List<T> src) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<List<T>>(src, headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> defaultJsonResponse(Object src) {

        Gson gson = new Gson();

        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<String>(gson.toJson(src), headers, HttpStatus.OK);
    }

    public static <T> T jsonToObject(String strJson, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(strJson, type);
    }

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> ResponseEntity<T> getJsonResponse(T src, HttpStatus status, Map<String, String> mapHeaderMessage) {

        HttpHeaders headers = new HttpHeaders();

        if (null != mapHeaderMessage) {
            for (String key : mapHeaderMessage.keySet()) {
                headers.add(key, mapHeaderMessage.get(key));
            }
        }

        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<T>(src, headers, status);
    }

    public static Map<String, Object> constructMapReturn(Collection voList, long totalElements, int totalPages) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(ConstantUtil.PageParameter.LIST_DATA, voList);
        map.put(ConstantUtil.PageParameter.TOTAL_ELEMENTS, totalElements);
        map.put(ConstantUtil.PageParameter.TOTAL_PAGES, totalPages);

        return map;
    }

}
