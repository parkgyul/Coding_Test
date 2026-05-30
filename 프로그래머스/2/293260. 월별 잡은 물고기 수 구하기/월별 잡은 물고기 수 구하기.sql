select count(*) as fish_count, month(time) as month
from fish_info
group by month
having fish_count >= 1
order by month;