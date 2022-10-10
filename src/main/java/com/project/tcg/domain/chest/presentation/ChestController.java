package com.project.tcg.domain.chest.presentation;

import com.project.tcg.domain.chest.presentation.dto.request.ChestPurchaseRequest;
import com.project.tcg.domain.chest.presentation.dto.response.DrawChestResponse;
import com.project.tcg.domain.chest.presentation.dto.response.QueryChestOpenDateTimeResponse;
import com.project.tcg.domain.chest.service.DrawFreeChestService;
import com.project.tcg.domain.chest.service.DrawGoldChestService;
import com.project.tcg.domain.chest.service.DrawLegendChestService;
import com.project.tcg.domain.chest.service.DrawSilverChestService;
import com.project.tcg.domain.chest.service.DrawSpecialChestService;
import com.project.tcg.domain.chest.service.QueryFreeChestOpenDateTimeService;
import com.project.tcg.domain.chest.service.QuerySpecialChestOpenDateTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/chests")
@RestController
public class ChestController {

    private final DrawFreeChestService drawFreeChestService;
    private final DrawSpecialChestService drawSpecialChestService;
    private final DrawSilverChestService drawSilverChestService;
    private final DrawGoldChestService drawGoldChestService;
    private final DrawLegendChestService drawLegendChestService;
    private final QueryFreeChestOpenDateTimeService queryFreeChestOpenDateTimeService;
    private final QuerySpecialChestOpenDateTimeService querySpecialChestOpenDateTimeService;

    @PostMapping("/free")
    public DrawChestResponse drawFreeChest(){
        return drawFreeChestService.execute();
    }

    @GetMapping("/free")
    public QueryChestOpenDateTimeResponse queryFreeChestOpenDateTime(){
        return queryFreeChestOpenDateTimeService.execute();
    }

    @PostMapping("/special")
    public DrawChestResponse querySpecialChestOpenDateTime(){
        return drawSpecialChestService.execute();
    }

    @GetMapping("/special")
    public QueryChestOpenDateTimeResponse drawSpecialChest(){
        return querySpecialChestOpenDateTimeService.execute();
    }

    @PostMapping("/silver")
    public DrawChestResponse drawSilverChest(@RequestBody @Valid ChestPurchaseRequest request){
        return drawSilverChestService.execute(request);
    }

    @PostMapping("/gold")
    public DrawChestResponse drawGoldChest(@RequestBody @Valid ChestPurchaseRequest request){
        return drawGoldChestService.execute(request);
    }

    @PostMapping("/legend")
    public DrawChestResponse drawLegendChest(@RequestBody @Valid ChestPurchaseRequest request){
        return drawLegendChestService.execute(request);
    }

}