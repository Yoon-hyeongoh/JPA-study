package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member m = em.find(Member.class, 1L);
            m.setName("AAAAAAAAAAA");

            em.clear();

            Member m2 = em.find(Member.class, 1L);

            tx.commit();
            System.out.println("AFTER");
        } catch(Exception e){
            tx.rollback();
        } finally {
          em.close();
        }

        emf.close();
    }
}
