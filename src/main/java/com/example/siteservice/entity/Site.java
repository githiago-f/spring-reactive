package com.example.siteservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.With;

@Table(name = "sites")
public record Site(@Id Integer id, String domain, @With String alias) {}
