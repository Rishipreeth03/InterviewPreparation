
/*
    Leetcode Link               : https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies
*/
    // Approach-1 - Brute Force
    // T.C : (n^2 * m)
    // S.C : O(n+S)
    import java.util.*;
    
private class Solution {
        public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            int n = recipes.length;
            List<String> result = new ArrayList<>();
            Set<String> st = new HashSet<>(Arrays.asList(supplies));
            boolean[] cooked = new boolean[n];
            int count = n;
            
            while (count-- > 0) {
                for (int j = 0; j < n; j++) {
                    if (cooked[j]) {
                        continue;
                    }
                    
                    boolean canMake = true;
                    for (String ing : ingredients.get(j)) {
                        if (!st.contains(ing)) {
                            canMake = false;
                            break;
                        }
                    }
                    
                    if (canMake) {
                        st.add(recipes[j]);
                        result.add(recipes[j]);
                        cooked[j] = true;
                    }
                }
            }
            
            return result;
        }
    }
    
    // Approach-2 - Using Topological Sorting
    // T.C : (n + m + S)
    // S.C : O(n+S)
    import java.util.*;
    
private  class Solution12 {
        public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            int n = recipes.length;
            Set<String> st = new HashSet<>(Arrays.asList(supplies));
            
            // adj
            Map<String, List<Integer>> adj = new HashMap<>(); // ing ---> recipe
            int[] indegree = new int[n];
            
            for (int i = 0; i < n; i++) {
                for (String ing : ingredients.get(i)) {
                    if (!st.contains(ing)) {
                        adj.putIfAbsent(ing, new ArrayList<>());
                        adj.get(ing).add(i);
                        indegree[i]++;
                    }
                }
            }
            
            Queue<Integer> que = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    que.add(i);
                }
            }
            
            List<String> result = new ArrayList<>();
            while (!que.isEmpty()) {
                int i = que.poll();
                String recipe = recipes[i];
                result.add(recipe);
                
                if (adj.containsKey(recipe)) {
                    for (int idx : adj.get(recipe)) {
                        indegree[idx]--;
                        if (indegree[idx] == 0) {
                            que.add(idx);
                        }
                    }
                }
            }
            
            return result;
        }
    }