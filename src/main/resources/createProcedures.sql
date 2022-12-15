CREATE PROCEDURE SAVE_RANK()
BEGIN
    INSERT INTO tcg.ranks(
        created_at, user_id, account_id, name, profile_image_url, ranking,
        s_grade_card_count, a_grade_card_count, b_grade_card_count, c_grade_card_count, d_grade_card_count
    ) SELECT
        DATE(now()) AS created_at,
        user.user_id,
        user.account_id ,
        user.name,
        user.profile_image_url AS profile_image_url,
        ROW_NUMBER() OVER (ORDER BY user.s_grade_card_count DESC,
            user.a_grade_card_count DESC,
            user.b_grade_card_count DESC,
            user.c_grade_card_count DESC,
            user.d_grade_card_count DESC) AS ranking,
        user.s_grade_card_count,
        user.a_grade_card_count,
        user.b_grade_card_count,
        user.c_grade_card_count,
        user.d_grade_card_count
    FROM tcg.user;
END$$