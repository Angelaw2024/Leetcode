package Doordash;

import java.util.*;

public class Order2{
    public static void main(String[] args) {
        String start = "18:00";
        String end = "03:00";
        String[][] orders = {
            {"接单", "1", "19:10"}, {"送达", "1", "19:30"}, 
            {"接单", "2", "23:00"}, {"送达", "2", "1:15"}, 
            {"接单", "3", "00:00"}, {"送达", "3", "02:30"},
            {"等待", "NA", "00:00"}, {"结束等待", "NA", "02:29"},

        };
        String[][] peakTimes = {
            {"20:00", "21:00"},
            {"00:00", "01:00"}
        };

        System.out.println("Total income: " + calculateIncome(start, end, orders, peakTimes));

    }

    public static int parseTime(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    public static boolean isWithinPeak(int time, int[][] peakTimes) {
        for (int[] peak : peakTimes) {
            if (time >= peak[0] && time <= peak[1]) {
                return true;
            }
        }
        return false;
    }
    
    public static double calculateIncome(String start, String end, String[][] orders, String[][] peakTimesStr) {
        int startTime = parseTime(start);
        int endTime = parseTime(end);
        boolean isOvernight = endTime < startTime;
        if (isOvernight) {
            endTime += 24 * 60; // Add 24 hours to end time
        }
        double rate = 0.3;

        int[][] peakTimes = new int[peakTimesStr.length][2];
        for (int i = 0; i < peakTimesStr.length; i++) {
            peakTimes[i][0] = parseTime(peakTimesStr[i][0]);
            peakTimes[i][1] = parseTime(peakTimesStr[i][1]);
        }

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

            // Handle orders crossing midnight
            if (orderTime < startTime && isOvernight) {
                orderTime += 24 * 60;
            }

            if (orderTime < startTime || orderTime > endTime) continue;
            double currentRate = isWithinPeak(orderTime, peakTimes) ? rate * 2 : rate;

            if (status.equals("接单")) {
                orderStartMap.put(orderId, orderTime);
            } else if (status.equals("等待")) {
                waitingStartTime = orderTime;
            } else if (status.equals("结束等待")){
                waitingEndTime = orderTime;
                if (waitingStartTime != -1 && waitingEndTime != -1) {
                    totalIncome += (waitingEndTime - waitingStartTime) * rate;
                }
            }
            else if (status.equals("送达")) {
                if (orderStartMap.containsKey(orderId)) {
                    int startOrderTime = orderStartMap.get(orderId);
                    int duration = orderTime - startOrderTime;
                    if (waitingStartTime != -1) {
                        int overlapStart = Math.max(startOrderTime, waitingStartTime);
                        int overlapEnd = waitingEndTime == -1 ? orderTime : Math.min(waitingEndTime, orderTime);
                        duration -= overlapEnd - overlapStart;
                    }
                    totalIncome += duration * rate;
                }
            }
        }

        return totalIncome;
    }
}
