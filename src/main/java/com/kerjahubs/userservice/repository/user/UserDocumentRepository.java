package com.kerjahubs.userservice.repository.user;

import com.kerjahubs.userservice.entity.user.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends JpaRepository<UserDocument, String> {
}
