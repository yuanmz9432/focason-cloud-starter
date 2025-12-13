SELECT
    /*%expand*/*
FROM
    iv102_inventory_adjustment_history
WHERE
    is_deleted = 0
    /*%if condition.getIds() != null */
    AND id IN /* condition.getIds() */(1,2,3)
    /*%end */
ORDER BY
    /*# sort.toSql() */