package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {
    @Autowired EntityManager em;
    @Test
    public void updateTest() throws Exception{
        Book book = em.find(Book.class, 1L);

        // TX
        book.setName("변경");

        // 변경 감지 = dirty checking (업데이트)
        // TX Commit

    }

}
