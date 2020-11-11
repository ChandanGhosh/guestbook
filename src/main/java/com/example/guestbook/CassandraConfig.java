package com.example.guestbook;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.autoconfigure.cassandra.DriverConfigLoaderBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionBuilderConfigurer;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.cql.session.init.KeyspacePopulator;
import org.springframework.data.cassandra.core.cql.session.init.ResourceKeyspacePopulator;

@Configuration
public class CassandraConfig {

    @Value("${cassandra.keyspace:betterbotz}")
    private String keyspace;

    @Value("${cassandra.contact-points:localhost}")
    private String contactPoints;

    @Value("${cassandra.port:9042}")
    private Integer port;

    @Value("${cassandra.local-datacenter:datacenter1}")
    private String localDataCenter;

    protected String getKeyspaceName() {
        return keyspace;
    }

    protected String getLocalDataCenter() {
        return localDataCenter;
    }

    protected String getContactPoints() {
        return contactPoints;
    }

    protected int getPort() {
        return 9042;
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer() {
        return builder -> builder.addContactPoint(new InetSocketAddress(this.contactPoints, this.port))
                .withLocalDatacenter(this.localDataCenter);
    }

    @Bean
    public DriverConfigLoaderBuilderCustomizer driverConfigLoaderBuilderCustomizer() {
        return builder -> builder.withString(DefaultDriverOption.SESSION_NAME, "guestbook");
    }

    @Bean
    public GuestDao productDao(CqlSession session) {
        return new GuestDao(session, keyspace, localDataCenter);
    }

}
