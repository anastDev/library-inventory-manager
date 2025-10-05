package main.service;

import main.core.exceptions.BookAlreadyExists;
import main.core.mapper.Mapper;
import main.dao.IBookDAO;
import main.dto.BookInsertDTO;
import main.dto.BookReadOnlyDTO;
import main.core.exceptions.BookNotFoundException;
import main.model.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
//        TODO: Review the code as it is recursive indefinitely
        try {
            Book book = bookDAO
                    .getByTitle(title)
                    .orElseThrow(()-> new BookNotFoundException("Book with " + title + "not found."));
            return searchBookByTitle(title);
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
    public void borrowBook(String title) {

    }
}
