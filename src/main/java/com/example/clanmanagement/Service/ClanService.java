package com.example.clanmanagement.Service;

import com.example.clanmanagement.Entity.Clan;

public interface ClanService {
    Clan getClan(long clanId);
    void updateClan(Clan clan);
}
