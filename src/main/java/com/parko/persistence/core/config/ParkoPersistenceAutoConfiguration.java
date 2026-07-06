package com.parko.persistence.core.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration(after = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = "parko-persistence", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(ParkoPersistenceProperties.class)
@EntityScan(basePackages = "com.parko.persistence.core.model.entity")
@EnableJpaRepositories(basePackages = "com.parko.persistence.core.repository")
public class ParkoPersistenceAutoConfiguration {
}
