package hellojpa;

import jakarta.persistence.*;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.Arrays;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // JPA에서는 트랜잭션 단위가 중요함. 데이터 변경 작업은 모두 트랜잭션 안에서 수행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            // DB SEQ = 1 | 1
            // DB SEQ = 51 | 2
            // DB SEQ = 51 | 3

            em.persist(member1); // 1, 51
            em.persist(member2); // 메모리
            em.persist(member3); // 메모리

            System.out.println("member1 = " + member1.getId());
            System.out.println("member1 = " + member2.getId());
            System.out.println("member1 = " + member3.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
