package menusystem;

import java.util.Scanner;

public class MenuHelper {
    private static Scanner input;

    static {
        input = new Scanner(System.in);
    }

    public MenuHelper() {
        input = new Scanner(System.in);
    }

    public static String readLine()
    {
        return input.nextLine();
    }

    public static int readInt()
    {
        return input.nextInt();
    }

    public static String readLine(String out)
    {
        System.out.print(out);
        return readLine();
    }

    public static int readInt(String out )
    {
        System.out.print(out);
        return readInt();
    }
}
