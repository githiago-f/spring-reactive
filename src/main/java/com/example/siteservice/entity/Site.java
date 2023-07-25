package com.example.siteservice.entity;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Site {
    private Long id;
    private String domain, alias;
    @Override
    public int hashCode() {
        return Objects.hash(id, domain, alias);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Site other = (Site) obj;
        return Objects.equals(id, other.id) || Objects.equals(domain, other.domain)
                || Objects.equals(alias, other.alias);
    }
    @Override
    public String toString() {
        return "Site(id=" + id + ")";
    }
}
