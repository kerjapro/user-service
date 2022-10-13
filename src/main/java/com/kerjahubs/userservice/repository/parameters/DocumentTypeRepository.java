package com.kerjahubs.userservice.repository.parameters;

import com.kerjahubs.userservice.entity.parameter.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, String> {
    @Query(value = "SELECT * FROM document_type dt WHERE dt.name = :name", nativeQuery = true)
    Optional<DocumentType> findIdByName(@Param("name") String name);
}
