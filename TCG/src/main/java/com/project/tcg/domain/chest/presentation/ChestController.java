package com.project.tcg.domain.chest.presentation;

import com.project.tcg.domain.chest.presentation.dto.response.DrawChestResponse;
import com.project.tcg.domain.chest.service.DrawFreeChestService;
import com.project.tcg.domain.chest.service.DrawGoldChestService;
import com.project.tcg.domain.chest.service.DrawLegendChestService;
import com.project.tcg.domain.chest.service.DrawSilverChestService;
import com.project.tcg.domain.chest.service.DrawSpecialChestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class ChestController {

    private final DrawFreeChestService drawFreeChestService;
    private final DrawSpecialChestService drawSpecialChestService;
    private final DrawSilverChestService drawSilverChestService;
    private final DrawGoldChestService drawGoldChestService;
    private final DrawLegendChestService drawLegendChestService;

    @PatchMapping("/draw/free")
    public DrawChestResponse drawFreeChest(){
        return drawFreeChestService.execute();
    }

    @PatchMapping("/draw/special")
    public DrawChestResponse drawSpecialChest(){
        return drawSpecialChestService.execute();
    }

    @PatchMapping("/draw/silver")
    public DrawChestResponse drawSilverChest(){
        return drawSilverChestService.execute();
    }

    @PatchMapping("/draw/gold")
    public DrawChestResponse drawGoldChest(){
        return drawGoldChestService.execute();
    }

    @PatchMapping("/draw/legend")
    public DrawChestResponse drawLegendChest(){
        return drawLegendChestService.execute();
    }

}