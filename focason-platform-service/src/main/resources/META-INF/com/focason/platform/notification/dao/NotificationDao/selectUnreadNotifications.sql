SELECT /*%expand*/*
FROM   base005_notification_read base005
WHERE  base005.is_deleted        = 0
  AND  base005.uid               = /* uid */'657a1a1a-8b93-4f0c-99d7-114bd619ad2c'
  AND  base005.is_read           = 0
;