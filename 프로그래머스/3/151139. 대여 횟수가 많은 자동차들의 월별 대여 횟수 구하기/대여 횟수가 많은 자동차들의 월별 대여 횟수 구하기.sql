with recursive months as(
    select 8 as month
    union all
    select month+1
    from months
    where month < 10
)

select 
    m.month, h.car_id, count(history_id) as RECORDS
from months m 
    join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on m.month = MONTH(h.START_DATE)
where h.car_id in (SELECT car_id
 from CAR_RENTAL_COMPANY_RENTAL_HISTORY
 where START_DATE BETWEEN '2022-08-01' and '2022-10-31'
 group by car_id
 having count(*) >= 5
) 
group by m.month, h.car_id
order by m.month, h.car_id desc;
