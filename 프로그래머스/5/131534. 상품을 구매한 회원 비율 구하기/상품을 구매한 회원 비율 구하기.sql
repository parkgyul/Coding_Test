-- 코드를 입력하세요
SELECT YEAR(o.SALES_DATE) as YEAR, 
        MONTH(o.SALES_DATE) as MONTH, 
        count(DISTINCT o.user_id) as PURCHASED_USERS, 
        ROUND(count( DISTINCT o.user_id) /
         (
            select count(*)
             from USER_INFO w
             where year(w.joined) = 2021
         ), 1)
         as PUCHASED_RATIO
from USER_INFO i join ONLINE_SALE o on i.USER_ID = o.USER_ID                
where YEAR(i.joined) = 2021                       
group by YEAR(o.SALES_DATE), MONTH(o.SALES_DATE)
order by YEAR(o.SALES_DATE), MONTH(o.SALES_DATE);