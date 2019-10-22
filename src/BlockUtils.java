import java.security.MessageDigest;

//BlockUtils contains some functions that are useful when implementing a BlockChain
//The description of each function is written together with the functions
public class BlockUtils {

    //convert an integer into an array of 4 bytes
    //Example n = 0x1337bad0 bytes = {0x13, 0x37, 0xba, 0xd0}
    public static byte[] int2bytes(int n){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)((n>>24) & 0xff);
        bytes[1] = (byte)((n>>16) & 0xff);
        bytes[2] = (byte)((n>>8) & 0xff);
        bytes[3] = (byte)(n & 0xff);
        return bytes;
    }

    //Takes an array of bytes and produce a string containing the bytes written in hexadecimal
    //Example: bytes = {0x13, 0x37, 0xba, 0xd0} String = "1337bad0"
    public static String bytes2hexString(byte[] bytes){
        String out = "";
        String hexChar = "0123456789abcdef";
        for (byte b:bytes) {
            out += hexChar.charAt((b>>4)&0xf);
            out += hexChar.charAt(b&0xf);
        }
        return out;
    }

    //Hash a String and return the hashvalue as a hexadecimal string
    //The hashfunction used in the function is the SHA-256 algorithm
    public static String hash(String data){
        String out = "";
        //need try catch for the getInstance method
        try {
            //transform the string data to a byte array
            byte[] dataBytes = data.getBytes();
            //Hash with SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashvalue = md.digest(dataBytes);
            out = bytes2hexString(hashvalue);
        }
        catch (Exception e){
            System.err.println("Exception in hash");
        }
        return out;
    }

}
