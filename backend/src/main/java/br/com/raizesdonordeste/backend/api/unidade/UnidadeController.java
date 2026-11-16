package br.com.raizesdonordeste.backend.api.unidade;

import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.application.unidade.CriarUnidadeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final CriarUnidadeUseCase criarUnidadeUseCase;

    public UnidadeController(CriarUnidadeUseCase criarUnidadeUseCase) {
        this.criarUnidadeUseCase = criarUnidadeUseCase;
    }

    @PostMapping
    public ResponseEntity<Unidade> criarUnidade(@RequestBody Unidade unidade) {
        Unidade unidadeSalva = criarUnidadeUseCase.executar(unidade);
        return ResponseEntity.ok(unidadeSalva);
    }
}