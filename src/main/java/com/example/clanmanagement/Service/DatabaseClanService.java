package com.example.clanmanagement.Service;

import com.example.clanmanagement.Entity.Clan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseClanService implements ClanService {

    private static final Logger logger = LoggerFactory.getLogger(Clan.class);
    private Connection connection;

    public DatabaseClanService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Clan getClan(long clanId) {
        String query = "SELECT id, name, gold FROM clans WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, clanId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int gold = resultSet.getInt("gold");
                return new Clan(id, name, gold);
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve clan from the database.", e);
        }
        return null;
    }

    @Override
    public void updateClan(Clan clan) {
        String query = "UPDATE clans SET name = ?, gold = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, clan.getName());
            statement.setInt(2, clan.getGold());
            statement.setLong(3, clan.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to update clan in the database.", e);
        }
    }

}

