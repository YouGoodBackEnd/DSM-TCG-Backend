package com.project.tcg.domain.user.domain;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.tcg.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(max = 30)
    private String accountId;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 60)
    private String password;

    @ColumnDefault("'" + DefaultImage.USER_PROFILE_IMAGE + "'")
    private String profileImageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private Authority authority;

    @NotNull
    private int coin;

    @NotNull
    private int diamond;

    @Embedded
    private CardCount cardCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCard> userCardList;

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateInfo(UpdateUserInfoRequest request) {
        this.profileImageUrl = request.getProfileImageUrl() == null ? DefaultImage.USER_PROFILE_IMAGE : getProfileImageUrl();
        this.name = request.getUsername();
    }

    public void addCoin(int coin) {
        this.coin += coin;
    }

    public void removeCoin(int coin) {
        this.coin -= coin;
    }

    public void addCard(Card card, int cardCount) {
        this.cardCount.addCount(card.getGrade(), cardCount);
        for (int i = 0; i < cardCount; i++) {
            this.userCardList.add(UserCard.builder()
                    .card(card)
                    .user(this)
                    .build());
        }
    }

    public void removeCard(Card card, int cardCount) {

        this.cardCount.removeCount(card.getGrade(), cardCount);

        List<UserCard> userCardListToDelete = this.userCardList
                .stream()
                .filter(o -> o.getCard() == card)
                .limit(cardCount)
                .collect(Collectors.toList());

        for (UserCard userCard : userCardListToDelete) {
            this.userCardList.remove(userCard);
        }
    }

    public void addDiamond(int diamond) {
        this.diamond += diamond;
    }

    public void giveResourcesToUser(Card card, Integer cardCount, Integer coin, User user) {

        if (card != null) {
            this.removeCard(card, cardCount);
            user.addCard(card, cardCount);
        }
        if (coin != null) {
            this.removeCoin(coin);
            user.addCoin(coin);
        }
    }


}