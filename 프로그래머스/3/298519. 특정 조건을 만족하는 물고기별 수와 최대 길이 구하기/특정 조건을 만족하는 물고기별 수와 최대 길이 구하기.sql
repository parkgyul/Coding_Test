select count(*) as fish_count, max(length) as max_length, fish_type
from FISH_INFO
group by fish_type
having AVG(IFNULL(length, 10)) >= 33
order by fish_type;