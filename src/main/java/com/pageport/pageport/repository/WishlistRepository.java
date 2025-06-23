package com.pageport.pageport.repository;

import com.pageport.pageport.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WishlistRepository extends JpaRepository<WishlistEntity, UUID> {
    List<WishlistEntity> findByUser_Uid(UUID userId);
}
