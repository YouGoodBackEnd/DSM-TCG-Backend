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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class RoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Boolean isAccepted;

    @Column
    private Boolean isOffered;

    @Embedded
    private Offer offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void acceptFalse() {
        this.isAccepted = false;
    }

    public void setOffer(Offer offer) {
        if (offer.getCardId() == null && offer.getCoin() == null) {
            this.isOffered = false;
            this.offer = null;
        } else {
            this.isOffered = true;
            this.offer = offer;
        }
    }

    public void doAccept() {
        this.isAccepted = true;
    }

    public void cancelAccept() {
        this.isAccepted = false;
    }
}