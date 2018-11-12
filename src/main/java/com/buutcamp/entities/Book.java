package com.buutcamp.entities;

import javax.persistence.*;


@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int bookId;

    @Column(name = "title")
    private String bookTitle;

    @Column(name = "author_firstname")
    private String authorFirstname;

    @Column(name = "author_lastname")
    private String authorLastname;

    @Column(name = "language")
    private String bookLanguage;

    @Column(name = "information")
    private String bookInformation;

    @Column(name = "image")
    private String bookImage;

    @Column(name = "status")
    private int bookStatus;


    //Constructors
    public Book() {
    }

    public Book(String bookTitle, String authorFirstname, String authorLastname, String bookLanguage, String bookInformation, String bookImage, int bookStatus) {
        this.bookTitle = bookTitle;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.bookLanguage = bookLanguage;
        this.bookInformation = bookInformation;
        this.bookImage = bookImage;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public void setAuthorLastname(String authorLastname) {
        this.authorLastname = authorLastname;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getBookInformation() {
        return bookInformation;
    }

    public void setBookInformation(String bookInformation) {
        this.bookInformation = bookInformation;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }
}
