## Explanation of Key Logic in the Code

### What is `prev`?

`prev` is a pointer used to build the `next` pointers for the nodes in the **next level**. 
- Initially, `prev` points to the dummy node at the start of each level.
- As we discover nodes in the next level, we use `prev` to connect them.

---

### Purpose of These Lines:

```java
if (temp.left != null) {
    prev.next = temp.left; // Connect the left child to the 'prev' pointer
    prev = prev.next;      // Move the 'prev' pointer forward
}
if (temp.right != null) {
    prev.next = temp.right; // Connect the right child to the 'prev' pointer
    prev = prev.next;       // Move the 'prev' pointer forward
}


## Visual Example and Walkthrough

### Tree:

    1
   / \
  2   3
 / \
4   5



---

### Walkthrough:

#### **Start at Level 1 (`temp = 1`):**

1. `prev` initially points to the dummy node.
2. The left child of `1` (`2`) is connected to `prev.next`, and `prev` moves to `2`.
3. The right child of `1` (`3`) is connected to `prev.next`, and `prev` moves to `3`.
4. After processing, `dummy.next` points to the start of level 2: `2 -> 3`.

---

#### **Move to Level 2 (`temp = 2`):**

1. `prev` is reset to the dummy node.
2. The left child of `2` (`4`) is connected to `prev.next`, and `prev` moves to `4`.
3. The right child of `2` (`5`) is connected to `prev.next`, and `prev` moves to `5`.
4. `temp` moves to the next node (`3`).
5. Node `3` has no children, so no further connections are made.
6. After processing, `dummy.next` points to the start of level 3: `4 -> 5`.

---

#### **Move to Level 3 (`temp = 4`):**

1. `prev` is reset to the dummy node.
2. Both `4` and `5` have no children, so no further connections are made.
3. The traversal ends as there are no more levels.


---

Why Do We Need These Lines?
prev.next = temp.left; and prev.next = temp.right;:
These lines ensure that nodes in the next level are correctly connected in left-to-right order.
prev = prev.next;:
This moves the prev pointer forward, so it is always ready to connect the next child node