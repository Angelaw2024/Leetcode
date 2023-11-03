package 买它;
import java.util.*;

class CustomMap {
    public static void main(String[] args) {
        CustomMap1 customMap = new CustomMap1();
        // Test set and get
        customMap.set(1, 100);
        customMap.set(2, 200);
        
        Integer val1 = customMap.get(1);
        Integer val2 = customMap.get(2);
        
        if (val1 != null && val1 == 100) {
            System.out.println("Test 1 passed");
        } else {
            System.out.println("Test 1 failed");
        }
        
        if (val2 != null && val2 == 200) {
            System.out.println("Test 2 passed");
        } else {
            System.out.println("Test 2 failed");
        }
    
        // Test setAll
        customMap.setAll(500);
        
        Integer val3 = customMap.get(1);
        Integer val4 = customMap.get(2);
        
        if (val3 != null && val3 == 500) {
            System.out.println("Test 3 passed");
        } else {
            System.out.println("Test 3 failed");
        }
    
        if (val4 != null && val4 == 500) {
            System.out.println("Test 4 passed");
        } else {
            System.out.println("Test 4 failed");
        }
        
        // Test get after setAll but before another set
        Integer val5 = customMap.get(3);
        if (val5 == null ) {
            System.out.println("Test 5 passed");
        } else {
            System.out.println("Test 5 failed");
        }
        
        // Test get after setAll and another set
        customMap.set(3, 300);
        Integer val6 = customMap.get(3);
        if (val6 != null && val6 == 300) {
            System.out.println("Test 6 passed");
        } else {
            System.out.println("Test 6 failed");
        }
    }
    
}

class CustomMap1 {
    int version;
    Map<Integer, Integer> versionMap;
    Map<Integer, Node> map;

    public CustomMap1() {
        version = 0;
        versionMap = new HashMap<>();
        map = new HashMap<>();
    }

	public void set(int key, int value) {
        map.put(key, new Node(version, value));
	}

	public Integer get(int key) {
        if (!map.containsKey(key)) return null;
        Node node = map.get(key);
        if (node.version < version) {
            return versionMap.get(version);
        }
        return node.val;
	}

	public void setAll(int value) {
        version++;
        versionMap.put(version, value);
	}
}

class Node {
    int version;
    int val;
    Node(int version, int val) {
        this.version = version;
        this.val = val;
    }
}