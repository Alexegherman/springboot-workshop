package eu.accesa.training.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Configuration
@MapperScan(value = {"eu.accesa.training"}, sqlSessionFactoryRef = "postgreSqlSessionFactory")
public class DbConfig {

    @Value("${db.default.url}")
    String dbUrl;

    @Value("${db.default.user}")
    String dbUser;

    @Value("${db.default.password}")
    String dbPass;

    @Value("${db.default.driver}")
    String dbDriver;

    @Value("${db.default.pool.size}")
    Integer maxPoolSize;

    private HikariDataSource hikariDataSource = new HikariDataSource();

    private SqlSessionTemplate sessionTemplate;

    @PostConstruct
    public void init() {
        hikariDataSource.setDriverClassName(dbDriver);
        hikariDataSource.setJdbcUrl(dbUrl);
        hikariDataSource.setConnectionTestQuery("select 1");
        hikariDataSource.setUsername(dbUser);
        hikariDataSource.setPassword(dbPass);
        hikariDataSource.setMaximumPoolSize(maxPoolSize);
    }

    @Bean(name = "postgreSqlDataSource")
    @Primary
    public DataSource getPostgresDataSource() {
        return hikariDataSource;
    }

    @Bean(name = "postgreSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(getPostgresDataSource());
        return sessionFactory.getObject();
    }

    @PreDestroy
    public void cleanup() {
        if (sessionTemplate != null) {
            sessionTemplate.close();
        }
    }
}
