package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Emoji;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmojiRepository extends CrudRepository<Emoji, Long> {

    List<Emoji> findBy();

}