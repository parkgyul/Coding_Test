select e.emp_no, e.emp_name,
    case when sum >= 96 then 'S'
         when sum >= 90 then 'A'
         when sum >= 80 then 'B'
         else 'C'
    end as grade,
    case when sum >= 96 then SAL * 0.2
         when sum >= 90 then SAL * 0.15
         when sum >= 80 then SAL * 0.1
         else 0
    end as BONUS
from HR_EMPLOYEES e join (
    select EMP_NO, AVG(score) as sum
    from HR_GRADE
    group by EMP_NO
) G on e.emp_no = G.emp_no
order by emp_no ;