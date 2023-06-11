package fr.eitelalaric.gestiondestock.exception;

import lombok.Data;

/**
 * @author eitel.tchuenkam
 */

@Data
public class InvalidOperationException extends RuntimeException {
    private ErrorCodes errorCodes;

    public InvalidOperationException(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }
    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, ErrorCodes errorCodes) {
        super(message);
        this.errorCodes = errorCodes;
    }

    public InvalidOperationException(String message, Throwable cause, ErrorCodes errorCodes) {
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public InvalidOperationException(Throwable cause, ErrorCodes errorCodes) {
        super(cause);
        this.errorCodes = errorCodes;
    }

    public InvalidOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCodes errorCodes) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCodes = errorCodes;
    }
}
