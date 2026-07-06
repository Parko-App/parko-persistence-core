package com.parko.persistence.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "parko-persistence")
public class ParkoPersistenceProperties {

    private boolean enabled = true;
}
