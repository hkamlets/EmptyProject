package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;

public class Util {
    // реализуйте настройку соеденения с БД
    private  static final String Url = "jdbc:mysql://localhost:3306/newdata";
    private  static final String User = "jpauser";
    private  static final String Password = "jpapwd";
    public static Connection getConnection() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(Url, User, Password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    private  static SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", Url)
                    .setProperty("hibernate.connection.username", User)
                    .setProperty("hibernate.connection.password", Password)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
