package utils;

import java.io.*;

public class MySerialization {

    public void serialize(int[] intArray, OutputStream out) throws IOException {
        int length = intArray.length;
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeInt(length);
        for (int j : intArray) {
            System.out.println("written: " + j);
            out.write(j);
        }
    }

    public int[] deserialize(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        int length = dis.readInt();
        int[] intArray = new int[length];
        for (int i = 0; i < length; i++) {
            intArray[i] = in.read();
            System.out.println("read: " + intArray[i]);
        }
        return intArray;
    }

    public void fileMagicEfficient1(String fileName, InputStream is, OutputStream os) throws IOException {
        try {
            String name = fileName;
            File file = new File(fileName);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeLong(file.length());
            is = new FileInputStream(file);
            os.write(is.read());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void fileMagicEfficient2(String fileName, InputStream is, OutputStream os) throws IOException {
        DataInputStream dis = new DataInputStream(is);
        long length = dis.readLong();
        String fileNameTarget = fileName;
        FileOutputStream fos = new FileOutputStream(fileNameTarget);
        os.write((int) length); //warum benutz ich das nicht? und auch keine schleife... ??
        fos.write(is.read());
    }
}
