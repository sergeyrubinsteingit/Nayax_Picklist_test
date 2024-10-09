DECLARE @columns NVARCHAR(max);
DECLARE @sql NVARCHAR(max)
SET @sql = 'SELECT ';

DECLARE @TBL_NAME AS VARCHAR(100)
SET @TBL_NAME = 'TBL_SMS_LOG' 

DECLARE @COL_NAME1 AS VARCHAR(100)
SET @COL_NAME1 = 'phone_number'

DECLARE @COL_NAME2 AS VARCHAR(100)
SET @COL_NAME2 = 'message_text'

DECLARE @COL_NAME3 AS VARCHAR(100)
SET @COL_NAME3 = 'sent_dt'


SELECT @sql = @sql + '[' + column_name +'],'
FROM information_schema.columns 
WHERE table_name = @TBL_NAME AND column_name in (@COL_NAME3, @COL_NAME2, @COL_NAME1)

SET @sql = left(@sql,len(@sql)-1) -- remove trailing comma
PRINT '>>> ' + @sql + ' FROM TBL_SMS_LOG'
SET @sql = @sql + ' FROM TBL_SMS_LOG'

EXEC sp_executesql @sql