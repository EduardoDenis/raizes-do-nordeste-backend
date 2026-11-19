package br.com.raizesdonordeste.backend.api.exception;

import br.com.raizesdonordeste.backend.domain.exception.RecursoNaoEncontradoException;
import br.com.raizesdonordeste.backend.domain.exception.RegraNegocioException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Apanha o erro 404 (ID não encontrado)
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadraoResponse> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                "NAO_ENCONTRADO",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Apanha o erro 409 (Regras de negócio como Estoque vazio)
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroPadraoResponse> handleRegraNegocio(RegraNegocioException ex, HttpServletRequest request) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                ex.getErrorName(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // Apanha qualquer outro erro inesperado (500) para não expor rastreios ao cliente
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoResponse> handleErroGenerico(Exception ex, HttpServletRequest request) {

        ex.printStackTrace();

        ErroPadraoResponse erro = new ErroPadraoResponse(
                "ERRO_INTERNO_SERVIDOR",
                "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}