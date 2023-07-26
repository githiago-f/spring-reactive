package com.example.siteservice.entity;

import java.util.Set;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Optional;

@Component
public class SiteRepository {
    private Long seq_id = 0L;
    private Set<Site> sites = new HashSet<>();

    
    public SiteRepository() {
        for(int i = 0; i < 10; i++) {
            save(Site.builder().domain("test.com").alias("test").build());
        }
    }
    
    public Mono<Site> save(Site site) {
        site.setId(++seq_id);
        sites.add(site);
        return Mono.just(site);
    }

    public Mono<Site> findById(Long id) {
        Optional<Site> site = sites.stream()
            .filter(i -> i.getId().equals(id))
            .findFirst();
        return Mono.justOrEmpty(site);
    }

    public Mono<Set<Site>> findAll() {
        return Mono.just(sites);
    }
}
