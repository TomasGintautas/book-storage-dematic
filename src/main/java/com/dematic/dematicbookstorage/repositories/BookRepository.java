package com.dematic.dematicbookstorage.repositories;

import com.dematic.dematicbookstorage.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookByBarcode(String barcode);
}
