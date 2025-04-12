package com.example.catalogservice.persistence.repository;

import com.example.catalogservice.persistence.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCatalogRepository extends JpaRepository<Catalog, Long> {
}
