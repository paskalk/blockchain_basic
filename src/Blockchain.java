//Class blockchain represent a blockchain as a list of blocks
//The class also contains common operations on the Blockchain
import java.util.ArrayList;
public class Blockchain {
    //The blockchain is implemented as an ArrayList with blocks
    private ArrayList<Block> theChain;
    private long startTime = 0;
    private long totalTime = 0;
    private int totalSeeds = 0;
    private String numberOfZeros = "0";
    private int loopCount = 5;


    //Create a new blockchain and set the starting block
    public Blockchain(){
        //In the beginning the blockchain contains only the initial block
        theChain = new ArrayList<Block>(1);
        //The empty block contains the empty string as data, and previous block empty
        Block startBlock = new Block(0,"",""); //id 0, no data and empty previous hash
        /*  start Weird experiment*/
        startBlock.generateHash();
        int seed = 0;
        while (!((startBlock.getHash()).substring(0,2)).equals("00")){
            seed += 1;
            startBlock.setSalt(seed);
        }
        /*End Weird experiment*/
        theChain.add(startBlock);
    }

    //Get block number i in the blockchain
    public Block getBlock(int i){return theChain.get(i);}

    //the number of blocks in the blockchain
    public int length(){return theChain.size();}

    //returns true is the blockchain is in a valid state
    public boolean verify(){
        //start by verifying all blocks
        for (int i=0; i<length(); i++){
            if(!getBlock(i).verify()){
                return false; //Individual hash doesn't match
            }
        }

        //verify the chain of hash
        //The hash for block i should be equal to prevHash for block i-1
        for (int i=length()-1; i > 0; i--){
            if(!((getBlock(i).getPrevHash()).equals(getBlock(i-1).getHash())) || !((getBlock(i).getPrevHash().substring(0,2)).equals("00"))){
                return false;
            }
        }
        return true;
    }


    //create a new block with data set to data
    public void addData(String data){

        String lastHash = getBlock(length()-1).getHash();// theChain.get(length()).getHash();
        int newId = length();

        //Create a new block and set all initial variables
        Block newBlock = new Block(newId, data, lastHash);
        newBlock.generateHash();

        //modify salt until we get a salt such that after hashing
        //the hash value starts with 00
        int seed = 0;
        startTimer();
        while (!((newBlock.getHash()).substring(0,numberOfZeros.length())).equals(numberOfZeros)){
            seed += 1;
            newBlock.setSalt(seed);
        }

        totalTime = totalTime + stopTimer();
        totalSeeds = totalSeeds + seed;

        //when a valid salt is found add block to the chain
        theChain.add(newBlock);
    }

    //modify Block: 2 and by simply setting data
    public void simpleDataAssignment(){
        getBlock(1).setData("Injected Data");
    }

    //modify Block: 2 by modifying data, salt & hashvalue
    public void improvedAttack(){
        Block blockToModify = getBlock(1);
        blockToModify.setData("Injected Data");

        blockToModify.generateHash();

        int seed = 0;
        while (!((blockToModify.getHash()).substring(0,2)).equals("00")){
            seed += 1;
            blockToModify.setSalt(seed);
        }
    }

    //modify Block: 2 (& subsequent blocks) by modifying data, salt, hashvalue & previous hashvalue in next block
    public void succesfulAttack(int positionToModify){

        //Update modified block first
        Block blockToModify = getBlock(positionToModify);
        blockToModify.setData("Injected Data");

        blockToModify.generateHash();

        int seed = 0;
        while (!((blockToModify.getHash()).substring(0, 2)).equals("00")) {
            seed += 1;
            blockToModify.setSalt(seed);
        }

        for (int i=positionToModify+1; i < length();i++) {
            getBlock(i).setPreviousHash(getBlock(i-1).getHash());

            getBlock(i).generateHash();
            int innerSeed = 0;
            while (!((getBlock(i).getHash()).substring(0, 2)).equals("00")) {
                innerSeed += 1;
                getBlock(i).setSalt(innerSeed);
            }

        }
    }

    //Record current start time
    public void startTimer(){
        startTime = System.currentTimeMillis();
    }

    //Get time difference between the start and end time (in milliseconds)
    public long stopTimer(){
        return (System.currentTimeMillis() - startTime);
    }

    public void results(){
        System.out.println("Average seeds: " + totalSeeds/loopCount);
        System.out.println("Average Time: " + (totalTime/loopCount)) ;
    }

    public void setZeros(String zeros, int divideBy){
        numberOfZeros = zeros;
        loopCount = divideBy;
    }
}

