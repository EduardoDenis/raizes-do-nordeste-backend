package br.com.raizesdonordeste.backend.domain.exception;

public class RegraNegocioException extends RuntimeException {
    private final String errorName;

    public RegraNegocioException(String errorName, String message) {
        super(message);
        this.errorName = errorName;
    }

    public String getErrorName() { return errorName; }
}