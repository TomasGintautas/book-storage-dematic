package com.dematic.dematicbookstorage.entities.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

@Data
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
}
