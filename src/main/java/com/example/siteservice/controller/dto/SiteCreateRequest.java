package com.example.siteservice.controller.dto;

import com.example.siteservice.entity.Site;

public record SiteCreateRequest(String domain, String alias) {
    public Site toEntity() {
        return new Site(null, domain, alias);
    }
}
