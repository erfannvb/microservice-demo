package nvb.dev.accounts.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDto(String apiPath,
                               HttpStatus statusCode,
                               String errorMessage,
                               LocalDateTime timestamp) {
}
