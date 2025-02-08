package shape.ToyLottery.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LotteryService {

    public List<Integer> getLotteryNumbers(List<Integer> excludeNumbers) {

        Random random = new Random();

        boolean allSame;
        boolean allDistinct;
        List<Integer> extractABCDE;
        do {
            extractABCDE = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                extractABCDE.add(random.nextInt(5));
            }
            extractABCDE.sort(Integer::compareTo);

            allSame = extractABCDE.stream().distinct().count() == 1;
            allDistinct = extractABCDE.stream().distinct().count() == 5;

        } while (allSame || allDistinct);

        while (true) {
            List<Integer> lotteryList = new ArrayList<>();

            for (int iter = 0; iter < 6; iter++) {
                int page = extractABCDE.get(iter);
                int min = 1 + page * 10;
                int max;
                if (page == 4) {
                    max = 45;
                } else {
                    max = 9 + page * 10;
                }

                int randomInt;
                do {
                    randomInt = random.nextInt(max - min + 1) + min;
                } while (excludeNumbers.contains(randomInt) || lotteryList.contains(randomInt));

                lotteryList.add(randomInt);
            }

            lotteryList.sort(Integer::compareTo);

            boolean isContinuousOne = lotteryList.stream()
                    .skip(1)
                    .filter(num -> 1 + lotteryList.get(lotteryList.indexOf(num) - 1) == num)
                    .count() == 1;

            if (isContinuousOne)
                return lotteryList;
        }
    }
}
