package com.dematic.dematicbookstorage.entities.dto.responses;

import com.dematic.dematicbookstorage.entities.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

@Data
public class BookResponse {

    private Long id;

    private String barcode;

    private String bookName;

    private String author;

    private Long quantity;

    private BigDecimal price;

    private Integer scienceIndex;

    private Year releaseYear;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.barcode = book.getBarcode();
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.quantity = book.getQuantity();
        this.price = book.getPrice();
        this.scienceIndex = book.getScienceIndex();
        this.releaseYear = book.getReleaseYear();
    }
}
