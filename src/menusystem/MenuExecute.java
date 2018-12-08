package menusystem;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MenuExecute {
    private Map<Integer, String> options;

    MenuExecute(Map<Integer, String> options)
    {
        this.options = options;
    }

    public Integer execute() {
        System.out.println(" - Válasszon menüpontot - ");
        for(int i = 0; i < options.size(); i++)
        {
            System.out.println(i+1 + " - " + options.get(i));
        }
        System.out.print("Bemenet: ");
        return getInput();
    }

    private Integer getInput()
    {
        try {
            return this.input();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Kérem próbálja újra.");
            System.out.print("Bemenet: ");
            this.getInput();
        }
        return 0;
    }

    private Integer input() throws Exception {
        Scanner input = new Scanner(System.in);
        int result = 0;
        try {
            result = input.nextInt();
        } catch (Exception e)
        {
            throw new Exception("ERROR: NaN");
        }

        return result;
    }
}
