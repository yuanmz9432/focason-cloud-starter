-- =====================================================
-- Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
-- =====================================================
SELECT /*%expand*/*
FROM   base003_user_token
WHERE is_deleted = 0
AND   uid        = /* uid */'Focason'
AND   device_id  = /* deviceId */'Focason'
