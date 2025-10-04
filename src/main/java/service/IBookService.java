package main.java.service;

import main.java.dto.BookInsertDTO;
import main.java.dto.BookReadOnlyDTO;
import main.java.exceptions.BookNotFoundException;

import java.util.List;

public interface IBookService {
    boolean createNewBook(BookInsertDTO dto);
    List<BookReadOnlyDTO> searchBook(String title, String genre, String author) throws BookNotFoundException;
    void delete(String isbn, String title) throws BookNotFoundException;
    //TODO: Add more queries
}
