package io.github.alexkar.happycustomer.service;

import io.github.alexkar.happycustomer.configuration.RewardConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class RewardPointsCalculatorTest {
    private final RewardPointsCalculator rewardPointsCalculator = new RewardPointsCalculator();

    @ParameterizedTest
    @CsvSource({
            "0, 0",     // $0 purchase = 0x$1 + 0x$2 + 0x$3 = 0 points.
            "50, 0",    // $50 purchase = 0x$1 + 0x$2 + 0x$3 = 0 points.
            "51, 1",    // $51 purchase = 1x$1 + 0x$2 + 0x$3 = 1 points.
            "100, 50",  // $100 purchase = 50x$1 + 0x$2 + 0x$3 = 50 points.
            "101, 52",  // $101 purchase = 50x$1 + 1x$2 + 0x$3 = 52 points.
            "200, 250", // $200 purchase = 50x$1 + 100x$2 + 0x$3 = 250 points.
            "201, 253", // $200 purchase = 50x$1 + 100x$2 + 1x$3 = 253 points.
            "300, 550", // $200 purchase = 50x$1 + 100x$2 + 100x$3 = 550 points.
    })
    public void calcTest(Long purchase, Long expected) {
        Map<Long, Long> levelMap = Map.of(
                50L, 1L,
                100L, 2L,
                200L, 3L
        );
        RewardConfiguration config = new RewardConfiguration();
        config.setLevels(levelMap);
        ReflectionTestUtils.setField(rewardPointsCalculator, "config", config);

        long actual = rewardPointsCalculator.calc(purchase);

        Assertions.assertEquals(expected, actual);
    }

}