package Doordash;
import java.util.*;

public class NearestCity {
    public static void main(String[] args) {
        int n = 3;
        String[] c = {"c1", "c2", "c3"};
        int[] x = {3, 2, 1};
        int[] y = {3, 2, 3};
        String[] result = closestStraightCity(c, x, y, c);
        System.out.println(Arrays.toString(result));
    }
    
    public static String[] closestStraightCity(String[] c, int[] x, int[] y, String[] q) {
        int n = c.length;
        int m = q.length;
        String[] results = new String[m];
    
        Map<String, String> computedPairs = new HashMap<>();

        for (int i = 0; i < m; i++) {
            String cityQuery = q[i];
            int idxQuery = -1;
    
            for (int j = 0; j < n; j++) {
                if (c[j].equals(cityQuery)) {
                    idxQuery = j;
                    break;
                }
            }
    
            if (computedPairs.containsKey(cityQuery)) {
                results[i] = computedPairs.get(cityQuery);
                continue;
            }

            int minDistance = Integer.MAX_VALUE;
            String closestCity = "NONE";
            
            for (int j = 0; j < n; j++) {
                if (j != idxQuery && (x[j] == x[idxQuery] || y[j] == y[idxQuery])) {
                    int distance = Math.abs(x[j] - x[idxQuery]) + Math.abs(y[j] - y[idxQuery]);
                    
                    if (distance < minDistance || (distance == minDistance && c[j].compareTo(closestCity) < 0)) {
                        minDistance = distance;
                        closestCity = c[j];
                    }
                }
            }
            if (!closestCity.equals("NONE")) {
                computedPairs.put(cityQuery, closestCity);

                computedPairs.put(closestCity, cityQuery);
            }
            results[i] = closestCity;
        }
        
        return results;
    }
}
