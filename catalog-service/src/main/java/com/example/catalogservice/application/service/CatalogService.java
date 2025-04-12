package com.example.catalogservice.application.service;

import com.example.catalogservice.presentation.dto.response.ReadCatalogResponse;

import java.util.List;

public interface CatalogService {
    List<ReadCatalogResponse> getAllCatalogs();
}
