package com.dematic.dematicbookstorage.entities;

import com.dematic.dematicbookstorage.entities.dto.requests.BookRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Year;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;

    private String bookName;

    private String author;

    private Long quantity;

    private BigDecimal price;

    private Integer scienceIndex;

    @JsonFormat(pattern = "yyyy")
    private Year releaseYear;

    public Book(BookRequest bookRequest) {
        this.barcode = bookRequest.getBarcode();
        this.bookName = bookRequest.getBookName();
        this.author = bookRequest.getAuthor();
        this.price = bookRequest.getPrice();
        this.quantity = bookRequest.getQuantity();
        this.releaseYear = bookRequest.getReleaseYear();
        this.scienceIndex = bookRequest.getScienceIndex();
    }
}
