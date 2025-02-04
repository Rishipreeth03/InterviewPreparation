""" Aproach 1"""
select max(salary) as SecondHighestSalary 
from employee
where salary not in (select max(salary) from employee);

""" Aproach 2"""
SELECT salary 
FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk 
    FROM employees
) ranked_salaries 
WHERE rnk = 2;

"""Approach 3"""
SELECT DISTINCT salary 
FROM employees 
ORDER BY salary DESC 
LIMIT 1 OFFSET 1;
