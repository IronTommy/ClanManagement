package com.example.clanmanagement;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.example.clanmanagement.Entity.Clan;
import com.example.clanmanagement.Service.ClanService;
import com.example.clanmanagement.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class TaskServiceTest {

    private ClanService clanService;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        clanService = mock(ClanService.class);
        Logger logger = Mockito.mock(Logger.class);
        taskService = new TaskService(clanService, logger);
    }

    @Test
    void testCompleteTaskSuccessfully() {
        ClanService clanService = mock(ClanService.class);
        Logger logger = mock(Logger.class);
        TaskService taskService = new TaskService(clanService, logger);

        Clan testClan = new Clan(1, "Test Clan", 100);
        Mockito.when(clanService.getClan(1)).thenReturn(testClan);

        taskService.completeTask(1, 1, 10);

        Mockito.verify(clanService).getClan(1);
        Mockito.verify(clanService).updateClan(Mockito.any(Clan.class));
        assertNotEquals(100, testClan.getGold(), "Gold in clan should change after completing a task");
    }

    @Test
    void testClanIsReal() {
        ClanService clanService = mock(ClanService.class);
        Logger logger = mock(Logger.class);
        TaskService taskService = new TaskService(clanService, logger);

        Mockito.when(clanService.getClan(1)).thenReturn(null);

        taskService.completeTask(1, 1, 10);

        Mockito.verify(clanService).getClan(1);
        Mockito.verify(clanService, Mockito.never()).updateClan(Mockito.any(Clan.class));
    }

    @Test
    void testCompleteTaskWithNullClan() {
        ClanService clanService = mock(ClanService.class);
        TestAppender testAppender = new TestAppender();

        Logger logger = (Logger) LoggerFactory.getLogger(TaskService.class);

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(testAppender);

        TaskService taskService = new TaskService(clanService, logger);

        Mockito.when(clanService.getClan(1)).thenReturn(null);

        taskService.completeTask(1, 1, 10);

        Mockito.verify(clanService, Mockito.never()).updateClan(Mockito.any(Clan.class));

        List<ILoggingEvent> loggingEvents = testAppender.getLoggingEvents();
    }

}
