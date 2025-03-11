# **Maximum Followers of Bob**

## **Problem Statement**
N people are standing in a row, each initially having `money[i]` dollars. Bob has a fixed amount `K`.

- All people who have the same money as Bob become his **followers**.
- You want to **maximize the number of Bob’s followers** by performing **at most one operation**.

### **Allowed Operation (At Most Once)**:
1. Choose a **subarray** `[L, R]` (where `0 ≤ L ≤ R ≤ N - 1`).
2. Choose an **integer X** (`-10⁹ ≤ X ≤ 10⁹`).
3. **Add X** to every person in that subarray:  
   ```
   money[j] = money[j] + X  (for all L ≤ j ≤ R)
   ```
   - If `X` is negative, you are **subtracting money** from people.
   - Only the **final value** of `money[i]` matters.

### **Objective**  
Find the **maximum number of followers of Bob** after performing at most one operation.

---

## **Function Description**
Implement the function:

```java
int solution(int N, int K, int[] money)
```

**Parameters**:
- `N` → Integer: Number of people (`1 ≤ N ≤ 10⁵`).
- `K` → Integer: Bob's amount (`1 ≤ K ≤ 10⁵`).
- `money` → Integer array: Initial money each person has (`1 ≤ money[i] < 10⁵`).

**Returns**:
- An integer representing the **maximum number of followers** Bob can get.

---

## **Constraints**
- `1 ≤ N ≤ 100000`
- `1 ≤ K ≤ 100000`
- `1 ≤ money[i] < 100000`

---

## **Example**
### **Input**
```
3
2
6 2 6
```
### **Output**
```
2
```

### **Explanation**
- Initial `money = [6, 2, 6]`
- Bob has `K = 2`
- We can **choose (L = 0, R = 0) and X = -4**  
  - This results in `money = [2, 2, 6]`
  - Now, `2` people have `money = K`, so the answer is **2**.

---

## **Approach (Sliding Window + HashMap)**
1. **Count initial followers** where `money[i] == K`.
2. **Find the best subarray [L, R] with X** such that:
   - It maximizes the number of people having `money = K`.
3. **Use frequency mapping** to efficiently compute the best choice.

---

