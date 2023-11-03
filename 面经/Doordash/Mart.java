package Doordash;

import java.util.*;

public class Mart {

    public static void main(String[] args) {
        char[][] city = {
            {'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'},
            {'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'},
            {' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '},
            {' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'},
            {' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'}
        };
        
        int[][] locations = {{2, 2}, {4, 0}, {0, 4}, {2, 6}};
        int[] result = getClosestDashmart(city, locations);
        System.out.println(Arrays.toString(result));

        // int[][] result = getClosestDashmart(city, locations);

        // for (int[] r:result ) {
        //         System.out.println(Arrays.toString(r));

        // }
    }

    static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int[] getClosestDashmart(char[][] city, int[][] locations) {
        int n = city.length;
        int m = city[0].length;
        Node[][] map = new Node[n][m];
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (city[i][j] == 'D') {
                    Node node = new Node(0, i, j);
                    queue.offer(node);
                    map[i][j] = node;
                } else {
                    map[i][j] = new Node(Integer.MAX_VALUE, i, j);
                }
            }
        }

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.dx + dir[i][0];
                int ny = cur.dy + dir[i][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && city[nx][ny] == ' ' && map[nx][ny].dist == Integer.MAX_VALUE) {
                    map[nx][ny].dist = cur.dist + 1;
                    // map[nx][ny].closetMartx = cur.closetMartx;
                    // map[nx][ny].closetMarty = cur.closetMarty;
                    queue.offer(map[nx][ny]);
                }
            }
        }
        int[] res = new int[locations.length];
        for (int i = 0; i < locations.length; i++) {
            Node node = map[locations[i][0]][locations[i][1]];
            res[i] = node == null ? -1 : node.dist;
        }
        return res;
        // int[][] res = new int[locations.length][2];
        // for (int i = 0; i < locations.length; i++) {
        //     Node node = map[locations[i][0]][locations[i][1]];
        //     res[i] = new int[]{node.closetMartx, node.closetMarty};
        // }
        // return res;
    }

    static class Node {
        int dist;
        int dx;
        int dy;
        // int closetMartx;
        // int closetMarty;
        public Node(int dist, int dx, int dy) {
            this.dist = dist;
            this.dx = dx;
            this.dy = dy;
            // this.closetMartx = closetMartx;
            // this.closetMarty = closetMarty;
        }
    }
}

