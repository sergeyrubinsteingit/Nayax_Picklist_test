SELECT
TBL_USERS.validate_phone_code AS VALIDATE_PHONE_CODE,
TBL_USERS.user_phone AS USER_PHONE,
*
FROM TBL_USERS
WHERE TBL_USERS.validate_phone_code IS NOT NULL
AND TBL_USERS.user_phone IS NOT NULL
AND TBL_USERS.user_phone != ''
AND TBL_USERS.user_phone NOT LIKE '%x%'

SELECT
TBL_USERS.validate_phone_code AS VALIDATE_PHONE_CODE,
TBL_USERS.user_phone AS USER_PHONE,
*
FROM TBL_USERS
WHERE TBL_USERS.ad_name LIKE 'nayaxvend\sityuniver'
 