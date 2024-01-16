package com.example.clanmanagement;

import com.example.clanmanagement.Entity.Clan;
import com.example.clanmanagement.Service.ClanService;
import com.example.clanmanagement.Service.UserAddGoldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class UserAddGoldServiceTest {

    private ClanService clanService;
    private UserAddGoldService userAddGoldService;

    @BeforeEach
    void setUp() {
        clanService = mock(ClanService.class);
        userAddGoldService = new UserAddGoldService(clanService);
    }

    @Test
    void testAddGoldToClan() {
        when(clanService.getClan(1)).thenReturn(new Clan(1, "Test Clan", 100));
        userAddGoldService.addGoldToClan(1, 1, 50);
        verify(clanService).getClan(1);
        verify(clanService).updateClan(any(Clan.class));
    }

    @Test
    void testClanIsUnreal() {
        when(clanService.getClan(1)).thenReturn(null);
        userAddGoldService.addGoldToClan(1, 1, 50);
        verify(clanService).getClan(1);
        verify(clanService, never()).updateClan(any(Clan.class));
    }
}
