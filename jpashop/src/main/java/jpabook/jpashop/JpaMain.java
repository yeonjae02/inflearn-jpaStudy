package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            // 객체지향적이지 못한 코드 : 단계적으로 찾아감. 관겨헹 DB에 맞춘 설계
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();
            Member member = em.find(Member.class, memberId);

            // 객체지향적 코드 : 한 번에 찾아감.
            Member findMember = order.getMember();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}