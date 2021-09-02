package com.dematic.dematicbookstorage.utils;

import com.dematic.dematicbookstorage.entities.Book;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class QuantityComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getQuantity().compareTo(o2.getQuantity());
    }
}
