package io.github.Leonardo.Encurtador.URL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginResponse {
    private String token;
    private String email;
}
