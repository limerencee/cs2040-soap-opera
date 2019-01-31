import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toMap;

class Main {

    static int _N;
    static int _M;
    static HashMap<Integer, List<Candy>> _candies;
    static Candy[][] _grid;

    /**
    * Reads user input N and M to initialize row and column of the Candy grid.
    * Reads NxM number of input to initialize _grid.
    * For modularity, _candies is designed to store all occurences of itself in List<V>
    * with type <K> being the type of candy.
    *
    * After initializing all the variables, check within  _grid<0, List<Candy>>
    * to see if there are colorful candies within its cardinals. If true, NxM is
    * returned as the grid is wiped.
    *
    * Sort _candies<Integer, List<Candy>> according to List<Candy>.size() in descending
    * order. Starting from index 0, check against all colorful candies to see if
    * there is a match. Proceed to next index if all colorful candies are not in
    * the cardinals.
    *
    * @return number of maximum candies that can be crushed.
    */
    static int maximumCandies() {
        Scanner sc = new Scanner(System.in);
        _N = sc.nextInt();
        _M = sc.nextInt();
        _candies = new HashMap<>();
        _grid = new Candy[_N][_M];

        //Read and initialize _candies as well as candy _grid.
        for (int i = 0; i < _N; i++) {
            for (int j = 0; j < _M; j++) {
                int candyType = sc.nextInt() + 1;
                Candy candy = new Candy(candyType, i, j);
                List<Candy> candyList = _candies.get(candyType);
                if (candyList == null) candyList = new ArrayList<>();
                candyList.add(candy);
                _candies.put(candyType, candyList);
                _grid[i][j] = candy;
            }
        }

        //Check all colorful candies to see if there are any next to each other.
        //_candies.get(0) = colorful Candy
        for (int i = 0; i < _candies.get(0).size() - 1; i++) {
            Candy one = _candies.get(0).get(i);
            Candy two = _candies.get(0).get(i + 1);
            boolean match = checkColorfulMatches(one, two);
            if (match) return _N * _M;
        }

        //Sort _candies according to the highest candy count index in
        //descending order.
        Map<Integer, List<Candy>> _sortedCandies = _candies.entrySet().stream()
            .sorted(Collections.reverseOrder(comparingInt(e -> e.getValue().size())))
            .collect(toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a,b) -> {throw new AssertionError();},
                LinkedHashMap::new
            ));

        //Starting from index 0 (highest candy count):
        //  1. check against all colorful candies if the colorful candy is within its
        //      cardinals.
        //  2. if all colorful candies does not have the highest candy type in
        //      its cardinals, move on to next highest candy count index.
        Iterator<Integer> candySet = _sortedCandies.keySet().iterator();
        while (candySet.hasNext()) {
            int candyType = candySet.next();
            for (int i = 0; i < _candies.get(0).size(); i++) {
                boolean match = checkCardinals(_candies.get(0).get(i), candyType);
                if (match) return (_sortedCandies.get(candyType).size() + 1);
            }
        }

        return -1;
    }

    /**
    * Auxiliary method to check if the two candies are next to each other.
    * @param one - Candy to check against the other Candy.
    * @param two - Candy to check against the other Candy.
    * @return true if candies are within cardinals, false otherwise.
    */
    static boolean checkColorfulMatches(Candy one, Candy two) {
        int oneRow = one.getRow();
        int oneCol = one.getCol();
        int twoRow = two.getRow();
        int twoCol = two.getCol();
        if (((twoRow - 1 == oneRow) && (twoCol == oneCol)) ||
            ((twoRow + 1 == oneRow) && (twoCol == twoCol)) ||
            ((twoRow == oneRow) && (twoCol - 1 == oneCol)) ||
            ((twoRow == oneRow) && (twoCol + 1 == oneCol))) return true;
        else return false;
    }

    /**
    * Auxiliary method to check if the input Candy has the input Candy type in
    * its cardinals.
    * @param one - Candy to check its cardinals for.
    * @param candyType - type of candy to match.
    * @return true if the input Candy has target Candy type in its cardinals,
    *         false otherwise.
    */
    static boolean checkCardinals(Candy one, int candyType) {
        int oneRow = one.getRow();
        int oneCol = one.getCol();
        Candy left = oneCol - 1 >= 0 ? _grid[oneRow][oneCol - 1] : null;
        Candy right = oneCol + 1 < _M ? _grid[oneRow][oneCol + 1] : null;
        Candy top = oneRow - 1 >= 0 ? _grid[oneRow - 1][oneCol] : null;
        Candy bottom = oneRow + 1 < _N ? _grid[oneRow + 1][oneCol] : null;

        if ((left != null && left.getType() == candyType) ||
            (right != null && right.getType() == candyType) ||
            (top != null && top.getType() == candyType) ||
            (bottom != null && bottom.getType() == candyType)) return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(maximumCandies());
    }

}
