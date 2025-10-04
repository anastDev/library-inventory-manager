package main.java.dao;

import main.java.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements IBookDAO{
    private static final List<Book> books = new ArrayList<>();

    @Override
    public Book save(Book book) {
        if (book == null) return null;
        books.add(book);
        return book;
    }

    @Override
    public void delete(String isbn) {
        books.removeIf(b -> b.getIsbn().equals(isbn));
    }

    @Override
    public Optional<Book> getById(String isbn) {
        return books
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public boolean getByGenre(String genre) {
        return books.stream()
                .anyMatch(b -> b.getGenre().equals(genre));
    }

    @Override
    public int getIndexOfBookId(String isbn) {
        int positionToReturn = -1;

        for (int i= 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == isbn) {
                positionToReturn = i;
                break;
            }
        }
        return positionToReturn;
    }

}
