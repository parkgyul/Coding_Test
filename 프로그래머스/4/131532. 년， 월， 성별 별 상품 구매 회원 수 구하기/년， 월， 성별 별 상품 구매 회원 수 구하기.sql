-- 코드를 입력하세요
SELECT year(SALES_DATE) as year, month(SALES_DATE) as month, gender, count(distinct i.user_id) as users
from USER_INFO i join ONLINE_SALE s on i.user_id = s.user_id
where gender is NOT NULL
group by year(SALES_DATE), month(SALES_DATE), gender
order by year(SALES_DATE), month(SALES_DATE), gender;