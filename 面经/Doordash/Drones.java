package Doordash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class City {
    String name;
    int x, y;
    
    City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}

class Drone {
    int id, x, y;
    
    Drone(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

public class Drones {
    public static void main(String[] args) {
        List<City> cities = List.of(
            new City("A", 1, 2),
            new City("B", 3, 4),
            new City("C", 1, 5),
            new City("D", 5, 5)
        );
        
        List<Drone> drones = List.of(
            new Drone(1, 1, 1),
            new Drone(2, 2, 2),
            new Drone(3, 3, 3)
        );
        
        Map<Integer, String> nearestCities = findNearestCities(drones, cities);
        
        for (Map.Entry<Integer, String> entry : nearestCities.entrySet()) {
            System.out.println("Drone " + entry.getKey() + " can deliver to city " + entry.getValue());
        }
    }
    
    public static Map<Integer, String> findNearestCities(List<Drone> drones, List<City> cities) {
        Map<Integer, String> nearestCities = new HashMap<>();
        
        for (Drone drone : drones) {
            List<City> reachableCities = new ArrayList<>();
            
            for (City city : cities) {
                if (drone.x == city.x || drone.y == city.y) {
                    reachableCities.add(city);
                }
            }
            
            if (!reachableCities.isEmpty()) {
                City nearestCity = Collections.min(reachableCities, (a, b) -> {
                    int distA = Math.abs(a.x - drone.x) + Math.abs(a.y - drone.y);
                    int distB = Math.abs(b.x - drone.x) + Math.abs(b.y - drone.y);
                    
                    if (distA == distB) {
                        return a.name.compareTo(b.name);
                    }
                    
                    return Integer.compare(distA, distB);
                });
                
                nearestCities.put(drone.id, nearestCity.name);
            }
        }
        
        return nearestCities;
    }
}
