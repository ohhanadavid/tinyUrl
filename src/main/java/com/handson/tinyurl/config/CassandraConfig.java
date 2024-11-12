package com.handson.tinyurl.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.URISyntaxException;

@Configuration
public class CassandraConfig {

    @Bean("cassandraSession")
    public CqlSession getCassandraSession() throws URISyntaxException {
        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress("cassandra", 9042))
                .withKeyspace("tiny_keyspace")
                .withLocalDatacenter("datacenter1")
                .build();
    }

}

