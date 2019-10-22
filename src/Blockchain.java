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
        theChain.add(startBlock);
    }

    //Get block number i in the blockchain
    public Block getBlock(int i){return theChain.get(i);}

    //the number of blocks in the blockchain
    public int length(){return theChain.size();}

    //returns true is the blockchain is in a valid state
    public boolean verify(){
        //start by verifying all blocks

        //verify the chain of hash
        //The hash for block i should be equal to prevHash for block i-1

        return true;
    }


    //create a new block with data set to data
    public void addData(String data){
        //Create a new block and set all initial variables

        //modify salt until we get a salt such that after hashing
        //the hash value starts with 00

        //when a valid salt is found add block to the chain
    }
}

