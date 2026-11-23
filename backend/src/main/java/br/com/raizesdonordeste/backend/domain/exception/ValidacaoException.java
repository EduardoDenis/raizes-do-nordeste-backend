package br.com.raizesdonordeste.backend.domain.exception;

/** Erro de validacao dos dados de entrada (resulta em HTTP 422). */
public class ValidacaoException extends RuntimeException {
    private final String errorName;
    private final String field;

    public ValidacaoException(String errorName, String field, String message) {
        super(message);
        this.errorName = errorName;
        this.field = field;
    }

    public String getErrorName() { return errorName; }
    public String getField() { return field; }
}
