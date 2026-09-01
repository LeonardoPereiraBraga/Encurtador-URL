package io.github.Leonardo.Encurtador.URL.controller;

import io.github.Leonardo.Encurtador.URL.dto.UrlPostRequest;
import io.github.Leonardo.Encurtador.URL.dto.UrlPostResponse;
import io.github.Leonardo.Encurtador.URL.entities.Url;
import io.github.Leonardo.Encurtador.URL.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlPostResponse> criarUrl(@Valid @RequestBody UrlPostRequest postRequest){
        UrlPostResponse urlEncurtada = urlService.criarUrlEncurtada(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlEncurtada);
    }
}
