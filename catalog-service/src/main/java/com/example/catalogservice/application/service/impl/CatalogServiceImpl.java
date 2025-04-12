package com.example.catalogservice.application.service.impl;

import com.example.catalogservice.application.service.CatalogService;
import com.example.catalogservice.persistence.repository.JpaCatalogRepository;
import com.example.catalogservice.presentation.dto.response.ReadCatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CatalogServiceImpl implements CatalogService {

    private final JpaCatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ReadCatalogResponse> getAllCatalogs() {
        return catalogRepository.findAll().stream().map(ReadCatalogResponse::fromEntity).toList();
    }
}
