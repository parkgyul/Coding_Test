SELECT HISTORY_ID, round(c.daily_fee * h.duration * (100 - IFNULL(P.DISCOUNT_RATE, 0)) / 100)  as FEE
from (
    select history_id, car_id, DATEDIFF(END_DATE, START_DATE)+1 as DURATION,
    case 
        when DATEDIFF(END_DATE, START_DATE)+1 >= 90 then '90일 이상'
        when DATEDIFF(END_DATE, START_DATE)+1 >= 30 then '30일 이상'
        when DATEDIFF(END_DATE, START_DATE)+1 >= 7 then '7일 이상'
    end as DURATION_TYPE
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
) h
join CAR_RENTAL_COMPANY_CAR c on h.CAR_ID = c.CAR_ID
left join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
    on p.car_type = c.car_type
    and h.DURATION_TYPE = p.DURATION_TYPE
where c.car_type = '트럭'
order by fee desc, history_id desc;