
/*
    YT refernce : https://www.youtube.com/watch?v=LVV5_MOzdq4
    Leetcode Link               : https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting
*/

//Approach - Using BFS and Cycle Checks
//T.C : ~O(n)
//S.C : ~O(n)
class Solution {
    public int BFS(int start, Map<Integer, List<Integer>> adj, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>(); // {node, path length}
        queue.add(new int[]{start, 0});
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currNode = current[0];
            int dist = current[1];

            for (int neighbor : adj.getOrDefault(currNode, new ArrayList<>())) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new int[]{neighbor, dist + 1});
                    maxDistance = Math.max(maxDistance, dist + 1);
                }
            }
        }

        return maxDistance;
    }

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();

        // Build reversed graph
        for (int i = 0; i < n; i++) {
            int u = i;
            int v = favorite[i];
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int longestCycleEmplCount = 0;
        int happyCoupleEmplCount = 0;

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Map<Integer, Integer> mp = new HashMap<>();
                int currNode = i;
                int currNodeCount = 0;

                while (!visited[currNode]) { // Until cycle is not detected
                    visited[currNode] = true;
                    mp.put(currNode, currNodeCount);

                    int nextNode = favorite[currNode]; // Favorite node of currNode
                    currNodeCount++;

                    if (mp.containsKey(nextNode)) { // Cycle detected
                        int cycleLength = currNodeCount - mp.get(nextNode);
                        longestCycleEmplCount = Math.max(longestCycleEmplCount, cycleLength);

                        if (cycleLength == 2) { // Happy couple case
                            boolean[] visitedNodes = new boolean[n];
                            visitedNodes[currNode] = true;
                            visitedNodes[nextNode] = true;
                            happyCoupleEmplCount += 2 + BFS(currNode, adj, visitedNodes) + BFS(nextNode, adj, visitedNodes);
                        }
                        break;
                    }
                    currNode = nextNode;
                }
            }
        }

        return Math.max(happyCoupleEmplCount, longestCycleEmplCount);
    }
}

/*
 * Maximum Employees to Be Invited to a Meeting | Super Detailed | Leetcode 2127 | codestorywithMIK

This video explains the Leetcode problem 2127, "Maximum Employees to Be Invited to a Meeting". The instructor provides a detailed breakdown of the problem, including its requirements, potential edge cases, and various strategies for finding an optimal solution using graph theory. The video includes live coding and debugging, and addresses how to handle cycles in the graph representing employee preferences for seating. Several examples illustrate how to derive the maximum number of employees that can attend a meeting based on their favorite person’s seating arrangements. The session aims to improve problem-solving skills and interview readiness by discussing complex algorithms in a clear manner.

Key Points:

Understanding the Problem
The video begins by clarifying the problem statement from Leetcode 2127, which requires determining the maximum number of employees that can attend a meeting given their preferences for whom they sit next to. The instructor emphasizes the importance of grasping the core requirements and hidden complexities within the problem.

Graph Representation
The problem is modeled using graph theory, where employees are nodes and preferences denote edges between them. The relationships between nodes are explored, allowing for the construction of a circular table configuration where only certain arrangements will satisfy everyone's seating conditions.

Cycle Detection and Length Calculation
The video discusses how to detect cycles within the graph, which indicates that specific employees prefer to sit next to each other. Various examples are used to illustrate this, and the instructor explains how to calculate the length of cycles, which helps in determining the maximum employees that can be seated together.

Algorithm Implementation
As the instructor codes the solution, they explain how to implement a BFS (Breadth-First Search) algorithm to traverse the graph. The flow of the algorithm, including how to maintain and track visited nodes, is crucial to effectively determine the maximum seating arrangement.

Edge Cases and Testing
The video concludes by discussing potential edge cases that could arise in the problem, as well as strategies for testing the implemented solution to ensure its accuracy and efficiency. This prepares viewers for similar problems they may encounter in coding interviews.


 */