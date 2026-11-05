SELECT a.APNT_NO, p.PT_NAME, p.PT_NO, a.MCDP_CD, d.DR_NAME, a.APNT_YMD
from APPOINTMENT a join DOCTOR d on d.DR_ID = a.MDDR_ID
    join PATIENT p on a.PT_NO = p.PT_NO
where 
    DATE(APNT_YMD) = '2022-04-13' and APNT_CNCL_YN = 'N' and a.MCDP_CD = 'CS'
order by a.APNT_YMD;