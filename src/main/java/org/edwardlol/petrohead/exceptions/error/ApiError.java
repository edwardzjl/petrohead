package org.edwardlol.petrohead.exceptions.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    //----------- instance fields -----------

    private HttpStatus httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<ApiSubError> subErrors;

    //----------- constructors -----------

    private ApiError(Builder builder) {
        this.httpStatus = builder.httpStatus;
        this.timestamp = builder.timestamp;
        this.message = builder.message;
        this.debugMessage = builder.debugMessage;
    }

    //----------- getter / setters -----------

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return this.debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    //----------- builder -----------

    public static Builder buider() {
        return new Builder();
    }

    public static class Builder {

        private HttpStatus httpStatus;
        private final LocalDateTime timestamp;
        private String message;
        private String debugMessage;

        public Builder() {
            this.timestamp = LocalDateTime.now();
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder debugMessage(Throwable ex) {
            this.debugMessage = ex.getLocalizedMessage();
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }

    }
}
