package io.github.Leonardo.Encurtador.URL.service;

import io.github.Leonardo.Encurtador.URL.dto.UsuarioLoginRequest;
import io.github.Leonardo.Encurtador.URL.dto.UsuarioLoginResponse;
import io.github.Leonardo.Encurtador.URL.dto.UsuarioRegisterRequest;
import io.github.Leonardo.Encurtador.URL.dto.UsuarioRegisterResponse;
import io.github.Leonardo.Encurtador.URL.entities.Usuario;
import io.github.Leonardo.Encurtador.URL.exception.EmailExistenteException;
import io.github.Leonardo.Encurtador.URL.exception.UrlExpiradaException;
import io.github.Leonardo.Encurtador.URL.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UsuarioRegisterResponse registrarUsuario(UsuarioRegisterRequest usuarioRequest){
        if (usuarioRepository.existsUsuarioByEmail(usuarioRequest.getEmail())){
            throw new EmailExistenteException("Email já existente");
        }
        String senhaHash = passwordEncoder.encode(usuarioRequest.getSenha());
        Usuario usuarioParaSalvar = new Usuario(null, usuarioRequest.getEmail(), senhaHash);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioParaSalvar);
        UsuarioRegisterResponse registerResponse = new UsuarioRegisterResponse(usuarioSalvo.getId(), usuarioSalvo.getEmail(), usuarioSalvo.getPassword());
        return registerResponse;
    }
    public UsuarioLoginResponse logarUsuario(UsuarioLoginRequest usuarioRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioRequest.getEmail(), usuarioRequest.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        UsuarioLoginResponse loginResponse = new UsuarioLoginResponse(token, usuarioRequest.getEmail());
        return loginResponse;
    }
}
