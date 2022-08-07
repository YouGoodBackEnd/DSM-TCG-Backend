package com.project.tcg.domain.chat.domain;

import com.project.tcg.domain.user.domain.User;
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

    public boolean checkBothOffered() {

        int offeredUserCount = (int) roomUsers
                .stream()
                .filter(RoomUser::getIsOffered)
                .count();

        return offeredUserCount == roomUsers.size();
    }

    public boolean checkBothAccepted() {

        int acceptedUserCount = (int) roomUsers.stream()
                .filter(RoomUser::getIsAccepted)
                .count();

        return acceptedUserCount == roomUsers.size();
    }

    public void addRoomUser(RoomUser roomUser) {
        this.roomUsers.add(roomUser);
    }

    public void removeUser(User user) {
        this.roomUsers.removeIf(roomUser -> roomUser.getUser() == user);
    }
}