package com.project.tcg.domain.chest.Facade;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.Grade;
import com.project.tcg.domain.card.domain.repository.CardRepository;
import com.project.tcg.domain.card.presentation.dto.response.CardInfoResponse;
import com.project.tcg.domain.chest.domain.DrawProbability;
import com.project.tcg.domain.chest.presentation.dto.response.DrawChestResponse;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ChestFacade {

    static final Random RANDOM = new Random();

    private final CardRepository cardRepository;

    public DrawChestResponse getDrawChestResponse(User user, int drawCount, DrawProbability drawProbability) {

        List<CardInfoResponse> drawnCardList = drawCardList(drawCount, drawProbability)
                .stream()
                .peek(user::addCard)
                .map(CardInfoResponse::of)
                .collect(Collectors.toList());

        int drawnCoin = drawCoin(drawProbability);
        user.addCoin(drawnCoin);

        int drawnDiamond = drawDiamond(drawProbability);
        user.addDiamond(drawnDiamond);

        return DrawChestResponse
                .builder()
                .cardList(drawnCardList)
                .coin(drawnCoin)
                .diamond(drawnDiamond)
                .build();
    }

    private List<Card> drawCardList(int drawCount, DrawProbability drawProbability) {

        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < drawCount; i++) {
            cardList.add(drawCard(drawProbability));
        }
        return cardList;
    }

    private Card drawCard(DrawProbability drawProbability) {

        double result = RANDOM.nextDouble();

        if (result < drawProbability.getGradeSSTotalProbability()) {
            return getRandomCardByGrade(Grade.SS);
        } else if (result < drawProbability.getGradeSTotalProbability()) {
            return getRandomCardByGrade(Grade.S);
        } else if (result < drawProbability.getGradeATotalProbability()) {
            return getRandomCardByGrade(Grade.A);
        } else if (result < drawProbability.getGradeBTotalProbability()) {
            return getRandomCardByGrade(Grade.B);
        } else if (result < drawProbability.getGradeCTotalProbability()) {
            return getRandomCardByGrade(Grade.C);
        } else {
            return null;
        }
    }

    private Card getRandomCardByGrade(Grade grade) {
        List<Card> cardList = cardRepository.findByGrade(grade);
        return cardList.get(RANDOM.nextInt(cardList.size()));
    }

    private int drawCoin(DrawProbability drawProbability) {
        int min = drawProbability.getMinCoin();
        int max = drawProbability.getMaxCoin();
        return RANDOM.nextInt(max - min + 1) + min;
    }

    private int drawDiamond(DrawProbability drawProbability) {
        int min = drawProbability.getMinDiamond();
        int max = drawProbability.getMazDiamond();
        return RANDOM.nextInt(max - min + 1) + min;
    }

}