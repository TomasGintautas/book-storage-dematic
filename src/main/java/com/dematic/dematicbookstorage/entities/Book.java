package com.dematic.dematicbookstorage.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Book {

    private Long id;

    private Long barcode;

    private String bookName;

    private String author;

    private Long quantity;

    private BigDecimal price;

    private Boolean isAntique;

    private Boolean isScienceJournal;

    private Long scienceIndex;

    private LocalDate releaseYear;

}
