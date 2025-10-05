package main.dao;

import main.model.Book;

import java.util.*;
import java.util.stream.Collectors;

public class BookDAOImpl implements IBookDAO{
    private static final Map<Long, Book> books = new HashMap<>();


    @Override
    public boolean save(Book book) {
       return books.putIfAbsent(book.getId(), book) == null;
    }

    @Override
    public Optional<Book> searchBook(String title) {
        return books
                .values()
                .stream()
                .filter(b-> b.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public Optional<Book> delete(Long id) {
        return Optional.ofNullable(books.remove(id));
    }

    @Override
    public Optional<Book> getById(Long id) {
        return books
                .values()
                .stream()
                .filter(b-> Objects.equals(b.getId(), id))
                .findFirst();
    }

    @Override
    public boolean getByIsbn(String isbn) {
       return books
               .values()
               .stream()
               .anyMatch(b -> b.getIsbn().equals(isbn));
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        return books
                .values()
                .stream()
                .filter(b -> b.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public List<Book> getAllBooks() {
        return books.values()
                .stream()
                        .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public boolean getByGenre(String genre) {
        return books
                .values()
                .stream()
                .anyMatch(b -> b.getGenre().equals(genre));
    }

}
