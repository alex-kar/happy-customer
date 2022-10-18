package io.github.alexkar.happycustomer.controller;

import io.github.alexkar.happycustomer.service.RewardRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1")
public class RewardController extends BaseController {
    private static final String REWARD_PATH = "reward";
    @Autowired
    private RewardRequestHandler rewardRequestHandler;

    @GetMapping(REWARD_PATH + "/customer/{id}")
    public long perCustomer(@PathVariable(value = "id", required = true) String customerId) {
        log.debug("Reward points request for customer [{}]", customerId);
        return rewardRequestHandler.perCustomer(customerId);
    }

    @GetMapping(REWARD_PATH + "/total")
    public long total() {
        log.debug("Total reward points request [{}]");
        return rewardRequestHandler.total();
    }
}
