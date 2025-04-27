package com.example.userservice.commons.exception;

import com.example.userservice.commons.exception.payload.ErrorStatus;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodName, Response response) {

        String message = extractBody(response);

        switch(response.status()) {
            case 400 :
                break;
            case 404 :
                if(methodName.contains("getByUserId")) {
                    return new ApplicationException(
                            ErrorStatus.toErrorStatus(message, 404, LocalDateTime.now())
                    );
                }
                break;
            default :
                return new ApplicationException(
                        ErrorStatus.toErrorStatus(message, response.status(), LocalDateTime.now())
                );
        }

        return null;
    }

    private String extractBody(Response response) {
        if (response.body() == null) return "No content";

        try (Scanner scanner = new Scanner(response.body().asInputStream(), StandardCharsets.UTF_8.name())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "No content";
        } catch (IOException e) {
            return "Failed to read error body: " + e.getMessage();
        }
    }
}
