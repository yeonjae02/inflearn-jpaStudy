package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stackQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
    
    // 비즈니스 로직
    public void addStock(int quantity) { // 재고 수량 증가
        this.stackQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int resStock = this.stackQuantity - quantity;
        if (resStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stackQuantity = resStock;
    }

}
