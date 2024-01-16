package com.example.clanmanagement.Service;

import com.example.clanmanagement.Entity.Clan;
import org.slf4j.Logger;

public class TaskService {

    private final ClanService clanService;
    private final Logger logger;

    public TaskService(ClanService clanService, Logger logger) {
        this.clanService = clanService;
        this.logger = logger;
    }


    public void completeTask(long clanId, long taskId, int goldReward) {
        Clan clan = clanService.getClan(clanId);
        if (clan != null) {
            synchronized (clan) {
                clan.addGold(goldReward);
                logger.info("Added {} gold to clan {} for completing task {}. New gold: {}", goldReward, clanId, taskId, clan.getGold());
                clanService.updateClan(clan);
            }
        } else {
            logger.warn("Clan with ID {} not found.", clanId);
        }
    }
}
