package br.com.raizesdonordeste.backend.api.unidade;

import br.com.raizesdonordeste.backend.domain.unidade.Unidade;
import br.com.raizesdonordeste.backend.application.unidade.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final CriarUnidadeUseCase criarUnidadeUseCase;
    private final ListarUnidadesUseCase listarUnidadesUseCase;
    private final BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase;
    private final AtualizarUnidadeUseCase atualizarUnidadeUseCase;
    private final DeletarUnidadeUseCase deletarUnidadeUseCase;

    public UnidadeController(CriarUnidadeUseCase criarUnidadeUseCase,
                             ListarUnidadesUseCase listarUnidadesUseCase,
                             BuscarUnidadePorIdUseCase buscarUnidadePorIdUseCase,
                             AtualizarUnidadeUseCase atualizarUnidadeUseCase,
                             DeletarUnidadeUseCase deletarUnidadeUseCase) {
        this.criarUnidadeUseCase = criarUnidadeUseCase;
        this.listarUnidadesUseCase = listarUnidadesUseCase;
        this.buscarUnidadePorIdUseCase = buscarUnidadePorIdUseCase;
        this.atualizarUnidadeUseCase = atualizarUnidadeUseCase;
        this.deletarUnidadeUseCase = deletarUnidadeUseCase;
    }

    @PostMapping
    public ResponseEntity<Unidade> criarUnidade(@RequestBody Unidade unidade) {
        return ResponseEntity.ok(criarUnidadeUseCase.executar(unidade));
    }

    @GetMapping
    public ResponseEntity<Iterable<Unidade>> listarUnidades() {
        return ResponseEntity.ok(listarUnidadesUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> buscarUnidadePorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarUnidadePorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> atualizarUnidade(@PathVariable Long id, @RequestBody Unidade unidade) {
        return ResponseEntity.ok(atualizarUnidadeUseCase.executar(id, unidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable Long id) {
        deletarUnidadeUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}