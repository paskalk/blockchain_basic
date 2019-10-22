//BlockMain contains the main method and will be the starting point of the application
//In the application the user will be presented by a menu
//From the menu a user can select the following operations on the blockchain; add data, verify and print.
import java.util.Scanner;
public class BlockMain {

    //Print the menu with the different choices
    public static void printMenu(){
        System.out.println();
        System.out.println("*****************************************************");
        System.out.println("Choose an alternative:");
        System.out.println("1. Add data");
        System.out.println("2. Verify the blockchain");
        System.out.println("3. Print the blockchain");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("*****************************************************");
        System.out.println();
    }


    //read a string of text from the user (System.in), end with an empty line
    public static String readData(){
        System.out.println("Enter data. End with empty line");
        String data = "";
        Scanner in = new Scanner(System.in);
        String indata = in.nextLine();
        while(!indata.equals("")){
            data += indata;
            data += "\n";
            indata = in.nextLine();
        }
        return data;
    }


    //Main: Init blockchain, then print a menu, read and interpreter user choice and repeat
    public static void main(String[] args){
        System.out.println("Welcome to my simple Blockchain");
        //init the blockchain
        Blockchain chain = new Blockchain();
        //run the main loop
        boolean stop = false;
        Scanner in = new Scanner(System.in);
        while(!stop){
            printMenu();
            int alt = in.nextInt();
            switch(alt) {
                case 1:
                    //read data from the user and add to the blockchain
                    String data = readData();
                    chain.addData(data);
                    break;
                case 2:
                    //verify the blockchain and print status
                    boolean status = chain.verify();
                    if(status){
                        System.out.println("The chain s valid");
                    }
                    else {
                        System.out.println("The chain contains some errors");
                    }
                    break;
                case 3:
                    //print the entire blockchain (print all blocks)
                    for(int i=0;i<chain.length();++i)
                        chain.getBlock(i).print();
                    break;
                case 0:
                    //exit the main loop
                    stop = true;
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

}
