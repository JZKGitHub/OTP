import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {

        Crypto crypto = new Crypto();
        byte messageBytes[] = crypto.readInput("Message.txt", 0); // get bytes of message
        byte keyBytes[] = crypto.convertKeyBytes("Key.txt", messageBytes.length);// get bytes of key depending on message length
        byte encryptedBytes[] = crypto.DNcryption(keyBytes, messageBytes);// encrypt message and get bytes
        crypto.writeOutput(encryptedBytes, "Encrypted");// write message i to Encrypted.txt


        byte messageBytes1[] = crypto.readInput("Encrypted.txt", 0);
        byte keyBytes1[] = crypto.convertKeyBytes("Key.txt", messageBytes1.length);
        byte encryptedBytes1[] = crypto.DNcryption(keyBytes1, messageBytes1);
        crypto.writeOutput(encryptedBytes1, "Decrypted");


        crypto.createHeader();


        // System.out.println(BigInteger.valueOf(2048).toByteArray());
        //System.out.println(Integer.toBinaryString(32768).getBytes()[0]);
        //int stringToInt = Integer.parseInt("1", 2);  // convert Binary String to Int

//                 byte intToByte = (byte)stringToInt;
        //byte ar[0]=intToByte;
        //               System.out.println(stringToInt);

        // byte[] aByteArray = {0xa,0x2,0xf,(byte)0xff,(byte)0xff,(byte)0xff};
       /* keyBytes=crypto.readInput("Key.txt",0);
        int width = 50;
        int height = 50;

        DataBuffer buffer = new DataBufferByte(keyBytes, keyBytes.length);
        //3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[] {0, 1, 2}, (Point)null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        System.out.println(cm.getPixelSize()+"SIZEOFPIXEL");
        BufferedImage image = new BufferedImage(cm, raster, true, null);

        ImageIO.write(image, "png", new File("image.png"));

*/
    }
}

