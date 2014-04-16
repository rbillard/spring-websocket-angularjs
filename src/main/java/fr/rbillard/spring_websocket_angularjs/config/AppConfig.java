package fr.rbillard.spring_websocket_angularjs.config;

import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.rbillard.spring_websocket_angularjs.aspects.NotifyAspect;
import fr.rbillard.spring_websocket_angularjs.dto.CommentDTO;
import fr.rbillard.spring_websocket_angularjs.dto.UserDTO;
import fr.rbillard.spring_websocket_angularjs.model.Comment;
import fr.rbillard.spring_websocket_angularjs.model.User;

@Configuration
@ComponentScan(basePackages = { "fr.rbillard.spring_websocket_angularjs" }, excludeFilters = { @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION) })
@EnableJpaRepositories(basePackages = { "fr.rbillard.spring_websocket_angularjs.repository" })
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.HSQL);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPersistenceUnitName("comments");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(CommentDTO.class, Comment.class);
                mapping(UserDTO.class, User.class);
            }
        };

        return builder;
    }

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(beanMappingBuilder());
        return mapper;
    }
    
    @Bean
    public NotifyAspect notifyAspect() {
        return new NotifyAspect();
    }
}
