package com.project.tcg.domain.chat.domain;

import com.project.tcg.domain.chat.exception.FulledRoomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<RoomUser> roomUsers = new ArrayList<>();

    public void addRoomUser(RoomUser roomUser) {
        this.roomUsers.add(roomUser);
    }

    public void removeRoomUser(RoomUser roomUser) {
        this.roomUsers.remove(roomUser);
    }

    public boolean isAllUserOffered() {

        int offeredUserCount = (int) roomUsers.stream()
                .filter(RoomUser::getIsOffered).count();

        return offeredUserCount == roomUsers.size();
    }

    public boolean isAllUserAccepted() {

        int acceptedUserCount = (int) roomUsers.stream()
                .filter(RoomUser::getIsAccepted).count();

        return acceptedUserCount == roomUsers.size();
    }

    public boolean isUserAccepted() {

        int acceptedUserCount = (int) roomUsers.stream()
                .filter(RoomUser::getIsAccepted).count();

        return acceptedUserCount > 0;
    }

    public void checkIsNotFulled() {
        if(this.roomUsers.size() >= 2) {
            throw FulledRoomException.EXCEPTION;
        }
    }


}