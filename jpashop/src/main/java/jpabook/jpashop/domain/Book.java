package jpabook.jpashop.domain;

import jakarta.persistence.Entity;

@Entity
public class Book extends Item {
    private String writer;
    private String isbn;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
