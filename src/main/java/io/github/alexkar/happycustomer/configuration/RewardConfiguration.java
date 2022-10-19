package io.github.alexkar.happycustomer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "reward")
@Data
public class RewardConfiguration {
    private Map<Long, Long> levels;
}
