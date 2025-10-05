package main.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String description;
    private int publishYear;
    private int availableCopies;


    public Book() {

    }

    public Book(Long id,
                String isbn,
                String title,
                String author,
                String genre,
                String description,
                int yearOfPublish,
                int availableCopies
               ) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.publishYear = yearOfPublish;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", yearOfPublish=" + publishYear +
                ", availableCopies=" + availableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn());
    }
}
