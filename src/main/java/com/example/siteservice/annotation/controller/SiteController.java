package com.example.siteservice.annotation.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.siteservice.entity.Site;
import com.example.siteservice.entity.SiteRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = { "/ann/sites" })
public class SiteController {
    private final SiteRepository siteRepository;
    public SiteController(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Site>> view(@PathVariable(required = true) Long id) {
        return siteRepository.findById(id)
            .map(site -> {
                if(site.isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok().body(site.get());
            });
    }

    @GetMapping()
    public Mono<ResponseEntity<Set<Site>>> list() {
        return siteRepository.findAll()
            .map(ResponseEntity.ok()::body);
    }
}
