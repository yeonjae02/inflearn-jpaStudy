package hellojpa;

import jakarta.persistence.*;

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
            /* 생성
            Member member = new Member();
            member.setId(2L);
            member.setName("helloB");

            em.persist(member);
             */

            /* 조회
            Member findMember = em.find(Member.class, 2L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
             */

            /* 삭제
            Member findMember = em.find(Member.class, 2L);
            em.remove(findMember);
             */

            /* 수정 : 커밋 전에 변경 여부 확인 -> 변경되었다면 업데이트 쿼리 날리고 커밋
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA"); // 저장 필요 없음.
            tx.commit();
             */

            /* 전체 회원 조회 : 객체를 대상으로 한 쿼리
             */
            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    //.setFirstResult(1)
                    //.setMaxResults(6)
                    .getResultList();

             for (Member member : result){
                 System.out.println("member.name = " + member.getName());
             }

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
