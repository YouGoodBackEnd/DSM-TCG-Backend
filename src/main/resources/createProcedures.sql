CREATE PROCEDURE SAVE_RANK()
BEGIN
    INSERT INTO ranks(
        created_at, user_id, account_id, name, profile_image_url, ranking,
        ss_grade_card_count, s_grade_card_count, a_grade_card_count, b_grade_card_count, c_grade_card_count
    ) SELECT
          DATE_SUB(DATE_SUB(now(), interval(SECOND(now())) SECOND), interval(MINUTE(now())) MINUTE) AS created_at,
          user.user_id,
          user.account_id ,
          user.name,
          user.profile_image_url AS profile_image_url,
          ROW_NUMBER() OVER (ORDER BY user.ss_grade_card_count DESC,
                                        user.s_grade_card_count DESC,
                                        user.a_grade_card_count DESC,
                                        user.b_grade_card_count DESC,
                                        user.c_grade_card_count DESC) AS ranking,
          user.ss_grade_card_count,
          user.s_grade_card_count,
          user.a_grade_card_count,
          user.b_grade_card_count,
          user.c_grade_card_count

    FROM user;
END$$