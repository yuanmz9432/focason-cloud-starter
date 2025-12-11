SELECT
    /*%expand*/*
FROM
    io001_file_task io001
WHERE
    is_deleted = 0
    /*%if condition.getBusinessUnit() != null */
    AND io001.business_unit like /* @infix(condition.getBusinessUnit()) */'%X%' escape '$'
    /*%end */
    /*%if condition.getFileType() != null && condition.getFileType().size() > 0 */
    AND io001.file_type IN /* condition.getFileType() */(1,2,3)
    /*%end */
    /*%if condition.getFileModule() != null && condition.getFileModule().size() > 0 */
    AND io001.file_module IN /* condition.getFileModule() */(1,2,3)
    /*%end */
    /*%if condition.getFileTaskStatus() != null && condition.getFileTaskStatus().size() > 0 */
    AND io001.status IN /* condition.getFileTaskStatus() */(1,2,3)
    /*%end */
ORDER BY
/*# sort.toSql() */
