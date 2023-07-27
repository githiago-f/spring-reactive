package com.example.siteservice.annotation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.siteservice.entity.Site;
import com.example.siteservice.entity.SiteRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = { "/ann/sites" })
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
    public ResponseEntity<Flux<Site>> list(@RequestParam String domain) {
        return ResponseEntity.ok().body(siteRepository.findByDomain(domain));
    }
}
