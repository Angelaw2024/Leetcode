package Doordash;

import java.util.*;

public class Order {
    public static void main(String[] args) {
        String start = "18:00";
        String end = "9:00";
        String[][] orders = {{"get", "1", "09:10"}, {"deliveried", "1", "09:30"}, {"get", "2", "19:00"}, {"deliveried", "2", "19:15"}};
        System.out.println("Total income: " + calculateIncome(start, end, orders));
    }

    public static int parseTime(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    public static double calculateIncome(String start, String end, String[][] orders) {
        int startTime = parseTime(start);
        int endTime = parseTime(end);
        boolean isOvernight = endTime < startTime;
        if (isOvernight) {
            endTime += 24 * 60; // Add 24 hours to end time
        }
        System.out.println("startTime + endTime: " + startTime + " " + endTime);
        double rate = 0.3;


        Map<String, Integer> orderStartMap = new HashMap<>();
        double totalIncome = 0.0;

        for (String[] order : orders) {
            String status = order[0];
            String orderId = order[1];
            int orderTime = parseTime(order[2]);

            // Handle orders crossing midnight
            if (orderTime < startTime && isOvernight) {
                orderTime += 24 * 60;
            }

            if (orderTime < startTime || orderTime > endTime) continue;

            if (status.equals("get")) {
                orderStartMap.put(orderId, orderTime);
            } else if (status.equals("deliveried")) {
                if (orderStartMap.containsKey(orderId)) {
                    int startOrderTime = orderStartMap.get(orderId);
                    int duration = orderTime - startOrderTime;
                    totalIncome += duration * rate;
                }
            }
        }

        return totalIncome;
    }
}
