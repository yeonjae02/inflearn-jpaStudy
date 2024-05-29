package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A") // DTYPE 이름 지정하기
public class Album extends Item{
    private String artist;
}
