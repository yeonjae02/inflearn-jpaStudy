package hellojpa;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 매핑 정보만 받는 슈퍼클래스
public abstract class BaseEntity {

    @Column(name="INSERT_MEMBER") // 이름 지정하기
    private String createdBy;
    private LocalDateTime createdDate;

    @Column(name="UPDATE_MEMBER")
    private String lastModifiedBY;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBY() {
        return lastModifiedBY;
    }

    public void setLastModifiedBY(String lastModifiedBY) {
        this.lastModifiedBY = lastModifiedBY;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
