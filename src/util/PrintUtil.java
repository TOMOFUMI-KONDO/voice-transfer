package util;

public class PrintUtil {
    public static void printException(Exception e) {
        System.out.println(e.getClass().toString() + ": " + e.getMessage());
        e.printStackTrace();
    }
}
