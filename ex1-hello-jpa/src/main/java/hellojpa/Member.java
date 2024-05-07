package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // jpa를 사용함을 인식
// @Table(name="") 데이터베이스의 테이블 이름을 지정
public class Member {
    @Id
    private Long id;
    // @Column(name="") 데이터베이스 열 이름을 지정
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
