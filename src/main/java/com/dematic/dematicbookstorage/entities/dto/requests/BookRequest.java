package com.dematic.dematicbookstorage.entities.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookRequest {

    @NotBlank
    private String barcode;

    @NotBlank
    private String bookName;

    @NotBlank
    private String author;

    @NotBlank
    private Long quantity;

    @NotBlank
    private BigDecimal price;

    @Range(min = 1, max = 10)
    private Integer scienceIndex;

    @JsonFormat(pattern = "yyyy")
    private LocalDate releaseYear;
}
