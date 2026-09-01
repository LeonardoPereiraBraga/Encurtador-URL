package io.github.Leonardo.Encurtador.URL.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlPostRequest {

    @NotBlank
    @URL(protocol = "https", message = "Deve ser uma url valida")
    private String websiteUrl;
}
