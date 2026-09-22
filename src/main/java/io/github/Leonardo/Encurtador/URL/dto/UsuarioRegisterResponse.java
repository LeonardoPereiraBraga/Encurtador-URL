package io.github.Leonardo.Encurtador.URL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegisterResponse {
    private Long id;
    private String email;
    private String senha;
}
