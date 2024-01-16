package com.example.clanmanagement.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@ToString
public class Clan {
    private long id;
    private String name;
    private final AtomicInteger gold;

    public Clan(long id, String name, int gold) {
        this.id = id;
        this.name = name;
        this.gold = new AtomicInteger(gold);
    }

    public int getGold() {
        return gold.get();
    }

    public void addGold(int amount) {
        while (true) {
            int currentGold = gold.get();
            int newGold = currentGold + amount;
            if (gold.compareAndSet(currentGold, newGold)) {
                break;
            }
        }
    }

    public void reduceGold(int amount) {
        while (true) {
            int currentGold = gold.get();
            int newGold = currentGold - amount;
            if (gold.compareAndSet(currentGold, newGold)) {
                break;
            }
        }
    }
}
