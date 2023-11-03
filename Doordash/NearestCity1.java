package Doordash;
import java.util.*;

public class NearestCity1 {
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
    
        Map<Integer, Set<Integer>> mapX = new HashMap<>();
        Map<Integer, Set<Integer>> mapY = new HashMap<>();
        Map<String, Integer> nameIdx = new HashMap<>();

        for (int i = 0; i < n; i++) {
            mapX.computeIfAbsent(x[i], k -> new HashSet<>()).add(i);
            mapY.computeIfAbsent(y[i], k -> new HashSet<>()).add(i);
            nameIdx.put(c[i], i);
        }

        for (int i = 0; i < m; i++) {
            int cityIdx = nameIdx.get(q[i]);
            Set<Integer> set = mapX.get(x[cityIdx]);
            Set<Integer> ySet = mapY.get(y[cityIdx]);
            set.addAll(ySet);
            int min = Integer.MAX_VALUE;
            String city = "NONE";
            for (Integer idx : set) {
                if (idx == cityIdx) continue;
                int distance = Math.abs(x[idx] - x[cityIdx]) + Math.abs(y[idx] - y[cityIdx]);
                if (distance < min || (distance == min && c[idx].compareTo(city) < 0)) {
                    min = distance;
                    city = c[idx];
                }
            }
            results[i] = city;
        }
        
        return results;
    }
}
