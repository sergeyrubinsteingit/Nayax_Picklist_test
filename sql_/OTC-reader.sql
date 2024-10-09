DECLARE @OTC VARCHAR(100);

SET @OTC = (
SELECT TOP (1)  TBL_SMS_LOG.message_text
FROM TBL_SMS_LOG
WHERE TBL_SMS_LOG.message_text LIKE '%Your Nayax verification code:%'
AND TBL_SMS_LOG.sent_to_user_id LIKE '83950145'
--ORDER BY TBL_SMS_LOG.message_text ASC 
)

PRINT @OTC

SELECT TOP (1)  TBL_SMS_LOG.message_text, *
FROM TBL_SMS_LOG
WHERE TBL_SMS_LOG.message_text LIKE '%Your Nayax verification code:%'


SELECT TOP (11)  TBL_SMS_LOG.message_text, *
FROM TBL_SMS_LOG
WHERE TBL_SMS_LOG.message_text LIKE '%Your Nayax verification code:%'
AND TBL_SMS_LOG.sent_to_user_id LIKE '83950145'
