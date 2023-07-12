package org.example;

import org.example.model.Child;
import org.example.model.Toys;
import org.example.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.HashSet;
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

            session.flush();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }




    }
}