SELECT b.AUTHOR_ID, a.AUTHOR_NAME, CATEGORY, sum(b.PRICE * S.total) as TOTAL_SALES

from book b join AUTHOR a on b.AUTHOR_ID = a.AUTHOR_ID
join (
    select book_id, sum(sales) as total
    from BOOK_SALES
    where SALES_DATE between '2022-01-01' and '2022-01-31'
    group by book_id
) S on b.book_id = s.book_id
group by author_id, category
order by b.AUTHOR_ID, CATEGORY desc ;

