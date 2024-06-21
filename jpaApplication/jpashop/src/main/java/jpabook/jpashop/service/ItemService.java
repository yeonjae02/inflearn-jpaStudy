package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional // 변경이 필요함
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional // 트랜잭션 커밋 시 flush -> 업데이트
    public void updateItem(Long itemId, String name, int price, int stockQuantity ) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice( price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Repository
    @RequiredArgsConstructor
    public static class MemberRepositoryOld {
        private final EntityManager em;

        public void save(Member member){
            em.persist(member);
        }

        public Member findOne(Long id) {
            return em.find(Member.class, id);
        }

        public List<Member> findAll() {
            return em.createQuery("select m from Member m", Member.class)
                    .getResultList();
        }

        public List<Member> findByName(String name){
            return em.createQuery("select m from Member m where m.name =:name", Member.class)
                    .setParameter("name", name)
                    .getResultList();
        }

    }

    @Service
    @Transactional(readOnly = true) // 성능 최적화
    @RequiredArgsConstructor
    public static class MemberService {

        private final MemberRepository memberRepository;
        // 회원 가입
        @Transactional
        public Long join(Member member) {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }


        private void validateDuplicateMember(Member member) {
            // exception
            List<Member> findMembers = memberRepository.findByName(member.getName());
            if (!findMembers.isEmpty()) {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            }
        }

        // 화원 전체 조회
        public List<Member> findMembers() {
            return memberRepository.findAll();
        }

        public Member findOne(Long memberId) {
            return memberRepository.findById(memberId).get();
        }

        @Transactional
        public void update(Long id, String name) {
            Member member = memberRepository.findById(id).get();
            member.setName(name);
        }
    }
}
