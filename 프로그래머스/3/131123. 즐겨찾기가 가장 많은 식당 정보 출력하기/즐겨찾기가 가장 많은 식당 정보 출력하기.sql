SELECT R.FOOD_TYPE, R.REST_ID, R.REST_NAME, R.FAVORITES
from REST_INFO R join (
    select food_type, REST_ID, REST_NAME, max(favorites) as max_fav
    from REST_INFO
    group by food_type
) F 
ON R.FOOD_TYPE = F.FOOD_TYPE and R.FAVORITES = F.max_fav
order by food_type desc;