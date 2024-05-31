package hellojpa;

import jakarta.persistence.*;


@Entity
public class Child {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Parent parent;

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
