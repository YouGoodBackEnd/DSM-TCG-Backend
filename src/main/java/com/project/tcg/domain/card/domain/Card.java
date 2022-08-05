package com.project.tcg.domain.card.domain;

import com.project.tcg.domain.card.domain.enums.CardCode;
import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private String cardImageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Grade grade;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30, unique = true)
    private CardCode code;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<UserCard> userCards;

}