select c.CAR_ID, c.CAR_TYPE, ROUND((c.DAILY_FEE * 30 *(100-p.DISCOUNT_RATE) / 100)) as fee
from CAR_RENTAL_COMPANY_CAR c join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p 
    on c.car_type = p.car_type
where c.CAR_TYPE in ('세단', 'SUV')
       AND p.DURATION_TYPE = '30일 이상'
       AND NOT EXISTS (
          SELECT 1
          FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
          WHERE c.CAR_ID = h.CAR_ID
            AND h.START_DATE <= '2022-11-30'
            AND h.END_DATE >= '2022-11-01'
      )
      and ROUND((c.DAILY_FEE * 30 *(100-p.DISCOUNT_RATE) / 100)) >= 500000
      and ROUND((c.DAILY_FEE * 30 *(100-p.DISCOUNT_RATE) / 100)) < 2000000
order by fee desc,  c.CAR_TYPE,  c.CAR_ID ASC;