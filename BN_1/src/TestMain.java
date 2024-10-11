import org.w3c.dom.ls.LSOutput;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("test hallo");

        for (int i = 0; i < args.length; i++) {
            String sb = i +
                    ": " +
                    args[i];
            System.out.println(sb);
        }
    }
}