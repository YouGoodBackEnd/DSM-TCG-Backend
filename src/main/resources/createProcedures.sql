CREATE PROCEDURE SAVE_USER_RANK()
BEGIN
    INSERT INTO user_rank(
        created_at, user_id, account_id, name, profile_image_url, ranking
    ) SELECT
        CONVERT(date,GETDATE()) AS created_at,
        user.user_id AS user_id,
        user.account_id AS account_id,
        user.name AS name,
        user.profile_image_url AS profile_image_url,
        ROW_NUMBER() OVER (ORDER BY user.ss_grade_caard_count DESC,
                                    user.s_grade_caard_count DESC,
                                    user.a_grade_caard_count DESC,
                                    user.b_grade_caard_count DESC,
                                    user.c_grade_caard_count DESC) AS ranking
    FROM user
END$$