package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Emoji;
import com.project.tcg.domain.chat.domain.enums.EmojiType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmojiRepository extends CrudRepository<Emoji, Long> {

    Optional<Emoji> findByEmojiType(EmojiType emojiType);
}