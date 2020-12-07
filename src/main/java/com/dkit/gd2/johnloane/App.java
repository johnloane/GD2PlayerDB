package com.dkit.gd2.johnloane;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * PlayerDB - help with CA3
 *
 */
public class App 
{
    private static Scanner keyboard = new Scanner(System.in);
    public static void main( String[] args )
    {
        App playerDBApp = new App();
        playerDBApp.start();
    }

    private void start()
    {
        System.out.println(Colours.BLUE + "Welcome to the playerDB App" + Colours.RESET);
        PlayerDB playerDB = new PlayerDB();
        playerDB.loadPlayerFromFile();
        doMainMenuLoop(playerDB);
        playerDB.savePlayersToFile();
    }

    private void doMainMenuLoop(PlayerDB playerDB)
    {
        boolean loop = true;
        MainMenu menuOption;
        int option = -1;
        while(loop)
        {
            printMainMenu();
            try
            {
                String input = keyboard.nextLine();
                if(input.isEmpty() || input.length() > 1)
                {
                    throw new IllegalArgumentException();
                }
                else
                {
                    option = Integer.parseInt(input);
                }

                if(option < 0 || option >= MainMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
                //keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch(menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case DISPLAY_PLAYER_MENU:
                        doPlayerMainMenuLoop(playerDB);
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
                keyboard.nextLine();
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
        System.out.println(Colours.BLUE + "Thanks for using the app" + Colours.RESET);
    }

    private void doPlayerMainMenuLoop(PlayerDB playerDB)
    {
        boolean loop = true;
        PlayerMainMenu menuOption;
        int option;
        while (loop)
        {
            printPlayerMainMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = PlayerMainMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_PLAYER_MENU:
                        loop = false;
                        break;
                    case ADD_PLAYER:
                        playerDB.addPlayer();
                        break;
                    case DELETE_PLAYER:
                        playerDB.deletePlayer();
                        break;
                    case PRINT_PLAYER:
                        playerDB.printPlayer();
                        break;
                }
            } catch (InputMismatchException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
    }

    private void printPlayerMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < PlayerMainMenu.values().length;i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + PlayerMainMenu.values()[i].toString()+Colours.RESET);
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < MainMenu.values().length;i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + MainMenu.values()[i].toString()+Colours.RESET);
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }
}
