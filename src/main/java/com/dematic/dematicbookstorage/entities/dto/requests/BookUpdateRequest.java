package com.dematic.dematicbookstorage.entities.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @NotBlank
    private String barcode;

    private String bookName;

    private String author;

    private Long quantity;

    private BigDecimal price;

    @Range(min = 1, max = 10)
    private Integer scienceIndex;

    private Year releaseYear;

    public BookUpdateRequest(String barcode, String bookName, String author, Long quantity, BigDecimal price) {
        this.barcode = barcode;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public BookUpdateRequest(String barcode, String bookName, String author, Long quantity, BigDecimal price, Integer scienceIndex) {
        this.barcode = barcode;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.scienceIndex = scienceIndex;
    }

    public BookUpdateRequest(String barcode, String bookName, String author, Long quantity, BigDecimal price, Year releaseYear) {
        this.barcode = barcode;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
        this.releaseYear = releaseYear;
    }
}
