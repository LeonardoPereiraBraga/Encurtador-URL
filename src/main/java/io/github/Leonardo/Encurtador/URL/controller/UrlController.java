package io.github.Leonardo.Encurtador.URL.controller;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.dto.UrlPostResponse;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.github.Leonardo.Encurtador.URL.service.RedirectService;
import io.github.Leonardo.Encurtador.URL.service.UrlService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    private final RedirectService redirectService;

    @PostMapping
    public ResponseEntity<UrlPostResponse> criarUrl(@Valid @RequestBody UrlPostRequest postRequest){
        UrlPostResponse urlEncurtada = urlService.criarUrlEncurtada(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlEncurtada);
    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirecionarParaUrlOriginal(@PathVariable String shortCode){

        String urlOriginal = redirectService.processarRedirect(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlOriginal)).build();

    }
}
