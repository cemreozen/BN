package tcp_test;

import org.junit.Test;
import utils.MySerialization;

import java.io.*;

public class fileTest {
    @Test
    public void testFile() throws IOException {
        String sampleData = "TestContent";
        String fileNameSource = "sourceFile.txt";

        String fileNameTarget = "targetFile.txt";

        DataOutputStream daos = new DataOutputStream(new FileOutputStream(fileNameSource));
        daos.writeUTF(sampleData);

        File file = new File(fileNameSource);
        MySerialization ms = new MySerialization();

        // hier geschieht leider *noch* keine Magie....... :\

        }
    }
}