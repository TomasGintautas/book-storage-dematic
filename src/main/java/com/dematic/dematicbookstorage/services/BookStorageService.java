package com.dematic.dematicbookstorage.services;

import com.dematic.dematicbookstorage.entities.Book;
import com.dematic.dematicbookstorage.entities.dto.requests.BookRequest;
import com.dematic.dematicbookstorage.entities.dto.requests.BookUpdateRequest;
import com.dematic.dematicbookstorage.entities.dto.responses.BarcodeQuantityResponse;
import com.dematic.dematicbookstorage.entities.dto.responses.BookPriceResponse;
import com.dematic.dematicbookstorage.entities.dto.responses.BookResponse;
import com.dematic.dematicbookstorage.exceptions.BookNotFoundException;
import com.dematic.dematicbookstorage.repositories.BookRepository;
import com.dematic.dematicbookstorage.utils.QuantityComparator;
import com.dematic.dematicbookstorage.validations.BookTypeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookStorageService {

    private final BookRepository bookRepository;
    private final BookTypeValidation bookTypeValidation;
    private final QuantityComparator quantityComparator;

    @Autowired
    public BookStorageService(BookRepository bookRepository, BookTypeValidation bookTypeValidation, QuantityComparator quantityComparator) {
        this.bookRepository = bookRepository;
        this.bookTypeValidation = bookTypeValidation;
        this.quantityComparator = quantityComparator;
    }

    public BookResponse createBook(BookRequest bookRequest){
        return new BookResponse(bookRepository.save(new Book(bookRequest)));
    }

    public BookResponse getBookByBarcode(String barcode){
        return new BookResponse(bookRepository.getBookByBarcode(barcode).orElseThrow(() -> new BookNotFoundException("Book was not found by given barcode: ", barcode)));
    }

    public BookResponse updateBook(String barcode, BookUpdateRequest bookUpdateRequest){
        Book bookToUpdate = bookRepository.getBookByBarcode(barcode).orElseThrow(() -> new BookNotFoundException("Book was not found by given barcode: ", barcode));

        if(bookUpdateRequest.getBookName() != null){
            bookToUpdate.setBookName(bookUpdateRequest.getBookName());
        }

        if(bookUpdateRequest.getAuthor() != null){
            bookToUpdate.setAuthor(bookUpdateRequest.getAuthor());
        }

        if(bookUpdateRequest.getPrice() != null){
            bookToUpdate.setPrice(bookUpdateRequest.getPrice());
        }

        if (bookUpdateRequest.getQuantity() != null) {
            bookToUpdate.setQuantity(bookUpdateRequest.getQuantity());
        }

        if(bookUpdateRequest.getBarcode() != null){
            bookToUpdate.setBarcode(bookUpdateRequest.getBarcode());
        }

        if(bookUpdateRequest.getScienceIndex() != null){
            bookToUpdate.setScienceIndex(bookUpdateRequest.getScienceIndex());
        }

        if(bookUpdateRequest.getReleaseYear() != null){
            bookToUpdate.setReleaseYear(bookUpdateRequest.getReleaseYear());
        }

        return new BookResponse(bookRepository.save(bookToUpdate));
    }

    public BookPriceResponse getTotalPrice(String barcode){
        Book book = bookRepository.getBookByBarcode(barcode).orElseThrow(() -> new BookNotFoundException("Book was not found by given barcode: ", barcode));
        BigDecimal totalPrice = BigDecimal.valueOf(book.getQuantity()).multiply(book.getPrice());

        if(bookTypeValidation.isAntique(book)){
            Long yearDifference = ChronoUnit.YEARS.between(book.getReleaseYear(),Year.now());
            totalPrice = totalPrice.multiply(BigDecimal.valueOf(yearDifference).divide(BigDecimal.valueOf(10L)));
        }

        if(bookTypeValidation.isScienceJournal(book)){
            totalPrice = totalPrice.multiply(BigDecimal.valueOf(book.getScienceIndex()));
        }

        return new BookPriceResponse(book, totalPrice);
    }

    public List<BarcodeQuantityResponse> getAllBarcodes(){
        return bookRepository.findAll()
                .stream()
                .sorted(quantityComparator)
                .map(book -> new BarcodeQuantityResponse(book))
                .collect(Collectors.toList());
    }

}
