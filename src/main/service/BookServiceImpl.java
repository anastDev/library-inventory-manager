package main.service;

import main.core.exceptions.BookAlreadyExists;
import main.core.exceptions.BookNotAvailableException;
import main.core.mapper.Mapper;
import main.dao.IBookDAO;
import main.dto.BookInsertDTO;
import main.dto.BookReadOnlyDTO;
import main.core.exceptions.BookNotFoundException;
import main.model.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements IBookService {
    private final IBookDAO bookDAO;

    public BookServiceImpl(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean createNewBook(BookInsertDTO dto) throws BookAlreadyExists {
        Book book  = Mapper.mapToModelEntity(dto);
        if (bookDAO.getByIsbn(book.getIsbn())) {
            throw new BookAlreadyExists("Book: " + book.getTitle() + "already exists." );
        }
        bookDAO.save(book);
        return true;
    }

    @Override
    public List<BookReadOnlyDTO> searchBookByTitle(String title) throws BookNotFoundException {
        try {
            Book book = bookDAO
                    .getByTitle(title)
                    .orElseThrow(()-> new BookNotFoundException("Book with " + title + "not found."));
            BookReadOnlyDTO dto = Mapper.mapToReadOnlyDTO(book);
            return List.of(dto);
        } catch (BookNotFoundException e) {
            System.out.printf("%s. Book with title: %s not found. \n%s", LocalDateTime.now(), title, e);
           throw e;
        }
    }

    @Override
    public Optional<Book> delete(Long id, String title) throws BookNotFoundException {
        try {
            Book book = bookDAO
                    .getById(id)
                    .orElseThrow(
                            () -> new BookNotFoundException("Book with title " + title + "not found."));
            return bookDAO.delete(id);

        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book with title "
                    + title + "doesn't exist.");
        }
    }

    @Override
    public void borrowBook(String title) throws BookNotFoundException, BookNotAvailableException {
        Book book = bookDAO
                .getByTitle(title)
                .orElseThrow(()-> new BookNotFoundException("Book with title: " + title + "not found."));

        if(book.getAvailableCopies() == 0) {
            throw new BookNotAvailableException("Book with title: " + title + "is not available");
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookDAO.save(book);
    }

    @Override
    public List<BookReadOnlyDTO> getByGenre(String genre) throws BookNotFoundException {
       try {
           List<Book> books = bookDAO.getByGenre(genre);

           if (books.isEmpty()) {
               throw new BookNotFoundException("Book with genre: " + genre + "not found");
           }

           return books.stream()
                   .map(Mapper::mapToReadOnlyDTO)
                   .collect(Collectors.toList());
       } catch(BookNotFoundException e) {
           System.out.printf("%s. No books found for genre: %s. \n%s", LocalDateTime.now(), genre, e);
           throw e;
       }
    }

    @Override
    public List<BookReadOnlyDTO> getAllOfBooks()  throws BookNotFoundException{
        return bookDAO.getAllBooks()
                .stream()
                .map(Mapper::mapToReadOnlyDTO)
                .collect(Collectors.toList());
    }
}
