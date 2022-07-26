package com.project.tcg.domain.user.domain;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.card.exception.CardNotFoundException;
import com.project.tcg.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.tcg.infrastructure.image.DefaultImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
public class User {
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

    @ColumnDefault(DefaultImage.USER_PROFILE_IMAGE)
    private String profileImageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private Authority authority;

    private int coin;

    private int diamond;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserCard> userCards;

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

    public void addCard(Card card) {
        this.userCards.add(UserCard.builder()
                .card(card)
                .user(this)
                .build());
    }

    public void removeCard(Card card) {

        UserCard userCard = this.userCards
                .stream()
                .filter(o -> o.getCard() == card)
                .findFirst()
                .orElseThrow(() -> CardNotFoundException.EXCEPTION);

        this.userCards.remove(userCard);
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void giveResourcesToUser(Card card, Integer coin, User user1) {

        if (card != null) {
            this.removeCard(card);
            user1.userCards.add(UserCard.builder()
                    .card(card)
                    .user(this)
                    .build());
        }
        if (coin != null){
            this.removeCoin(coin);
            user1.addCoin(coin);
        }
    }
}