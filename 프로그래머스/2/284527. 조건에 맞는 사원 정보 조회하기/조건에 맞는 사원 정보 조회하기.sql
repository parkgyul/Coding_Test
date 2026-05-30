Select sum(g.score) as score, e.emp_no, e.emp_name, e.position, e.email
from HR_EMPLOYEES e join HR_GRADE g on e.emp_no = g.emp_no
where g.year = 2022
group by e.emp_no
order by score desc
limit 1;