select YEAR(s.SALES_DATE) as year, month(s.SALES_DATE) as month,
     count(distinct i.user_id) as PURCHASED_USERS,
    ROUND(
        count(distinct i.user_id) / (
            select count(distinct(w.user_id))
            from USER_INFO w
            where year(w.joined) = 2021
        ), 1
    ) as PUCHASED_RATIO
from USER_INFO i join ONLINE_SALE s on i.user_id = s.user_id
where YEAR(i.joined) = 2021
group by year(s.sales_date), month(s.SALES_DATE)
order by year(s.sales_date), month(s.SALES_DATE);