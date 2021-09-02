package com.dematic.dematicbookstorage.utils;

import com.dematic.dematicbookstorage.entities.Book;
import java.util.Comparator;

public class QuantityComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getQuantity().compareTo(o2.getQuantity());
    }
}
