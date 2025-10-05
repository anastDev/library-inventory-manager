package main.dao;

import main.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    boolean save(Book book);
    Optional<Book>searchBook(String title);
    Optional<Book> delete(Long id);
    Optional<Book> getById(Long id);
    boolean getByIsbn(String isbn);
    Optional<Book> getByTitle(String title);
    List<Book> getAllBooks();
    boolean getByGenre(String genre);
}
