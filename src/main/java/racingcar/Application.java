package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // 경주 자동차 리스트, 시도 횟수 입력
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        List<RacingCar> carList = car_input(Console.readLine());
        System.out.println("시도할 횟수는 몇 회인가요?");
        int tryCount = count_input(Console.readLine());

        // 경주 시작
        System.out.println("실행 결과");
        for (int i = 0; i < tryCount; i++) {
            for(RacingCar car : carList){
                car.move();
                System.out.println(car.getName() + " : " + car.getlength(car.getDistance()));
            }
            System.out.println();
        }

        // 경주 결과 출력
        int maxDistance = getMaxDistance(carList);
        String winners = findWinner(carList, maxDistance);


        System.out.println("최종 우승자 : " + winners);



    }

    public static List<RacingCar> car_input(String input){
        String[] carArray = input.split(",");
        List<RacingCar> cars = new ArrayList<>();
        for(String name : carArray){
            cars.add(new RacingCar(name.trim()));
        }
        return cars;
    }

    public static int count_input(String input){
        return Integer.parseInt(input);
    }


    public static int getMaxDistance(List<RacingCar> carList){
        int maxDistance = carList.stream()
                .mapToInt(RacingCar::getDistance)
                .max()
                .orElse(0);
        return maxDistance;
    }

    public static String findWinner(List<RacingCar> carList, int maxDistance){
        StringBuilder winner = new StringBuilder();
        for(RacingCar car : carList){
            if(car.getDistance() == maxDistance){
                if(winner.length() > 0){
                    winner.append(", ");
                }
                winner.append(car.getName());
            }
        }
        return winner.toString();
    }
}


