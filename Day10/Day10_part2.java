
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day10_part2 {
  private static final String DATA_LOCATION = "Day10/Day10-data.txt";
  private static ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();

  public static void main(String[] args) throws FileNotFoundException {
    data = parseData();

    int sumOfScores = 0;

    for (int j = 0; j < data.size(); j++) {
      for (int k = 0; k < data.get(0).size(); k++) {
        if (data.get(j).get(k) == 0) {
          List<List<Integer>> found = countNeighborHills(j, k);
          sumOfScores += found.size();
        }
      }
    }

    System.out.println(sumOfScores);
  }

  private static ArrayList<ArrayList<Integer>> parseData() throws FileNotFoundException {
    ArrayList<ArrayList<Integer>> parsedData = new ArrayList<ArrayList<Integer>>();

    File file = new File(DATA_LOCATION);
    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      ArrayList<Integer> lineList = new ArrayList<Integer>();

      for (char character : line.toCharArray()) {
        lineList.add(Integer.parseInt(String.valueOf(character)));
      }

      parsedData.add(lineList);
    }

    scanner.close();

    return parsedData;
  }

  private static boolean isValidNeighbor(int j, int k) {
    return (0 <= j && j < data.size()) && (0 <= k && k < data.get(0).size());
  }

  private static boolean isUphillNeighbor(int j, int k, int currentValue) {
    return (isValidNeighbor(j, k)) && (currentValue + 1 == data.get(j).get(k));
  }

  private static List<List<Integer>> countNeighborHills(int j, int k) {
    int currentVal = data.get(j).get(k);
    
    List<List<Integer>> indices = new ArrayList<List<Integer>>();

    if (currentVal == 9) {
      indices.add(Arrays.asList(j, k));

      return indices;
    }

    if (isUphillNeighbor(j + 1, k, currentVal)) {
      indices.addAll(countNeighborHills(j + 1, k));
    }
    if (isUphillNeighbor(j - 1, k, currentVal)) {
      indices.addAll(countNeighborHills(j - 1, k));
    }
    if (isUphillNeighbor(j, k + 1, currentVal)) {
      indices.addAll(countNeighborHills(j, k + 1));
    }
    if (isUphillNeighbor(j, k - 1, currentVal)) {
      indices.addAll(countNeighborHills(j, k - 1));
    }

    return indices;
  }
}
