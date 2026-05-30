SELECT p.MEMBER_NAME, r.REVIEW_TEXT, r.REVIEW_DATE
from REST_REVIEW r join MEMBER_PROFILE p on r.MEMBER_ID = p.MEMBER_ID
where r.MEMBER_ID = (
        select member_id
        from REST_REVIEW
        group by member_id
        order by count(*) desc
        limit 1
    )
order by REVIEW_DATE, REVIEW_TEXT;