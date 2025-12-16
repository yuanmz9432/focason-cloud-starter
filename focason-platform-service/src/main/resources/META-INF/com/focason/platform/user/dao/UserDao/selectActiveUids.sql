-- =====================================================
-- Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
-- =====================================================
SELECT uid
FROM   base001_user
WHERE  is_deleted = 0
AND    status = 1
