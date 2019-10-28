//Class blockchain represent a blockchain as a list of blocks
//The class also contains common operations on the Blockchain
import java.util.ArrayList;
public class Blockchain {
    //The blockchain is implemented as an ArrayList with blocks
    private ArrayList<Block> theChain;

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
        while (!((newBlock.getHash()).substring(0,2)).equals("00")){
            seed += 1;
            newBlock.setSalt(seed);
        }

        //when a valid salt is found add block to the chain
        theChain.add(newBlock);
    }
}

