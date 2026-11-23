package br.com.raizesdonordeste.backend.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

/**
 * Devolve 401 (sem token / token invalido) e 403 (sem permissao) no mesmo JSON padrao do restante da API,
 * pois essas falhas ocorrem na cadeia de filtros, antes de chegar ao GlobalExceptionHandler.
 */
@Component
public class RespostaErroSeguranca implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        escrever(request, response, HttpServletResponse.SC_UNAUTHORIZED, "NAO_AUTENTICADO",
                "Token ausente, invalido ou expirado. Faca login em /auth/login.");
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        escrever(request, response, HttpServletResponse.SC_FORBIDDEN, "ACESSO_NEGADO",
                "Voce nao tem permissao para executar esta operacao.");
    }

    private void escrever(HttpServletRequest request, HttpServletResponse response,
                          int status, String error, String message) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String json = "{\"error\":\"" + error + "\","
                + "\"message\":\"" + message + "\","
                + "\"details\":[],"
                + "\"timestamp\":\"" + Instant.now() + "\","
                + "\"path\":\"" + escapar(request.getRequestURI()) + "\"}";
        response.getWriter().write(json);
    }

    private String escapar(String valor) {
        return valor == null ? "" : valor.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
