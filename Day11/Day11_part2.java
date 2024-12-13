import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11_part2 {
    private static final String DATA_LOCATION = "Day11/Day11-data.txt";
    private static ArrayList<BigInteger> data = new ArrayList<BigInteger>();

    public static void main(String[] args) throws FileNotFoundException {
        data = parseData();

        System.out.println("Base stones:");
        printStones(data);

        ArrayList<BigInteger> currentList = data;

        for (int itter = 1; itter <= 75; itter++) {
            if (itter % 5 == 0) { System.out.println("Itteration " + itter); }

            currentList = mutateStones(currentList);
            // printStones(currentList);
        }

        // printStones(currentList);

        System.out.println("Final stone length: " + currentList.size());
    }

    private static ArrayList<BigInteger> parseData() throws FileNotFoundException {
        ArrayList<BigInteger> parsedData = new ArrayList<BigInteger>();

        File file = new File(DATA_LOCATION);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextInt()) {
            BigInteger writtenNum = scanner.nextBigInteger();
            parsedData.add(writtenNum);
        }

        scanner.close();

        return parsedData;
    }

    private static void printStones(ArrayList<BigInteger> list) {
        System.out.print("Stones:");

        for (BigInteger num : list) {
            System.out.print(" " + num);
        }

        System.out.println();
    }

    private static ArrayList<BigInteger> mutateStones(ArrayList<BigInteger> previousList) {
        ArrayList<BigInteger> newList = new ArrayList<BigInteger>();

        for (BigInteger num : previousList) {
            // If num is 0
            if (num.equals(new BigInteger("0"))) {
                newList.add(new BigInteger("1"));
                continue;
            }

            // If the number of digits composing num are even
            String numStr = String.valueOf(num);
            if (numStr.length() % 2 == 0) {
                newList.add(new BigInteger(numStr.substring(0, numStr.length() / 2))); // Add a stone composed of the first half of the digits
                newList.add(new BigInteger(numStr.substring(numStr.length() / 2, numStr.length()))); // Add a stone composed of the second half of the digits
                continue;
            }

            newList.add(num.multiply(new BigInteger("2024")));
        }

        return newList;
    }
}
