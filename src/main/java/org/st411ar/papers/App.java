package org.st411ar.papers;


import java.util.List;


import org.springframework.context.support.FileSystemXmlApplicationContext;


import org.st411ar.papers.dao.*;
import org.st411ar.papers.entity.*;
import org.st411ar.papers.factory.Factory;


public class App {

    public static void main( String[] args ) {
        testDao(new JdbcDAO());
        testDao(new HibernateDAO());

        String[] configs = new String[]{"spring.cfg.xml", "db.cfg.xml"};
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(configs);
        Factory factory = (Factory) context.getBean("factoryTransactionProxy");
        DAO dao = factory.getDao();
        testDao(dao);
    }

    private static void testDao(DAO dao) {
        System.out.println("-----------");
        System.out.println(dao);
        System.out.println("-----------");
        for (User user : dao.getUsers()) {
            System.out.println(user);
        }
        for (Book book : dao.getBooks()) {
            System.out.println(book);
        }

        for (Order order : dao.getOrders()) {
            System.out.println(order);
        }
    }
}