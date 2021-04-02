package org.sxymi.ox;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        welcome();
    }

    public static void welcome(){
        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome in OX game created by Sxymi\n" +
                "Instructions:\n" +
                "\t1. Select fields from 1 - 9 to place X\n" +
                "\t2. Collect 3 X in row to win\n" +
                "\t3. If you want to exit the game select 0\n" +
                "\t4. Enjoy!\n");
        System.out.print("Do you want to start? [y/n] ");

        boolean loop = true;

        while(loop){
            switch (scn.next()){
                case "y":
                    game();
                    loop = false;
                    break;
                case "n":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input, please provide y or n.");
                    break;
            }
        }
    }

    public static void game(){
        System.out.println();

        int[] fields = new int[9];

        while(true) {
            showArena(fields);
            playerMove(fields);
            checkRound(fields, checkConditions(fields));
            botMove(fields);
            checkRound(fields, checkConditions(fields));
        }
    }

    public static void playerMove(int[] fields){
        System.out.print("\nPick free field [1 - 9] ");
        Scanner scn = new Scanner(System.in);

        boolean loop = true;

        while(loop){
            int choice = scn.nextInt() - 1;
            if(choice == -1) {
                System.exit(0);
            }
            else if(choice>=0 && choice<9){
                if(fields[choice] == 0){
                    fields[choice] = 1;
                    loop = false;
                }
                else{
                    System.out.println("Field is occupied, please select another.");
                }
            }
            else{
                System.out.println("You picked wrong field, please try again. Fields: 1 - 9, Exit: 0.");
            }
        }
    }

    public static void botMove(int[] fields){
        boolean loop = true;

        while(loop) {
            int choice = random();
            if (fields[choice] == 0) {
                fields[choice] = 2;
                loop = false;
            }
        }
    }

    public static void showArena(int[] fields){
        char[] symbols = new char[9];

        for(int i=0; i<fields.length; i++){
            if(fields[i] == 1) symbols[i] = 'X';
            else if(fields[i] == 2) symbols[i] = 'O';
            else symbols[i] = ' ';
        }
        System.out.println();
        System.out.println(symbols[0] + " | " + symbols[1] + " | " + symbols[2]);
        System.out.println(symbols[3] + " | " + symbols[4] + " | " + symbols[5]);
        System.out.println(symbols[6] + " | " + symbols[7] + " | " + symbols[8]);
    }

    public static int checkConditions(int[] fields){
        //1 2 3
        if(fields[0] == 1 && fields[1] == 1 && fields[2] == 1) return 1;
        else if(fields[0] == 2 && fields[1] == 2 && fields[2] == 2) return 2;

        //4 5 6
        else if(fields[3] == 1 && fields[4] == 1 && fields[5] == 1) return 1;
        else if(fields[3] == 2 && fields[4] == 2 && fields[5] == 2) return 2;

        //7 8 9
        else if(fields[6] == 1 && fields[7] == 1 && fields[8] == 1) return 1;
        else if(fields[6] == 2 && fields[7] == 2 && fields[8] == 2) return 1;

        //1 4 7
        else if(fields[0] == 1 && fields[3] == 1 && fields[6] == 1) return 1;
        else if(fields[0] == 2 && fields[3] == 2 && fields[6] == 2) return 2;

        //2 5 8
        else if(fields[1] == 1 && fields[4] == 1 && fields[7] == 1) return 1;
        else if(fields[1] == 2 && fields[4] == 2 && fields[7] == 2) return 2;

        //3 6 9
        else if(fields[2] == 1 && fields[5] == 1 && fields[8] == 1) return 1;
        else if(fields[2] == 2 && fields[5] == 2 && fields[8] == 2) return 2;

        //1 5 9
        else if(fields[0] == 1 && fields[4] == 1 && fields[8] == 1) return 1;
        else if(fields[0] == 2 && fields[4] == 2 && fields[8] == 2) return 2;

        //3 5 7
        else if(fields[2] == 1 && fields[4] == 1 && fields[6] == 1) return 1;
        else if(fields[2] == 2 && fields[4] == 2 && fields[6] == 2) return 2;

        //Draw
        int count = 0;
        for (int field: fields) {
            if (!(field == 0)) count++;
        }
        if(count == 9) return 3;

        return 0;
    }

    public static void checkRound(int[] fields, int check){
        if(check == 1){
            showArena(fields);
            System.out.println("You won! Congratulations!");
            System.exit(0);
        }
        else if(check == 2){
            showArena(fields);
            System.out.println("You lost! Better luck next time!");
            System.exit(0);
        }
        else if(check == 3) {
            showArena(fields);
            System.out.println("Draw! GG.");
            System.exit(0);
        }
    }

    public static int random(){
        long seed = System.currentTimeMillis();
        Random rnd = new Random(seed);
        return rnd.nextInt(8);
    }
}
