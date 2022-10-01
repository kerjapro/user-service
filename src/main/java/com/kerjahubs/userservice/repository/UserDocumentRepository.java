package com.kerjahubs.userservice.repository;

import com.kerjahubs.userservice.entity.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, String> {
}
