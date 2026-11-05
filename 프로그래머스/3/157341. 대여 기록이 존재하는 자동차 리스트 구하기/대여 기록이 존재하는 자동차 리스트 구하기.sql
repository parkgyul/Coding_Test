-- 코드를 입력하세요
SELECT distinct(c.car_id)
from CAR_RENTAL_COMPANY_CAR c join CAR_RENTAL_COMPANY_RENTAL_HISTORY h
on c.CAR_ID = h.CAR_ID
where c.CAR_TYPE = '세단' and MONTH(h.START_DATE) = 10
order by car_id desc;