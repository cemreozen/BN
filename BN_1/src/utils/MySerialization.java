package utils;

import javax.xml.crypto.Data;
import java.io.*;

public class MySerialization {

    public void serialize(int[] intArray, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeInt(intArray.length);
        for (int j : intArray) {
            //System.out.println("written: " + j);
            out.write(j);
        }
    }

    public int[] deserialize(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        int length = dis.readInt();
        int[] intArray = new int[length];
        for (int i = 0; i < length; i++) {
            intArray[i] = dis.readInt();
            //System.out.println("read: " + intArray[i]);
        }
        return intArray;
    }

    public void fileMagicWriteFromFile(String fileName, OutputStream os) throws IOException {
        try {
            File file = new File(fileName);
            DataOutputStream dos = new DataOutputStream(os);
            long length = file.length();
            dos.writeLong(length);
            FileInputStream fis = new FileInputStream(file);
            for (int i = 0; i < length; i++) {
                os.write(fis.read());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public File fileMagicWriteToFile(String fileName, InputStream is, OutputStream os) throws IOException {
        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);
        long length = dis.readLong();
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fileName);
        dos.writeLong(length);
        for (int i = 0; i < length; i++)
            fos.write(dis.read());
        return file;
    }

        public void streamMagic(long laenge, InputStream is, OutputStream os) throws IOException {
        for (int l = 0; l < laenge; l++)
            os.write(is.read());
    }
}
