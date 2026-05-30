select t.total_score as score, e.EMP_NO, e.EMP_NAME, e.POSITION, e.EMAIL
from HR_EMPLOYEES e 
join(
    select h.emp_no, sum(g.score) total_score
    from HR_EMPLOYEES h join HR_GRADE g on h.EMP_NO = g.EMP_NO
    group by h.emp_no
    order by total_score desc
    limit 1
)t on e.emp_no = t.emp_no;
