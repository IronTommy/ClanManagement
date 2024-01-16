package com.example.clanmanagement.Service;

import com.example.clanmanagement.Entity.Clan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAddGoldService {

    private static final Logger logger = LoggerFactory.getLogger(UserAddGoldService.class);
    private final ClanService clanService;

    public UserAddGoldService(ClanService clanService) {
        this.clanService = clanService;
    }

    public void addGoldToClan(long userId, long clanId, int gold) {
        Clan clan = clanService.getClan(clanId);
        if (clan != null) {
            synchronized (clan) {
                clan.addGold(gold);
                logger.info("Added {} gold to clan {} for user {}", gold, clanId, userId);
                clanService.updateClan(clan);
            }
        } else {
            logger.error("Clan with id {} not found for user {}", clanId, userId);
        }
    }
}
