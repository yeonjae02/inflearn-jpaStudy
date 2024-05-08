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
            Member member = em.find(Member.class, 150L);
            member.setName("aaaa");

            em.detach(member); // JPA에서 더 이상 관리하지 않음.

            /*
            Member member = new Member();
            member.setId(10L);
            member.setName("helloJPA"); // 현재까지 비영속 상태
            em.persist(member); // 영속 상태가 됨. -> 바로 DB 쿼리 날아가지 X

            Member member1 = em.find(Member.class, 10L);
            Member member2 = em.find(Member.class, 10L);
            System.out.println("member1.getId() = " + member1.getId()); // 하나의 트랜잭션 내 1차 캐시에서 먼저 조회

            System.out.println(member1 == member2); // 영속 엔티티의 동일성 보장
             */

            /*
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

             Member member = em.find(Member.class, 150L);
             member.setName("zzz"); // 이름 변경
            // persist() 호출하지 않아도 자동으로 update 쿼리
             */

            /*
             Member member = new Member(200L, "200m");
            em.persist(member);
            em.flush(); // 강제 호출 -> insert 쿼리 나감
             */

            tx.commit(); // DB 쿼리는 커밋 시.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
