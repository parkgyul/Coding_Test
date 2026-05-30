SELECT PRODUCT_CODE, (W.total * p.price) as SALES
from PRODUCT p join (
    select PRODUCT_ID, sum(SALES_AMOUNT) as total
    from OFFLINE_SALE
    group by PRODUCT_ID
) W on p.PRODUCT_ID = W.PRODUCT_ID
order by sales desc, PRODUCT_CODE;