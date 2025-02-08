package shape.ToyLottery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shape.ToyLottery.service.LotteryService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lottery")
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryService lotteryService;

    @GetMapping
    public String getLottery(
            @RequestParam(required = false) List<Integer> excludeNumbers,
            Model model) {

        if (excludeNumbers == null) {
            excludeNumbers = new ArrayList<>();
        }

        List<Integer> lotteryNumbers = lotteryService.getLotteryNumbers(excludeNumbers);
        model.addAttribute("lotteryNumbers", lotteryNumbers);

        return "lottery"; // templates/lottery.html을 렌더링
    }
}
