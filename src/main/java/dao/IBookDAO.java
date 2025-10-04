package main.java.dao;

import main.java.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
     Book save(Book book);
    void delete(String isbn);
    Optional<Book> getById(String isbn);
    List<Book> getAllBooks();
    boolean getByGenre(String genre);
    int getIndexOfBookId(String isbn);
}
