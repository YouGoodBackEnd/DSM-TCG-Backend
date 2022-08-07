package com.project.tcg.domain.chat.domain;

import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class RoomUser {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private Boolean isAccepted;

    @NotNull
    private Boolean isOffered;

    @Embedded
    private Offer offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Boolean setOffer(Offer offer) {
        if ((this.offer == null && offer == null) || Objects.equals(this.offer, offer)) {
            return false;
        } else {
            if (offer.getCardId() == null && offer.getCoin() == null) {
                this.isOffered = false;
                this.offer = null;
            } else {
                this.isOffered = true;
                this.offer = offer;
            }
            return true;
        }
    }

    public void doAccept() {
        this.isAccepted = true;
    }

    public void cancelAccept() {
        this.isAccepted = false;
    }
}