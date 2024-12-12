package tcp;

import java.io.*;

public class fileMagicEfficient {

    public void fileMagicEfficien1(String fileName, InputStream is, OutputStream os) throws IOException {
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

    public byte[] readFileIntoByteArray(File file) throws IOException {
        int length = (int) file.length();
        byte[] buffer = new byte[length];
        InputStream is = new FileInputStream(file);
        for (int i = 0; i < length; i++) {
            buffer[i] = (byte) is.read(buffer);
        }
        return buffer;
    }
}