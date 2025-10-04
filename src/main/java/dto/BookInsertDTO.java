package main.java.dto;

import java.time.LocalDate;

public class BookInsertDTO {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private LocalDate publishYear;
    private String description;
    private int availableCopies;

    public BookInsertDTO() {

    }

    public BookInsertDTO(String isbn,
                         String title,
                         String author,
                         String genre,
                         LocalDate publishYear,
                         String description,
                         int availableCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishYear = publishYear;
        this.description = description;
        this.availableCopies = availableCopies;
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

    public LocalDate getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(LocalDate publishYear) {
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
        return "BookInsertDTO{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publishYear=" + publishYear +
                ", description='" + description + '\'' +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
