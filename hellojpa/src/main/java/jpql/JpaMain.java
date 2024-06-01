package jpql;
import jakarta.persistence.*;

import javax.lang.model.SourceVersion;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            int resultCnt = em.createQuery("update Member m set m.age =20")
                    .executeUpdate();
            System.out.println("resultCnt = " + resultCnt);

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember.getAge() = " + findMember.getAge());

//            String, int -> 서로 다른 타입 -> Query 타입
//            Query query2 = em.createQuery("select m.username, m.age from Member m");
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//            Member member1 = result.get(0);
//            member1.setAge(20);
//
//            List<Team> result2 = em.createQuery("select t from Member m join m.team t", Team.class)
//                    .getResultList();
//
//            em.createQuery("select o.address from Order o", Address.class) // 임베디드 타입
//                    .getResultList();
//
//            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("memberDTO = " + memberDTO.getUsername());
//            System.out.println("memberDTO = " + memberDTO.getAge());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}