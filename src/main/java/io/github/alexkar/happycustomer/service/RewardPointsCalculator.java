package io.github.alexkar.happycustomer.service;

import io.github.alexkar.happycustomer.configuration.RewardConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardPointsCalculator {
    @Autowired
    private ConfigurableEnvironment configurableEnvironment;
    @Autowired
    private RewardConfiguration config;

    public long calc(Long purchase) {
        int result = 0;
        List<Map.Entry<Long, Long>> entries =
                config.getLevels().entrySet()
                        .stream()
                        .sorted(Comparator.comparingLong(Map.Entry::getKey))
                        .collect(Collectors.toList());
        if (!entries.isEmpty()) {
            for (int i = 0; i < entries.size(); i++) {
                Map.Entry<Long, Long> entry = entries.get(i);
                Long curLevel = entry.getKey();
                Long points = entry.getValue();
                if (purchase > curLevel) {
                    Long nextLevel = i < entries.size() - 1 ? entries.get(i + 1).getKey() : purchase;
                    result += (Math.min(nextLevel, purchase) - curLevel) * points;
                }
            }
        }
        return result;
    }

}
