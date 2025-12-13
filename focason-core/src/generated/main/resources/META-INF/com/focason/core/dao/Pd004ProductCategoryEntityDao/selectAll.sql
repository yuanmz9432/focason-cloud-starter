SELECT
    /*%expand*/*
FROM
    pd004_product_category
WHERE
    is_deleted = 0
    /*%if condition.getIds() != null */
    AND id IN /* condition.getIds() */(1,2,3)
    /*%end */
ORDER BY
    /*# sort.toSql() */