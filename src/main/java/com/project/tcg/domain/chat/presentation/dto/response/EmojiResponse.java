package com.project.tcg.domain.chat.presentation.dto.response;

import com.project.tcg.domain.chat.domain.Emoji;
import com.project.tcg.domain.chat.domain.enums.EmojiType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmojiResponse {

    private Long emojiId;
    private EmojiType emojiType;
    private String emojiImageUrl;

    public static EmojiResponse of(Emoji emoji) {

        return EmojiResponse
                .builder()
                .emojiId(emoji.getId())
                .emojiType(emoji.getEmojiType())
                .emojiImageUrl(emoji.getEmojiImageUrl())
                .build();
    }
}