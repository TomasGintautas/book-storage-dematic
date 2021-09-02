package com.dematic.dematicbookstorage.controllers;

import com.dematic.dematicbookstorage.entities.dto.requests.BookRequest;
import com.dematic.dematicbookstorage.entities.dto.requests.BookUpdateRequest;
import com.dematic.dematicbookstorage.entities.dto.responses.BarcodeQuantityResponse;
import com.dematic.dematicbookstorage.entities.dto.responses.BookPriceResponse;
import com.dematic.dematicbookstorage.entities.dto.responses.BookResponse;
import com.dematic.dematicbookstorage.services.BookStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/v1/book-storage")
@RestController
public class BookStorageController {

    @Autowired
    private BookStorageService bookStorageService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@Valid @RequestBody BookRequest bookRequest){
        return bookStorageService.createBook(bookRequest);
    }

    @GetMapping("/{barcode}")
    public BookResponse getBook(@PathVariable("barcode") String barcode){
        return bookStorageService.getBookByBarcode(barcode);
    }

    @PutMapping("/{barcode}")
    public BookResponse updateBook(@PathVariable("barcode") String barcode,@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        return bookStorageService.updateBook(barcode, bookUpdateRequest);
    }

    @GetMapping("/{barcode}/price")
    public BookPriceResponse calculatePrice(@PathVariable("barcode") String barcode){
        return bookStorageService.getTotalPrice(barcode);
    }

    @GetMapping("/price-list")
    public List<BarcodeQuantityResponse> calculateAllPrices(){
        return bookStorageService.getAllBarcodes();
    }
}