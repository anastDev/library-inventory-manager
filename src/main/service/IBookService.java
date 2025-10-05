package main.service;

import main.core.exceptions.BookAlreadyExists;
import main.dto.BookInsertDTO;
import main.dto.BookReadOnlyDTO;
import main.core.exceptions.BookNotFoundException;
import main.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    boolean createNewBook(BookInsertDTO dto) throws BookAlreadyExists;
    List<BookReadOnlyDTO> searchBookByTitle(String title) throws BookNotFoundException;
    Optional<Book> delete(Long id, String title) throws BookNotFoundException;
    void borrowBook(String title);
    //TODO: Add more queries
}
