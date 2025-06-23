package com.pageport.pageport.service;

import com.pageport.pageport.dto.WishlistDto;
import com.pageport.pageport.entity.BookEntity;
import com.pageport.pageport.entity.UserEntity;
import com.pageport.pageport.entity.WishlistEntity;
import com.pageport.pageport.repository.BookRepository;
import com.pageport.pageport.repository.UserRepository;
import com.pageport.pageport.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void addToWishlist(UUID userId, UUID bookId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        BookEntity book = bookRepository.findById(bookId).orElseThrow();
        WishlistEntity item = new WishlistEntity(null, user, book);
        wishlistRepository.save(item);
    }

    public List<WishlistDto> getWishlist(UUID userId) {
        return wishlistRepository.findByUser_Uid(userId).stream().map(w -> new WishlistDto(
                w.getId(),
                userId,
                w.getBook().getBid(),
                w.getBook().getTitle(),
                w.getBook().getAuthor(),
                w.getBook().getPrice()
        )).toList();
    }

    public void removeFromWishlist(UUID wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}
