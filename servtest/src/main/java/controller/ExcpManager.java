package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The class to generate http error messages.
 */
public class ExcpManager {

    public static ResponseEntity<String> getBadRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Use \"?nameFilter=\" + REGEX filter, or select all");
    }
    public static ResponseEntity<String> getNotFound() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}