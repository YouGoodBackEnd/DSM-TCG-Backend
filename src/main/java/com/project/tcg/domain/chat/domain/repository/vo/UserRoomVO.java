package com.project.tcg.domain.chat.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
public class UserRoomVO {

    private final Long roomId;
    private final Long userId;
    private final String profileImageUrl;
    private final  String userName;

    @QueryProjection
    public UserRoomVO(Long roomId, Long userId, String profileImageUrl, String userName) {
        this.roomId = roomId;
        this.userId = userId;
        this.profileImageUrl = profileImageUrl;
        this.userName = userName;
    }
}