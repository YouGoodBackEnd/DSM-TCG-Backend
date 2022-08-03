package com.project.tcg.domain.chest.Facade;

import com.project.tcg.domain.chest.domain.UserChest;
import com.project.tcg.domain.chest.domain.repository.UserChestRepository;
import com.project.tcg.domain.chest.exception.UnopenedChestException;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserChestFacade {

    private final UserChestRepository userChestRepository;

    public UserChest getUserChestById(User user) {
        return userChestRepository.findById(user)
                .orElse(createUserChest(user));
    }

    private UserChest createUserChest(User user) {

        return userChestRepository.save(UserChest.builder()
                .user(user)
                .freeChestOpenDateTime(LocalDateTime.now().minusSeconds(1))
                .specialChestOpenDateTime(LocalDateTime.now().minusSeconds(1))
                .build());
    }

    public void checkOpenableAndRenewFreeChest(User user) {

        UserChest userChest = getUserChestById(user);

        if(userChest.getFreeChestOpenDateTime().isAfter(LocalDateTime.now())){
            throw UnopenedChestException.EXCEPTION;
        }

        userChest.renewFreeChestOpenDateTime();
    }

    public void checkOpenableAndRenewSpecialChest(User user) {

        UserChest userChest = getUserChestById(user);

        if(userChest.getSpecialChestOpenDateTime().isAfter(LocalDateTime.now())){
            throw UnopenedChestException.EXCEPTION;
        }

        userChest.renewSpecialChestOpenDateTime();
    }

}