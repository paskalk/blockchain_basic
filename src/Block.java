//Class block represents one block in the blockchain
//Each block contains the following
//counter, a number that should be incremented with each block
//prevHash, a string that contains the hashvalue of the previous block
//data, a string of data
//salt, an array of 8 bytes, selected such that the hash has desired properties
//string, hash value of the block
public class Block {
    private int counter;
    private String prevHash;
    private String data;
    private byte[] salt;
    private String hash;

    //create a new block with given id, data and previous hash
    //The salt is created as 8 bytes with only zeros
    //After assigning values the hash is calculated
    public Block(int id, String blockData, String inHash){

    }

    //Print a block, by printing all its attributes
    public void print(){
        System.out.println("----------------------------------------");
        System.out.print("Block: ");
        System.out.println(counter);
        System.out.println("Previous Hash-value");
        System.out.println(prevHash);
        System.out.println("Data: ");
        System.out.println(data);
        System.out.println("Salt");
        System.out.println(BlockUtils.bytes2hexString(salt));
        System.out.println("Hash: ");
        System.out.println(hash);
    }


    //verify that the hash of the content equals the stored hash
    public boolean verify(){
        //Hash the attributes of the block (counter, prevHash, data, salt)
        //compare the output from the Hashfunction with the stored hash
        return true;
    }


    //Calculate the hash  value of the Block
    public void generateHash(){
        //create the input to the hashfunction as the concatenation of the following attributes written as strings
        //counter, prevHash, data, salt

        //input to the hashfunction and store in hash

    }

    //return the data in the block
    public String getData(){return data;}

    //return the hashValue
    public String getHash(){return hash;};

    //return the previous hash value
    public String getPrevHash(){return prevHash;}

    //set new salt value and update the hash to take new salt into account
    public void setSalt(byte[] newSalt){
        for(int i=0;i<8;++i)
            salt[i]=newSalt[i];
        generateHash();
    }

}


