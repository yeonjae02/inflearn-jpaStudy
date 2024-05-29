package hellojpa;
import jakarta.persistence.Entity;

@Entity
public class Book extends Item{
    private String writer;
    private String isbn;

}