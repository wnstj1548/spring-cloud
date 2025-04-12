package com.example.catalogservice.presentation.controller;

import com.example.catalogservice.application.service.CatalogService;
import com.example.catalogservice.presentation.dto.response.ReadCatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<ReadCatalogResponse>> getAllCatalogs() {
        return ResponseEntity.ok(catalogService.getAllCatalogs());
    }
}
