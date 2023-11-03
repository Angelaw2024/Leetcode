package Doordash;

import java.util.*;

public class Order1 {
    public static void main(String[] args) {
        String start = "18:00";
        String end = "03:00";
        String[][] orders = {
            {"接单", "1", "19:10"}, {"送达", "1", "19:30"}, 
            {"接单", "2", "23:00"}, {"送达", "2", "1:15"}, 
            {"接单", "3", "00:00"}, {"送达", "3", "02:30"},
            {"等待", "NA", "00:00"}, {"结束等待", "NA", "02:29"},

        };
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
        int waitingStartTime = -1;
        int waitingEndTime = -1;
        double totalIncome = 0.0;
        Arrays.sort(orders, (a, b) -> {
            int time1 = parseTime(a[2]);
            int time2 = parseTime(b[2]);
            if (isOvernight) {
                if (time1 < startTime) time1 += 24 * 60;
                if (time2 < startTime) time2 += 24 * 60;
            }  
            return Integer.compare(time1, time2);
        });
        for (String[] order : orders) {

            String status = order[0];
            String orderId = order[1];
            int orderTime = parseTime(order[2]);
            System.out.println("orderId: " + orderId) ;

            // Handle orders crossing midnight
            if (orderTime < startTime && isOvernight) {
                orderTime += 24 * 60;
            }

            if (orderTime < startTime || orderTime > endTime) continue;

            if (status.equals("接单")) {
                orderStartMap.put(orderId, orderTime);
            } else if (status.equals("等待")) {
                waitingStartTime = orderTime;
            } else if (status.equals("结束等待")){
                waitingEndTime = orderTime;
                System.out.println("waitingStartTime + waitingEndTime: " + waitingStartTime + " " + waitingEndTime);

                if (waitingStartTime != -1 && waitingEndTime != -1) {
                    totalIncome += (waitingEndTime - waitingStartTime) * rate;
                }
            }
            else if (status.equals("送达")) {
                if (orderStartMap.containsKey(orderId)) {
                    int startOrderTime = orderStartMap.get(orderId);
                    int duration = orderTime - startOrderTime;
                    if (waitingStartTime == -1 || orderTime < waitingStartTime) {
                        totalIncome += duration * rate;
                        System.out.println("totalIncome: " + totalIncome);
                    } else {
                        int overlapStart = Math.max(startOrderTime, waitingStartTime);
                        int overlapEnd = waitingEndTime == -1 ? orderTime : Math.min(waitingEndTime, orderTime);
                        System.out.println("overlapStart + overlapEnd: " + overlapStart + " " + overlapEnd);

                        duration -= overlapEnd - overlapStart;
                        System.out.println("duration2: " + duration);
                        System.out.println("duration2 totalIncome: " + totalIncome);
                        totalIncome += duration * rate;
                        System.out.println("duration2 totalIncome: " + totalIncome);

                    }
                }
            }
        }

        return totalIncome;
    }
}
