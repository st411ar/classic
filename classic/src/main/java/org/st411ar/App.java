package org.st411ar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;


import org.hibernate.Session;


import org.st411ar.entity.User;

import org.st411ar.util.HibernateUtil;


public class App {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String URI = "jdbc:mysql://127.0.0.1:3306/classic";
    private static final String NAME = "root";
    private static final String PASSWORD = "qwerty";

    private static final String USERS_QUERY = "select id, name from users";

    public static void main( String[] args ) {
        testSimpleJDBC();
        testHibernate();
    }

    private static void testSimpleJDBC() {
        System.out.println( "testSimpleJDBC() has been started" );
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
	        Class.forName(DRIVER);
	        connection = DriverManager.getConnection(URI, NAME, PASSWORD);
	        statement = connection.createStatement();
            resultSet = statement.executeQuery(USERS_QUERY);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                System.out.println("id: '" + id + "', name: '" + name + "'");
            }
        } catch (ClassNotFoundException e) {
        	System.out.println("Can't find driver class \"" + DRIVER + "\"");
        	e.printStackTrace();
        } catch (SQLException e) {
        	System.out.println("SQLException has been caught");
        	e.printStackTrace();
        } finally {
        	try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
        		if (connection != null) {
        			connection.close();
        		}
	        } catch (SQLException e) {
	        	System.out.println("finally SQLException has been caught");
	        	e.printStackTrace();
        	}
        }
        System.out.println( "testSimpleJDBC() has been finished" );
    }

    private static void testHibernate() {
        System.out.println( "testHibernate() has been started" );
        List<User> users = getUsers();
        for (User user : users) {
            System.out.println("id: '" + user.getId() + "', name: '" + user.getName() + "'");
        }
        System.out.println( "testHibernate() has been finished" );
    }

    private static List<User> getUsers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        session.getTransaction().commit();
        return users;
    }
}