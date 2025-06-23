package com.pageport.pageport.repository;

import com.pageport.pageport.entity.BookEntity;
import com.pageport.pageport.entity.CartEntity;
import com.pageport.pageport.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    List<CartEntity> findByUser_Uid(UUID userId);
    CartEntity findByUserAndBook(UserEntity user, BookEntity book);
}
