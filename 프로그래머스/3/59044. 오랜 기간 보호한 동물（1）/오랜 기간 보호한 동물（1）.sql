-- 코드를 입력하세요
SELECT NAME, DATETIME
from animal_ins a
where a.ANIMAL_ID NOT IN(
    select i.animal_id
    from ANIMAL_INS i join ANIMAL_OUTS o on i.ANIMAL_ID = o.ANIMAL_ID
)
order by DATETIME 
limit 3;