package ru.mirea.pkmn;

import java.io.Serializable;

public class AttackSkill implements Serializable {
    public static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String cost;

    private int damage;

    public AttackSkill(String name, String cost, int damage) {
        this.name = name;
        this.description = "";
        this.cost = cost;
        this.damage = damage;
    }

    public AttackSkill() {
    }

    @Override
    public String toString() {
        return "AttackSkill{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost='" + cost + '\'' +
                ", damage=" + damage +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}