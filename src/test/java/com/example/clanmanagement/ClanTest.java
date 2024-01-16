package com.example.clanmanagement;


import com.example.clanmanagement.Entity.Clan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClanTest {

    @Test
    void testAddGold() {
        Clan clan = new Clan(1, "Test Clan", 100);
        clan.addGold(50);
        assertEquals(150, clan.getGold(), "Adding gold should increase the clan's gold");
    }

    @Test
    void testReduceGold() {
        Clan clan = new Clan(1, "Test Clan", 100);
        clan.reduceGold(30);
        assertEquals(70, clan.getGold(), "Reducing gold should decrease the clan's gold");
    }
}
