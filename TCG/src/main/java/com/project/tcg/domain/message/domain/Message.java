package com.project.tcg.domain.message.domain

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    private String author;
    private String content;
    private String timestamp;
}