package com.project.tcg.domain.card.facade;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.card.domain.repository.CardCollectionRepository;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserCardFacade {

    private final CardCollectionRepository cardCollectionRepository;

    public List<Card> getUserCardList(Long userId) {

        return cardCollectionRepository.findByUserId(userId)
                .stream()
                .map(UserCard::getCard)
                .collect(Collectors.toList());
    }

    public Boolean checkUserCardExist(User user, Long cardId){

        return cardCollectionRepository.findByUserAndCard_id(user,cardId).isPresent();
    }

}