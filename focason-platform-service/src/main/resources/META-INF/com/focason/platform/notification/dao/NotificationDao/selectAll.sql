SELECT base004.notification_id
     , base004.title
     , base004.content
     , base004.status
     , base004.type
     , base004.notify_at
     , base005.uid
     , base005.is_read
     , base005.read_at
FROM   base004_notification      base004
     , base005_notification_read base005
WHERE  base005.is_deleted        = 0
AND    base005.uid               = /* condition.getUid() */'657a1a1a-8b93-4f0c-99d7-114bd619ad2c'
AND    base004.notification_id   = base005.notification_id
ORDER BY /*# sort.toSql() */
