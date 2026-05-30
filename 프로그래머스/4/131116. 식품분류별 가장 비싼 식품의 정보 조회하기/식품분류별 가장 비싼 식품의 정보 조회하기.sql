SELECT f.CATEGORY, f.PRICE as MAX_PRICE, f.PRODUCT_NAME
from (
    select p.CATEGORY, MAX(p.price) as max
    from FOOD_PRODUCT p
    where p.CATEGORY in ('과자', '국', '김치', '식용유')
    group by p.CATEGORY
) W join FOOD_PRODUCT f on W.max = f.price and W.category = f.category
order by max_price desc;