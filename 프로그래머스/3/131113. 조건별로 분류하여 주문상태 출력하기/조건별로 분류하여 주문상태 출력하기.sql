SELECT ORDER_ID, PRODUCT_ID, OUT_DATE,
    case when OUT_DATE <= '2022-05-01' then '출고완료'
    when out_date is null then '출고미정'
    else '출고대기'
    end as 출고여부
from FOOD_ORDER
order by ORDER_ID;