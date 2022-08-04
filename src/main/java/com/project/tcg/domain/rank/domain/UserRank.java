package com.project.tcg.domain.rank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserRankId.class)
@Entity
public class UserRank {
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
    private Long ranking;
}