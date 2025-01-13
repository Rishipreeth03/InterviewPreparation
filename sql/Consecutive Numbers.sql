-- Approach 1 simple
select l1.num as ConsecutiveNums
from logs l1,logs l2,logs l3
where l2.id-l1.id=1
and l3.id-l2.id=1
and l1.num=l2.num
and l2.num=l3.num
group by l1.num;
/* Without GROUP BY, multiple occurrences of a number appearing consecutively three times would result
    in duplicate rows for each set of consecutive rows.Since we aliased 3 tables.
 */



-- Approach 2 using lag

-- Common Table Expression (CTE) to create a temporary result set called NumberSequences
WITH NumberSequences as (
    -- Select the current 'num' value and its previous two 'num' values using the LAG() function
    SELECT
        num,  -- The current 'num' value
        -- LAG(num, 1, null) gets the value of 'num' from the previous row (if available), otherwise returns null
        LAG(num, 1, null) OVER () AS prev_value_1, 
        -- LAG(num, 2, null) gets the value of 'num' from two rows before (if available), otherwise returns null
        LAG(num, 2, null) OVER () AS prev_value_2
    FROM Logs  -- From the 'Logs' table
)
-- Main query to select distinct numbers that appear consecutively three times
SELECT DISTINCT num as ConsecutiveNums  -- Select unique 'num' values as 'ConsecutiveNums'
FROM NumberSequences  -- From the CTE 'NumberSequences'
WHERE num = prev_value_1  -- Check if the current 'num' is the same as the previous row's 'num'
AND num = prev_value_2  -- Check if the current 'num' is the same as the 'num' from two rows before



-- Approach 3 using lead

-- Common Table Expression (CTE) to create a temporary result set with current and future rows
WITH cte AS (
    -- Select the current 'num' value and the next two 'num' values using the LEAD() function
    SELECT
        num,  -- The current 'num' value from the 'Logs' table
        -- LEAD(num, 1) gets the value of 'num' from the next row (if available), otherwise returns NULL
        LEAD(num, 1) OVER () AS num1,  
        -- LEAD(num, 2) gets the value of 'num' from two rows after the current row, or NULL if not available
        LEAD(num, 2) OVER () AS num2  
    FROM logs  -- From the 'logs' table
)
-- Main query to select distinct numbers that appear consecutively at least three times
SELECT DISTINCT num AS ConsecutiveNums  -- Select unique 'num' values as 'ConsecutiveNums'
FROM cte  -- From the CTE result set
WHERE (num = num1)  -- Check if the current 'num' is the same as the next row's 'num'
AND (num = num2)  -- Check if the current 'num' is the same as the 'num' from two rows after