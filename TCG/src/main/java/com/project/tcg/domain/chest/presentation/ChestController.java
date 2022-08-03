package com.project.tcg.domain.chest.presentation;

import com.project.tcg.domain.chest.presentation.dto.response.DrawChestResponse;
import com.project.tcg.domain.chest.service.DrawFreeChestService;
import com.project.tcg.domain.chest.service.DrawGoldChestService;
import com.project.tcg.domain.chest.service.DrawLegendChestService;
import com.project.tcg.domain.chest.service.DrawSilverChestService;
import com.project.tcg.domain.chest.service.DrawSpecialChestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/draw/free")
    public DrawChestResponse drawFreeChest(){
        return drawFreeChestService.execute();
    }

    @PostMapping("/draw/special")
    public DrawChestResponse drawSpecialChest(){
        return drawSpecialChestService.execute();
    }

    @PostMapping("/draw/silver")
    public DrawChestResponse drawSilverChest(){
        return drawSilverChestService.execute();
    }

    @PostMapping("/draw/gold")
    public DrawChestResponse drawGoldChest(){
        return drawGoldChestService.execute();
    }

    @PostMapping("/draw/legend")
    public DrawChestResponse drawLegendChest(){
        return drawLegendChestService.execute();
    }

}