package com.project.tcg.domain.trade.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<RoomUser> RoomUsers = new ArrayList<>();

    public boolean checkBothOffered() {

        int offeredUserCount = (int) RoomUsers.stream()
                .filter(RoomUser::getIsOffered)
                .count();

        return offeredUserCount == RoomUsers.size();
    }

    public boolean checkBothAccepted() {

        int acceptedUserCount = (int) RoomUsers.stream()
                .filter(RoomUser::getIsAccepted)
                .count();

        return acceptedUserCount == RoomUsers.size();
    }
}