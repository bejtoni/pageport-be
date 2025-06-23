package com.pageport.pageport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {
    private UUID id;
    private UUID userId;
    private UUID bookId;
    private String title;
    private String author;
    private double price;
}
