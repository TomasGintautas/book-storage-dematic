package com.dematic.dematicbookstorage.entities.dto.responses;

import com.dematic.dematicbookstorage.entities.Book;
import lombok.Data;

@Data
public class BarcodeQuantityResponse {

    private String barcode;

    private Long quantity;

    public BarcodeQuantityResponse(Book book) {
        this.barcode = book.getBarcode();
        this.quantity = book.getQuantity();
    }
}
