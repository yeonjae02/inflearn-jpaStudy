package hellojpa;

import jakarta.persistence.*;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.Arrays;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            // 생성
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); // team에서 add로 멤버를 추가해도 반영되지 않음.
            em.persist(member);

            /*
            flush, clear를 하지 않는다면 아래 코드를 실행해야 반복문 결과가 제대로 나옴.
            flush, clear를 하지 않고 + 아래 코드 없이 잘 동작하게 하려면 연관관계 편의 메소드 작성
             */
            // team.getMembers().add(member);

            /*
            연관관계 편의 메소드는 양쪽에 다 있으면 오류 발생 가능 -> 처음에 정하기
             */
            // team.addMember(member); -

            em.flush();
            em.clear();

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            // 양방향 연관관계
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();

            /* 연관관계 매핑 미사용
            // 생성
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());
            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);
             */
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
