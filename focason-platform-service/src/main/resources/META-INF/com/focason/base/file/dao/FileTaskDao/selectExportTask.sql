SELECT
    /*%expand*/*
FROM
    io001_file_task
WHERE
    is_deleted = 0
    AND task_type = 2
    AND task_code = /* taskCode */'7a3b8e5e'

