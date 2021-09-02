package com.dematic.dematicbookstorage.entities.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

@Data
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
}
