package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            Address address = new Address("city", "street", "zipcode");
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(address);
//            member.setWorkPeriod(new Period());
//            em.persist(member);
//
//            // 현재 setter를 이용한 값 변경을 막아두었는데, 값을 변경하고 싶을 때 -> 새로 만들어서 넣기
//            Address newAddress = new Address("new Address", address.getStreet(), address.getZipcode());
//            member.setHomeAddress(newAddress);

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street1", "10000"));

            member.getAddressHistory().add(new AddressEntity("old1", "street1", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street1", "10000"));

            member.getFavortieFoods().add("치킨");
            member.getFavortieFoods().add("피자");
            member.getFavortieFoods().add("햄버거");

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=========START=============");
            Member findMember = em.find(Member.class, member.getId());
            Address old = findMember.getHomeAddress();

            // 수정 -> 새롭게 생성해서 넣어야 함.
            findMember.setHomeAddress(new Address("newCity", old.getStreet(), old.getZipcode()));

            // 치킨 -> 한식
            findMember.getFavortieFoods().remove("치킨");
            findMember.getFavortieFoods().add("한식");

            findMember.getAddressHistory().remove(new AddressEntity("old1", "street1", "10000"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street1", "10000"));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}