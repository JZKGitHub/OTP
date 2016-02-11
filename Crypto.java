import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by 1337 on 09.02.2016.
 */
public class Crypto {

/**DNcrytion is for Encrytion and Decryption. Arguments are 2 byte arrays with equal length. After XORing  byte by byte , message with  key, there is
 * a cast to byte necessary. */
    public byte[]DNcryption(byte[]key,byte[] mssg){

        if(mssg.length==key.length) {
            byte[] encryptedBytes= new byte[key.length];

            for(int i=0;i<key.length;i++){
                encryptedBytes[i]=(byte)(mssg[i]^key[i]);// XOR encryption with random keys, cast back to byte
                System.out.print( (char)encryptedBytes[i]);
            }
            return encryptedBytes;
        }
        else {
            System.out.println("Key and message not equal bytes");
            return null;
        }

    }



/**Read a textfile with 0s and 1s and use them as binary .Every digit in textfile is a byte, but it represents a bit in binary.
   1s or 0s as char represent 48 and 49 in byte. This function converts a array of bytes containing 0s and 1s to a 8 bit Binary String e.g("10100010"),
   then to an integer and then cast integer to byte.
   Argument int keylenght is multiplied by 8 in readInput(keyFile,keylenght), because it is in bytes. Every byte in our textfile is converted to 1 bit.


 test
 String Binary = stringBuilder.toString();
 System.out.println(Binary);
 Binary="01000000";
 int test =  new BigInteger(Binary, 2).intValue();
 System.out.println(test+"trs+");
 convertedKeyBytes = new BigInteger(Binary, 2).toByteArray();
 for(int i = 0;i<convertedKeyBytes.length;i++) {
 System.out.println(convertedKeyBytes[i] + "i:" + i + "-----" + keylength);
 }

 **/
    public byte[] convertKeyBytes(String keyFile, int keylength) throws IOException
    {

        byte keyBytesfromFile[]=readInput(keyFile,keylength);          // readinput() returns (keylength*8) bytes,contains 48 or 49s.
        StringBuilder stringBuilder = new StringBuilder();             //Stringbuilder append chars to build String
        byte convertedKeyBytes[]=new byte[keyBytesfromFile.length/8];            //size of
        int stringToInt;
        byte intToByte;
        for(int indexKey=0,bitcounter=1,indexCoverted=0;indexKey<keyBytesfromFile.length;indexKey++,++bitcounter)
        {

            stringBuilder.append((char) keyBytesfromFile[indexKey]); //append 48 or 49 casted to char


            if(bitcounter==8)
            {
                String Binary = stringBuilder.toString();   // if 8 bits appended, toString
                System.out.println("-"+Binary+"-");
                stringToInt = Integer.parseInt(Binary, 2);  // convert Binary String to Int
                intToByte = (byte)stringToInt;              // cast Int to byte, cast necessary,direct cast from String to byte fails
                convertedKeyBytes[indexCoverted++]=intToByte;
                bitcounter=0;
                stringBuilder.setLength(0);                 //set length to 0, to append next 8 bit.
            }

        }
                  /**t**/
                //System.out.println("keyrequest + keyresceived"+keylength+"."+convertedKeyBytes.length);
            return convertedKeyBytes;
    }



/**read message in plaintext or ciphertext and return bytes[] if sizeofkey ==0 .Or read keys with sizefofKey from file
 * with characters 0s and 1s in combination with convertKeyBytes function*/


    public byte[] readInput(String File,int sizeofKey) throws IOException
    {
        StringBuffer buffer = new StringBuffer();

        try
        {
            FileInputStream fis = new FileInputStream(File);
            InputStreamReader isr = new InputStreamReader(fis);//utf8
            Reader in = new BufferedReader(isr);
            int character;
            int keySizeCount=0;
            Path path = Paths.get(File);

            if(sizeofKey==0)                                        //if sizeofKey == 0 , its a message to read not a key
            {
                byte messageBytes[]=Files.readAllBytes(path);      //read allBytes from message
                in.close();
                return messageBytes;                               //return message bytes
            }else                                                  //else it is a key to read
                {
                    while ( keySizeCount++!= sizeofKey*8) {                // read keys as char . 1 byte = 1 bit in txt,chars 0 and 1
                        character = in.read();                         //read char by char
                        if(character==32||character==13||character==10)              // Space, CR , LF, TO DO !1,!0
                        {
                            keySizeCount-=1;
                        }else {
                            buffer.append((char) character);
                        }
                    }
                    in.close();
                    return  buffer.toString().getBytes();
                }
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }








    public void writeOutput(byte encrypted [],String file) {
        try {
            Path path = Paths.get(file+".txt");
            Files.write(path, encrypted, StandardOpenOption.CREATE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void createHeader() {

        int messagesize;
        messagesize = 400;
        String binaryMessageSize = Integer.toBinaryString(messagesize);
        StringBuilder stringb = new StringBuilder();
        if (binaryMessageSize.length() <= 8) {
            /*****do whatevs*/
        } else {

            int index2ndByte = binaryMessageSize.length() - 8;
            for (; index2ndByte <= binaryMessageSize.length() - 1; index2ndByte++) {
                stringb.append(binaryMessageSize.charAt(index2ndByte));
            }
            int secondByte = Integer.parseInt(stringb.toString(), 2);  // convert Binary String to Int
            System.out.println(secondByte);//2ndbyte
            stringb.setLength(0);
            index2ndByte = binaryMessageSize.length() - 8;
            for (int i = 0; i <= index2ndByte - 1; i++) {
                stringb.append(binaryMessageSize.charAt(i));
            }
            int firstByte = Integer.parseInt(stringb.toString(), 2);
            System.out.println(firstByte);//1stbyte
            String a =Integer.toBinaryString(firstByte);
            String b =Integer.toBinaryString(secondByte);
            a+=b;
            System.out.println(Integer.parseInt(a,2));//400
            for (int i = 0; i <= binaryMessageSize.length() - 1; i++) {
                System.out.print(binaryMessageSize.charAt(i));
            }
            // binaryMessageSize.charAt(0); 11001000
        }
    }

}






















