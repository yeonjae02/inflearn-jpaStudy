package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Child child1 = new Child();
            Child child2 = new Child();
            Parent parent = new Parent();

            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

             em.flush();
             em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent); // 부모가 사라지면 자식도 같이

//            Member member = em.find(Member.class, 1L);
//            printMember(member);
//            printMemberAndTeam(member);
// 멤버만 가져올 때와 멤버+팀 정보를 같이 가져오는 경우가 같이 필요할 때

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Team teamB = new Team();
//            teamB.setName("teamB");
//            em.persist(teamB);
//
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            member1.setTeam(team);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setTeam(teamB);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();

//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();

            // 패치 조인
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
//                    .getResultList();
//
//
//            Member m = em.find(Member.class, member1.getId());
//            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());
//            System.out.println("===");
//            System.out.println("teamName = " + m.getTeam().getName());
//            System.out.println("===");
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("m1.getClass() = " + refMember.getClass());
//            refMember.getUsername(); // 강제초기화 == Hibernate.initialize(refMember);
//            System.out.println("isLoaded " + emf.getPersistenceUnitUtil().isLoaded(refMember));

//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("ref = " + findMember.getClass());
//            System.out.println("refMember == findMember ; " + (refMember == findMember));
            
//            Member m2 = em.getReference(Member.class, member2.getId());
//            logic(m1, m2);

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1, m2 " +  (m1.getClass() == m2.getClass()));
        System.out.println("m1, m2 : " +  (m1 instanceof Member));
        System.out.println("m1, m2 : " +  (m2 instanceof Member));
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());

    }
}