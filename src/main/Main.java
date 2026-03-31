package main;

import main.core.exceptions.BookAlreadyExists;
import main.core.exceptions.BookNotAvailableException;
import main.core.exceptions.BookNotFoundException;
import main.dao.BookDAOImpl;
import main.dao.IBookDAO;
import main.dto.BookInsertDTO;
import main.model.Book;
import main.service.BookServiceImpl;
import main.service.IBookService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // --- Setup ---
        IBookDAO bookDAO = new BookDAOImpl();
        IBookService bookService = new BookServiceImpl(bookDAO);

        // --- 1. Create books ---
        System.out.println("=== Creating books ===");
        BookInsertDTO dto1 = new BookInsertDTO(
                "9780241252086",
                "White Nights",
                "Fyodor Dostoyevsky",
                "Poetry",
                "An exquisitely crafted miniature from the titan of Russian literature, Dostoyevsky's profound story of love and loss on the streets of St Petersburg retains all of its intensity and power in the twenty-first century.",
                2016,
                128,
                3
        );

        BookInsertDTO dto2 = new BookInsertDTO(
                "9781035093113",
                "The Other Bennet Sister",
                "Janice Hadlow",
                "Romance",
                "\n" +
                        "\n" +
                        "A beautifully judged and tender homage to Pride and Prejudice, Hadlow’s period novel puts the oft-neglected Mary Bennet centre stage in a tale of quiet ambition and personal freedom which is both moving and warmly funny.",
                2020,
                672,
                1
        );

        BookInsertDTO dto3 = new BookInsertDTO(
                "9781529157468",
                "Project Hail Mary",
                "Andy Wier",
                "Science Fiction",
                "Charting the desperate last-chance mission of a lone astronaut to safeguard our planet, Project Hail Mary is a riveting interstellar adventure from the bestselling author of The Martian.",
                1925,
                496,
                4
        );

        try {
            System.out.println("Created dto1: " + bookService.createNewBook(dto1));
            System.out.println("Created dto2: " + bookService.createNewBook(dto2));
            System.out.println("Created dto3: " + bookService.createNewBook(dto3));
        } catch (BookAlreadyExists e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- 2. Create duplicate (exception) ---
        System.out.println("\n=== Creating Duplicate  ===");
        try {
            bookService.createNewBook(dto1);
        } catch (BookAlreadyExists e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- 3. Get all books ---
        System.out.println("\n=== Getting all the books ===");
        try {
            bookService.getAllOfBooks()
                    .forEach(System.out::println);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- 4. Search by title ---
        System.out.println("\n=== Searching by title ===");
        try {
            bookService.searchBookByTitle("White Nights")
                    .forEach(System.out::println);
        } catch (BookNotFoundException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // --- 5. Search by genre ---
        System.out.println("\n=== Searching by genre ===");
        try {
            bookService.getByGenre("Romance")
                    .forEach(System.out::println);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- 6. Borrow a book ---
        System.out.println("\n=== Borrowing a book ===");
        try {
            bookService.borrowBook("The Other Bennet Sister");
            System.out.println("Book borrowed successfully!");
            bookService.borrowBook("The Other Bennet Sister");
        } catch (BookNotFoundException | BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- 7. Delete a book ---
        System.out.println("\n=== Deleting a book ===");
        try {
            Optional<Book> deleted = bookService.delete(1L, "White Nights");
            deleted.ifPresent(b -> System.out.println("Deleted: " + b));
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- 8. Get all books after deletion ---
        System.out.println("\n=== Get all the books after deletion ===");
        try {
            bookService.getAllOfBooks()
                    .forEach(System.out::println);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
