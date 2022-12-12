package com.project.tcg.domain.chest.domain;

import com.project.tcg.domain.chest.domain.enums.ChestType;
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
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class UserChest {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime freeChestOpenDateTime;

    @NotNull
    private LocalDateTime specialChestOpenDateTime;

    public void renewFreeChestOpenDateTime(){
        this.freeChestOpenDateTime = LocalDateTime.now().plusMinute(ChestType.FREE_CHEST.getRenewalTime());
    }

    public void renewSpecialChestOpenDateTime(){
        this.specialChestOpenDateTime = LocalDateTime.now().plusMinute(ChestType.SPECIAL_CHEST.getRenewalTime());
    }
}