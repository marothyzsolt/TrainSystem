package menusystem;

import java.util.Scanner;

public class MenuHelper {
    private Scanner input;
    private static MenuHelper instance;

    public static MenuHelper getInstance() {
        if(instance == null)
            instance = new MenuHelper();
        return instance;
    }

    private MenuHelper() {
        input = new Scanner(System.in);
    }

    public String readLine()
    {
        return new Scanner(System.in).nextLine();
    }

    public int readInt()
    {
        return new Scanner(System.in).nextInt();
    }

    public String readLine(String out)
    {
        System.out.print(out);
        return readLine();
    }

    public int readInt(String out )
    {
        System.out.print(out);
        return readInt();
    }
}
