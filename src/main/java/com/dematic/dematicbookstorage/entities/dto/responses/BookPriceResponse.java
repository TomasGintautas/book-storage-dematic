package com.dematic.dematicbookstorage.entities.dto.responses;

import com.dematic.dematicbookstorage.entities.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;

@Data
@NoArgsConstructor
public class BookPriceResponse {

    private Long id;

    private String barcode;

    private String bookName;

    private String author;

    private Long quantity;

    private BigDecimal price;

    private Integer scienceIndex;

    private Year releaseYear;

    private BigDecimal totalPrice;

    public BookPriceResponse(Book book, BigDecimal totalPrice) {
        this.id = book.getId();
        this.barcode = book.getBarcode();
        this.bookName = book.getBookName();
        this.author = book.getAuthor();
        this.quantity = book.getQuantity();
        this.price = book.getPrice();
        this.scienceIndex = book.getScienceIndex();
        this.releaseYear = book.getReleaseYear();
        this.totalPrice = totalPrice;
    }
}
