package com.project.tcg.domain.rank.domain;

import com.project.tcg.domain.user.domain.CardCount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(RankId.class)
@Table(name = "ranks")
@Entity
public class Rank {

    @Id
    private Long userId;

    @Id
    private LocalDate createdAt;

    @NotNull
    @Size(max = 30)
    private String accountId;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private String profileImageUrl;

    @NotNull
    @Embedded
    private CardCount cardCount;

    @NotNull
    private Long ranking;
}