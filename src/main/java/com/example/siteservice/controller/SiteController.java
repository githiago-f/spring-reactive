package com.example.siteservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.siteservice.controller.dto.SiteCreateRequest;
import com.example.siteservice.controller.dto.SiteUpdateRequest;
import com.example.siteservice.entity.Site;
import com.example.siteservice.entity.SiteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = { "/sites" })
public class SiteController {
    private final SiteRepository siteRepository;

    public SiteController(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Site>> view(@PathVariable Integer id) {
        return siteRepository.findById(id)
            .map(ResponseEntity.ok()::body)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public Flux<Site> list(@RequestParam String alias) {
        return siteRepository.findByAlias(alias);
    }

    @PostMapping()
    public ResponseEntity<Mono<Site>> create(@RequestBody SiteCreateRequest body) {
        Mono<Site> site = siteRepository.save(body.toEntity());
        return ResponseEntity.status(201).body(site);
    }

    @PatchMapping()
    public Mono<ResponseEntity<Site>> update(@RequestBody SiteUpdateRequest body) {
        if(body.alias().isEmpty() || body.alias().isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        return siteRepository.findById(body.id())
            .flatMap(site -> Mono.just(site.withAlias(body.alias())))
            .flatMap(siteRepository::save)
            .map(ResponseEntity.ok()::body)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
