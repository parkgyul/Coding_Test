SELECT hour(DATETIME) as hour, count(*) as count
from ANIMAL_OUTS
where hour(DATETIME) >= 9 and hour(DATETIME) <= 19
group by hour
order by hour;