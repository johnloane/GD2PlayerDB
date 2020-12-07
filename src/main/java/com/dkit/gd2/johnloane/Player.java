package com.dkit.gd2.johnloane;

public class Player
{
    private String name;
    private int hitPoints;
    private int strength;
    private String weapon;

    public Player(String name, int hitPoints, int strength, String weapon)
    {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = weapon;
    }

    public String getName()
    {
        return name;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public int getStrength()
    {
        return strength;
    }

    public String getWeapon()
    {
        return weapon;
    }

    @Override
    public String toString()
    {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                '}';
    }
}
