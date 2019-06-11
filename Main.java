import java.util.Scanner;

public class Main
{
    private static int[] matrix = new int[9];

    // true - Player 1
    // false - Player 2
    private static boolean userSwitcher = false;

    /**
     * Стартовый метод программы
     * @param args
     */
    public static void main(String[] args)
    {
        for (int i = 0; i < matrix.length; i++)
            matrix[i] = 0;

        printBoard();

        for (int i = 0; i < matrix.length; i++)
        {
            userSwitcher = !userSwitcher;


            int[] userInput = getUserInput();
            matrix[userInput[0] - 1] = userInput[1];
            printBoard();
            checkBoard();
        }

        System.out.println("Draw!");
        System.exit(1);
    }

    private static void printBoard()
    {
        System.out.println();
        System.out.println(matrix[0] + " | " + matrix[1] + " | " + matrix[2]);
        System.out.println(matrix[3] + " | " + matrix[4] + " | " + matrix[5]);
        System.out.println(matrix[6] + " | " + matrix[7] + " | " + matrix[8]);
        System.out.println();
    }


    private static int[] getUserInput()
    {


        System.out.println("[" + (userSwitcher ? "Player 1" : "Player 2") +"] Enter col and num (1-9): ");

        Scanner scan = new Scanner(System.in);
        String[] value = scan.nextLine().split(" ");

        // Первое число номер, второе значение
        int col = Integer.parseInt(value[0]);
        int val = Integer.parseInt(value[1]);

        // Число должно быть в интервале от 0 до 9
        if (val < 0 || val > 9)
        {
            System.out.println("The number must be in the range from 1 to 9!");
            return getUserInput();
        }

        // Проверяем не занято-ли место
        // Если занято вызываем рекурсивно
        if (matrix[col - 1] != 0)
        {
            System.out.println("This cell is already taken!");
            return getUserInput();
        }

        for (int i = 0; i < matrix.length; i++)
        {
            if (matrix[i] == val)
            {
                System.out.println("This number has already been used!");
                return getUserInput();
            }
        }

        return new int[] {col, val};
    }


    private static void checkBoard()
    {
        for (int i = 0; i < matrix.length; i+= 3)
        {
            if (matrix[i] == 0 || matrix[i+1] == 0 || matrix[i+2] == 0)
                continue;

            if (matrix[i] + matrix[i+1] + matrix[i+2] == 15)
            {
                System.out.println((userSwitcher ? "Player 1" : "Player 2") + " is Winner!");
                System.exit(1);
            }
        }

        for (int i = 0; i < 3; i++)
        {
            if (matrix[i] == 0 || matrix[i+3] == 0 || matrix[i+6] == 0)
                continue;

            if (matrix[i] + matrix[i+3] + matrix[i+6] == 15)
            {
                System.out.println((userSwitcher ? "Player 1" : "Player 2") + " is Winner!");
                System.exit(1);
            }
        }

        if (matrix[0] != 0 && matrix[4] != 0 && matrix[8] != 0)
            if (matrix[0] + matrix[4] + matrix[8] == 15)
            {
                System.out.println((userSwitcher ? "Player 1" : "Player 2") + " is Winner!");
                System.exit(1);
            }

        if (matrix[2] != 0 && matrix[4] != 0 && matrix[6] != 0)
            if (matrix[2] + matrix[4] + matrix[6] == 15)
            {
                System.out.println((userSwitcher ? "Player 1" : "Player 2") + " is Winner!");
                System.exit(1);
            }
    }
}
