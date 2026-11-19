package br.com.raizesdonordeste.backend.api.auth;

import br.com.raizesdonordeste.backend.api.auth.dto.DadosCadastroUsuarioDTO;
import br.com.raizesdonordeste.backend.api.auth.dto.DadosLoginDTO;
import br.com.raizesdonordeste.backend.api.auth.dto.TokenResponseDTO;
import br.com.raizesdonordeste.backend.domain.usuario.Usuario;
import br.com.raizesdonordeste.backend.infrastructure.security.TokenService;
import br.com.raizesdonordeste.backend.infrastructure.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager manager, TokenService tokenService,
                          UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody DadosLoginDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody DadosCadastroUsuarioDTO dto) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setEmail(dto.getEmail());
        // AQUI ESTÁ O HASH DE SENHA EXIGIDO PELA LGPD DO PDF!
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setPerfil(dto.getPerfil());

        repository.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }
}