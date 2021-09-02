package com.dematic.dematicbookstorage.validations;

import com.dematic.dematicbookstorage.entities.Book;

public class BookTypeValidation {

    public Boolean isAntique(Book book) {
        return book.getReleaseYear() != null;
    }

    public Boolean isScienceJournal(Book book) {
        return book.getScienceIndex() != null;
    }
}
