package main.core.mapper;

import main.dto.BookInsertDTO;
import main.dto.BookReadOnlyDTO;
import main.model.Book;

public class Mapper {

    private Mapper() {

    }

    public static Book mapToModelEntity(BookInsertDTO dto) {
        Book b = new Book();

        b.setIsbn(dto.getIsbn().replaceAll("\\W", ""));
        b.setTitle(dto.getTitle());
        b.setAuthor(dto.getAuthor());
        b.setGenre(dto.getGenre());
        b.setDescription(dto.getDescription());
        b.setPublishYear(dto.getPublishYear());
        b.setAvailableCopies(dto.getAvailableCopies());

        return b;
    }

    public static BookReadOnlyDTO mapToReadOnlyDTO(Book book) {
       return new BookReadOnlyDTO(
               book.getId(),
               book.getIsbn(),
               book.getTitle(),
               book.getAuthor(),
               book.getGenre(),
               book.getDescription(),
               book.getPublishYear(),
               book.getAvailableCopies()
               );
    }
}
