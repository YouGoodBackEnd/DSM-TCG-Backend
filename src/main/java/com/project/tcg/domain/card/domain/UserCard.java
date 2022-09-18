package com.project.tcg.domain.card.domain;

import com.project.tcg.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserCardId.class)
@AllArgsConstructor
@Entity
public class UserCard {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Integer count;

    public void addUserCard(int addCount) {
        count += addCount;
    }

    public void deleteUserCard(int deleteCount) {
        count -= deleteCount;
    }
}