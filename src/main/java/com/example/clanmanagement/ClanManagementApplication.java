package com.example.clanmanagement;

import com.example.clanmanagement.Service.TaskService;
import com.example.clanmanagement.Service.UserAddGoldService;
import com.example.clanmanagement.Service.DatabaseClanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class ClanManagementApplication {

    public static void main(String[] args) {
        Connection dbConnection = createDatabaseConnection();

        DatabaseClanService databaseClanService = new DatabaseClanService(dbConnection);
        Logger logger = LoggerFactory.getLogger(TaskService.class);

        TaskService taskService = new TaskService(databaseClanService, logger);
        UserAddGoldService userAddGoldService = new UserAddGoldService(databaseClanService);

        long clanId = 1L;
        long taskId = 2L;
        int goldReward = 50;

        taskService.completeTask(clanId, taskId, goldReward);

        long userId = 42L;
        userAddGoldService.addGoldToClan(userId, clanId, goldReward);
    }

    private static Connection createDatabaseConnection() {
        return DatabaseConnectionManager.createConnection();
    }
}
