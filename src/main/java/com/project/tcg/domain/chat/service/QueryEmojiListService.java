package com.project.tcg.domain.chat.service;

import com.project.tcg.domain.chat.domain.repository.EmojiRepository;
import com.project.tcg.domain.chat.presentation.dto.response.EmojiResponse;
import com.project.tcg.domain.chat.presentation.dto.response.QueryEmojiListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryEmojiListService {

    private final EmojiRepository emojiRepository;

    public QueryEmojiListResponse execute() {

        List<EmojiResponse> emojiList =
                emojiRepository.findBy()
                        .stream()
                        .map(EmojiResponse::of)
                        .collect(Collectors.toList());

        return new QueryEmojiListResponse(emojiList);
    }
}