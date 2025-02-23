import java.util.*;

// Approach-1 (Brute Force)
class LRUCacheBruteForce {
    private List<int[]> cache;
    private int capacity;

    public LRUCacheBruteForce(int capacity) {
        this.capacity = capacity;
        this.cache = new ArrayList<>();
    }

    public int get(int key) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i)[0] == key) {
                int val = cache.get(i)[1];
                int[] temp = cache.get(i);
                cache.remove(i);
                cache.add(temp);
                return val;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i)[0] == key) {
                cache.remove(i);
                cache.add(new int[]{key, value});
                return;
            }
        }

        if (cache.size() == capacity) {
            cache.remove(0);
        }
        cache.add(new int[]{key, value});
    }
}

//Approach 2

class LRUCacheLinkedList {
    private int capacity;
    private LinkedList<Integer> dll;
    private Map<Integer, Integer> cache;

    public LRUCacheLinkedList(int capacity) {
        this.capacity = capacity;
        this.dll = new LinkedList<>();
        this.cache = new HashMap<>();
    }

    private void makeMostRecentlyUsed(int key) {
        dll.remove((Integer) key);
        dll.addFirst(key);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeMostRecentlyUsed(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            dll.remove((Integer) key);
        } else if (cache.size() == capacity) {
            int lruKey = dll.removeLast();
            cache.remove(lruKey);
        }
        dll.addFirst(key);
        cache.put(key, value);
    }
}

/**
 * Time Complexity:
 * - get(): O(1) (HashMap lookup)
 * - put(): O(1) (HashMap insertion/removal + LinkedList operation)
 *
 * Space Complexity: O(C) where C is the cache capacity
 */




// MRU Cache
// Most Recently Used (MRU) Cache Implementation

class MRUCache {
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public MRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key); // Remove old occurrence
        }
        cache.put(key, value);
    }
}

/**
 * Time Complexity:
 * - get(): O(1) (Lookup in HashMap)
 * - put(): O(1) (Insertion/Removal in LinkedHashMap)
 *
 * Space Complexity: O(C) where C is the cache capacity
 */
