package com.webdev.productsystem.Shared.Infrastruture.Hibernate;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfigFactory {

    @Autowired
    private Environment env;

    @Bean("session-factory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource());
        sessionFactory.setHibernateProperties(this.hibernateProperties());

        FileSystemResource userResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Users/User/Infrastructure/Hibernate/User.hbm.xml");
        FileSystemResource hotelResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/Hotel/Infrastructure/Hibernate/Hotel.hbm.xml");
        FileSystemResource ticketResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/Ticket/Infrastructure/Hibernate/Ticket.hbm.xml");
        FileSystemResource bookingResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/Booking/infrastructure/Hibernate/Booking.hbm.xml");
        FileSystemResource tourResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/Tour/Infrastructure/Hibernate/Tour.hbm.xml");
        FileSystemResource cityResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/City/Infrastructure/Hibernate/City.hbm.xml");
        FileSystemResource phoneResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Users/Phone/Infrastructure/Hibernate/Phone.hbm.xml");
        FileSystemResource addressResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/Address/Infrastructure/Hibernate/Address.hbm.xml");
        FileSystemResource touristicLocationResource = new FileSystemResource("./src/main/java/com/webdev/productsystem/Tours/TouristicLocation/Infrastructure/Hibernate/TouristicLocation.hbm.xml");

        sessionFactory.setMappingLocations(userResource, hotelResource, ticketResource,tourResource, cityResource, bookingResource, phoneResource, addressResource, touristicLocationResource);
        return sessionFactory;
    }

    @Bean("transactional-manager")
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(AvailableSettings.HBM2DDL_AUTO, "none");
        hibernateProperties.put(AvailableSettings.SHOW_SQL, "false");
        hibernateProperties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MariaDBDialect");
        return hibernateProperties;
    }

    private DataSource dataSource() {
        String url = env.getProperty("datasource.url");
        String userName = env.getProperty("datasource.username");
        String password = env.getProperty("datasource.password");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mariadb://" + url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        return dataSource;
    }

}
