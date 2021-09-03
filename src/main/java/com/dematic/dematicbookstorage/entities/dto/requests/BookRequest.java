package com.dematic.dematicbookstorage.entities.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Year;

@Data
@NoArgsConstructor
public class BookRequest {

    @NotBlank
    private String barcode;

    @NotBlank
    private String bookName;

    @NotBlank
    private String author;

    @NotNull
    private Long quantity;

    @NotNull
    private BigDecimal price;

    @Range(min = 1, max = 10)
    private Integer scienceIndex;

    private Year releaseYear;

    public BookRequest(String barcode, String bookName, String author, Long quantity, BigDecimal price) {
        this.barcode = barcode;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public BookRequest(String barcode, String bookName, String author, Long quantity, BigDecimal price, Integer scienceIndex) {
        this.barcode = barcode;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.scienceIndex = scienceIndex;
    }

    public BookRequest(String barcode, String bookName, String author, Long quantity, BigDecimal price, Year releaseYear) {
        this.barcode = barcode;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.releaseYear = releaseYear;
    }
}
