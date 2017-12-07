package org.st411ar;


import java.util.List;


import org.st411ar.dao.*;
import org.st411ar.entity.*;


public class App {

    public static void main( String[] args ) {
        testJdbc();
        testHibernate();
    }

    private static void testJdbc() {
        System.out.println( "testJdbc() has been started" );
        try {
            DAO dao = new JdbcDAO();

            for (User user : dao.getUsers()) {
                System.out.println(user);
            }
            for (Book book : dao.getBooks()) {
                System.out.println(book);
            }

            for (Order order : dao.getOrders()) {
                System.out.println(order);
            }
        } catch (ClassNotFoundException e) {
            System.out.println( "can't find jdbc driver class" );
            e.printStackTrace();
        }
        System.out.println( "testJdbc() has been finished" );
    }

    private static void testHibernate() {
        System.out.println( "testHibernate() has been started" );
        DAO dao = new HibernateDAO();

        for (User user : dao.getUsers()) {
            System.out.println(user);
        }
        for (Book book : dao.getBooks()) {
            System.out.println(book);
        }

        for (Order order : dao.getOrders()) {
            System.out.println(order);
        }

        System.out.println( "testHibernate() has been finished" );
    }
}