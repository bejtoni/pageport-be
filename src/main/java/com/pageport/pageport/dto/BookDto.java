package com.pageport.pageport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    UUID id;
    String title;
    String author;
    String genre;
    Number price;
    String description;
}
