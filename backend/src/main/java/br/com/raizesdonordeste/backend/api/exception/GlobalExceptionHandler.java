package br.com.raizesdonordeste.backend.api.exception;

import br.com.raizesdonordeste.backend.domain.exception.RecursoNaoEncontradoException;
import br.com.raizesdonordeste.backend.domain.exception.RegraNegocioException;
import br.com.raizesdonordeste.backend.domain.exception.ValidacaoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
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

    // 422: dados de entrada invalidos (campo obrigatorio ausente, canal invalido, quantidade negativa...)
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ErroPadraoResponse> handleValidacao(ValidacaoException ex, HttpServletRequest request) {
        ErroPadraoResponse erro = new ErroPadraoResponse(ex.getErrorName(), ex.getMessage(), request.getRequestURI());
        erro.addDetail(ex.getField(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    // 400: JSON malformado ou valor de enum/tipo invalido no corpo da requisicao
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadraoResponse> handleCorpoInvalido(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                "CORPO_INVALIDO",
                "O corpo da requisicao esta malformado ou contem valores invalidos.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // 401: login com credenciais invalidas (lancado dentro do controller de autenticacao)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErroPadraoResponse> handleAutenticacao(AuthenticationException ex, HttpServletRequest request) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                "CREDENCIAIS_INVALIDAS",
                "E-mail ou senha invalidos.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }

    // 403: usuario autenticado, mas sem o perfil exigido (@PreAuthorize)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErroPadraoResponse> handleAcessoNegado(AccessDeniedException ex, HttpServletRequest request) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                "ACESSO_NEGADO",
                "Voce nao tem permissao para executar esta operacao.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
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