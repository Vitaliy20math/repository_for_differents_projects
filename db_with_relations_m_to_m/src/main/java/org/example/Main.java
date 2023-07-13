package org.example;

import org.example.model.Child;
import org.example.model.Toys;
import org.example.util.Util;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /*Connection connection = Util.getConnection();
        System.out.println(connection);
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();*/
        /*Child child1 = session.find(Child.class, 1);
        session.delete(child1);*/
        Transaction transaction = null;

        EntityManagerFactory emf = Util.getSessionFactory();

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();


        Toys toysN1 = new Toys("Elephant");
        Toys toysN2 = new Toys("Fox");
        Toys toysN3 = new Toys("Wolf");
        em.persist(toysN1);
        em.persist(toysN2);
        em.persist(toysN3);

        Set<Toys> setN1 = new HashSet<>();
        setN1.add(toysN1);
        setN1.add(toysN2);
        Set<Toys> setN2 = new HashSet<>();
        setN2.add(toysN3);
        setN2.add(toysN2);

        Child child1 = new Child("Petya", "Petrov", setN1);
        Child child2 = new Child("Semen", "Semenov", setN2);

        em.persist(child1);
        em.persist(child2);
        //String sqlCommand = "select c from Child c";
        TypedQuery<Toys> query = em.createQuery("select t from Toys t", Toys.class);
        List<Toys> list = query.getResultList();


        em.getTransaction().commit();
        em.close();




  /*      Toys toys = new Toys("Mouse");
        Toys toys1 = new Toys("Rabbit");
        Toys toys2 = new Toys("Fox");
        Set<Toys> set = new HashSet<>();
        Set<Toys> set1 = new HashSet<>();
        set1.add(toys);
        set.add(toys1);
        set.add(toys);
        Child child = new Child("Rob", "Stark", set);
        Child child1 = new Child("Bob", "Marly", set1);
        Child child2 = new Child("Bobby", "Fisher");
        em.persist(child1);
        //session.save(child2);
        em.persist(toys);
        em.persist(toys2);
        em.persist(toys1);
        em.persist(child);
        String hqlCommand = "select c from Child c left join fetch c.toys";
        TypedQuery<Child> query = em.createQuery(hqlCommand, Child.class);
        List<Child> list = query.getResultList();
        //session.flush();

        em.getTransaction().commit();
        em.close();
        for (Child ch : list) {
            Set<Toys> setToys = ch.getToys();
            System.out.println("next would be lazy init exception");
            setToys.size();
        }*/
/*

        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Toys toys = new Toys("Mouse");
            Toys toys1 = new Toys("Rabbit");
            Toys toys2 = new Toys("Fox");
            Set<Toys> set = new HashSet<>();
            Set<Toys> set1 = new HashSet<>();
            set1.add(toys);
            set.add(toys1);
            set.add(toys);
            Child child = new Child("Rob", "Stark", set);
            Child child1 = new Child("Bob", "Marly", set1);
            Child child2 = new Child("Bobby", "Fisher");
            session.save(child1);
            //session.save(child2);
            session.save(toys);
            session.save(toys2);
            session.save(toys1);
            session.save(child);
            String hqlCommand = "select c from Child c";
            TypedQuery<Child> query = session.createQuery(hqlCommand, Child.class);
            List<Child> list = query.getResultList();
            //session.flush();
            transaction.commit();
            session.close();
            for (Child ch : list) {
                Set<Toys> setToys = ch.getToys();
                System.out.println("next would be lazy init exception");
                setToys.size();
            }

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
*/
    }
}