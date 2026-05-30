with recursive hours as(
    select 0 as hour
    union all
    select hour+1
    from hours
    where hour < 23
)

select h.hour, count(distinct a.ANIMAL_ID)
from hours h left join ANIMAL_OUTS a on h.hour = hour(a.DATETIME)
group by h.hour
order by h.hour;