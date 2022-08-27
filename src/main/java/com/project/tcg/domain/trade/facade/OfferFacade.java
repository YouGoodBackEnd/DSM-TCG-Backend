package com.project.tcg.domain.trade.facade;

import com.project.tcg.domain.card.facade.UserCardFacade;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.exception.BadOfferException;
import com.project.tcg.domain.trade.exception.CardLackException;
import com.project.tcg.domain.trade.exception.CoinLackException;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OfferFacade {

    private final UserCardFacade userCardFacade;

    public Offer validateAndGetOffer(Long cardId, Integer cardCount, Integer coin, User user) {

        validateCoin(coin, user);
        validateCard(cardId, cardCount, user);

        return Offer.builder()
                .cardId(cardId)
                .cardCount(cardCount)
                .coin(coin)
                .build();
    }

    private void validateCoin(Integer coin, User user) {
        if (coin != null) {
            if (user.getCoin() < coin) {
                throw CoinLackException.EXCEPTION;
            }
        }
    }

    private void validateCard(Long cardId, Integer cardCount, User user) {
        if (cardId != null) {
            if (cardCount == null) {
                throw BadOfferException.EXCEPTION;
            }
            else if (userCardFacade.getUserCardCount(cardId, user) < cardCount) {
                throw CardLackException.EXCEPTION;
            }
        } else {
            if (cardCount != null) {
                throw BadOfferException.EXCEPTION;
            }
        }
    }

}