-- =====================================================
-- Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
-- =====================================================
SELECT mg005.id
     , mg005.username
     , mg005.email
     , mg005.type
     , mg005.uuid
FROM   mg005_user mg005
WHERE  mg005.is_deleted = 0
       /*%if condition.getUsername() != null */
AND    mg005.username like /* @infix(condition.getUsername()) */'%X%' escape '$'
       /*%end */
       /*%if condition.getEmail() != null */
AND    mg005.email like /* @infix(condition.getEmail()) */'%X%' escape '$'
       /*%end */
GROUP BY mg005.uuid
ORDER BY /*# sort.toSql() */
