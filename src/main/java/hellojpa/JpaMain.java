package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //Member member = new Member();
            //member.setId(2L);
            //member.setName("helloB");
            //em.persist(member);

            //Member findMember = em.find(Member.class, 1L);
            //findMember.setName("HelloA");
            //System.out.println("findMember.id " + findMember.getId());
            //System.out.println("findMember.name " + findMember.getName());
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for(Member m : result){
                System.out.println("member.name :: " + m.getName());
                System.out.println("member.name :: " + m.getId());
            }

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
          em.close();
        }

        emf.close();
    }
}
