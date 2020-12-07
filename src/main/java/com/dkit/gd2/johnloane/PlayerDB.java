package com.dkit.gd2.johnloane;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerDB
{
    private ArrayList<Player> players;
    private static Scanner keyboard = new Scanner(System.in);

    public PlayerDB()
    {
        this.players = new ArrayList<>();
    }

    protected void loadPlayerFromFile()
    {
        try(Scanner playersFile = new Scanner(new BufferedReader(new FileReader("players.txt"))))
        {
            String input;
            while(playersFile.hasNextLine())
            {
                input = playersFile.nextLine();
                String[] data = input.split(",");
                String name = data[0];
                int hitPoints = Integer.parseInt(data[1]);
                int strength = Integer.parseInt(data[2]);
                String weapon = data[3];

                Player readInPlayer = new Player(name, hitPoints, strength, weapon);
                this.players.add(readInPlayer);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println(Colours.PURPLE + "Could not load players. If this is the first time running the program this could be fine" + Colours.RESET);
        }

    }

    public void savePlayersToFile()
    {
        try(BufferedWriter playersFile = new BufferedWriter(new FileWriter("players.txt")))
        {
            for(Player player : players)
            {
                playersFile.write(player.getName()+","+player.getHitPoints()+","+player.getStrength()+","+player.getWeapon());
                playersFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED+"Could not save the players"+Colours.RESET);
        }
    }

    public void addPlayer()
    {
        String name = enterField("name");
        boolean loop = true;
        int hitPoints = loopUntilValidIntEntry("hit points");
        int strength = loopUntilValidIntEntry("strength");
        String weapon = enterField("weapon");
    }

    private int loopUntilValidIntEntry(String intField)
    {
        boolean loop = true;
        while(loop)
        {
            try
            {
                if(intField.equals("hit points"))
                {
                    int hitPoints = Integer.parseInt(enterField(intField));
                    return hitPoints;
                }
                else if(intField.equals("strength"))
                {
                    int strength = Integer.parseInt(enterField(intField));
                    return strength;
                }
            } catch (NumberFormatException nfe)
            {
                System.out.println(Colours.RED + "Please enter an integer for the hit points and strength variables" + Colours.RESET);
            }
        }
        return -1;
    }

    private String enterField(String field)
    {
        String input;
        System.out.print("Please enter player " + field + " :>");
        input = keyboard.nextLine();
        return input;
    }

    public void deletePlayer()
    {
        if(this.players != null)
        {
            String nameToDelete = enterField("name to delete");
            Player playerToDelete = findPlayer(nameToDelete);
            if(playerToDelete != null)
            {
                players.remove(playerToDelete);
            }
            else
            {
                System.out.println(Colours.RED + "That player does not exist" + Colours.RESET);
            }
        }
    }

    private Player findPlayer(String nameToDelete)
    {
        for(Player player : players)
        {
            if(player.getName().equals(nameToDelete))
            {
                return player;
            }
        }
        return null;
    }

    public void printPlayer()
    {
        String nameToPrint = enterField("name to print");
        Player playerToPrint = findPlayer(nameToPrint);
        if(playerToPrint != null)
        {
            System.out.println(playerToPrint);
        }
        else
        {
            System.out.println(Colours.RED + "That player does not exist" + Colours.RESET);
        }
    }
}
