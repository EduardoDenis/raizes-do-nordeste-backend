package br.com.raizesdonordeste.backend.api.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ErroPadraoResponse {
    private String error;
    private String message;
    private List<DetalheErro> details = new ArrayList<>();
    private String timestamp;
    private String path;

    public ErroPadraoResponse(String error, String message, String path) {
        this.error = error;
        this.message = message;
        this.timestamp = Instant.now().toString();
        this.path = path;
    }

    public void addDetail(String field, String issue) {
        this.details.add(new DetalheErro(field, issue));
    }

    public String getError() { return error; }
    public String getMessage() { return message; }
    public List<DetalheErro> getDetails() { return details; }
    public String getTimestamp() { return timestamp; }
    public String getPath() { return path; }

    public static class DetalheErro {
        private String field;
        private String issue;

        public DetalheErro(String field, String issue) {
            this.field = field;
            this.issue = issue;
        }

        public String getField() { return field; }
        public String getIssue() { return issue; }
    }
}