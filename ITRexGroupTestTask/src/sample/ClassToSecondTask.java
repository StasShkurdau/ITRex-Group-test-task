package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClassToSecondTask {

    Areas[][][] labyrinth;
    int[] playerPosition;
    int[] princessPosition;
    int time;


    public ClassToSecondTask() {
        areaInitialization();
        time = 0;
    }

    public Integer startGame(){
        int deltaX;
        int deltaY;
        while((playerPosition[0] != princessPosition[0]) || (playerPosition[1] != princessPosition[1]) || (playerPosition[2] != princessPosition[2])){
            deltaX = princessPosition[1] - playerPosition[1];
            deltaY = princessPosition[2] - playerPosition[2];
            if(playerPosition[0] != princessPosition[0]){
                if(labyrinth[playerPosition[0]+1][playerPosition[1]][playerPosition[2]] == Areas.FLOR){
                    brakeFlor();

                } else if(deltaX > 0 && (labyrinth[playerPosition[0]][playerPosition[1] + 1][playerPosition[2]] == Areas.FLOR) && (labyrinth[playerPosition[0]+1][playerPosition[1] + 1][playerPosition[2]] == Areas.FLOR)){
                    playerPosition[1]++;

                }else if(deltaY > 0 && (labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] + 1] == Areas.FLOR) && (labyrinth[playerPosition[0]+1][playerPosition[1]][playerPosition[2] + 1] == Areas.FLOR)){
                    playerPosition[2]++;

                }else if(deltaX < 0 && (labyrinth[playerPosition[0]][playerPosition[1] - 1][playerPosition[2]] == Areas.FLOR) && (labyrinth[playerPosition[0]+1][playerPosition[1] - 1][playerPosition[2]] == Areas.FLOR)){
                    playerPosition[1]--;

                }else if( deltaX < 0 &&(labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] - 1] == Areas.FLOR) && (labyrinth[playerPosition[0]+1][playerPosition[1]][playerPosition[2] - 1] == Areas.FLOR)){
                    playerPosition[2]--;

                }else if(deltaX > 0 && (labyrinth[playerPosition[0]][playerPosition[1] + 1][playerPosition[2]] == Areas.PILLAR) && (labyrinth[playerPosition[0]+1][playerPosition[1] + 1][playerPosition[2]] == Areas.FLOR)){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1] + 1, playerPosition[2]});

                }else if(deltaY > 0 && (labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] + 1] == Areas.PILLAR) && (labyrinth[playerPosition[0]+1][playerPosition[1]][playerPosition[2] + 1] == Areas.FLOR)){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1], playerPosition[2] + 1});

                }else if(deltaX < 0 && (labyrinth[playerPosition[0]][playerPosition[1] - 1][playerPosition[2]] == Areas.PILLAR) && (labyrinth[playerPosition[0]+1][playerPosition[1] - 1][playerPosition[2]] == Areas.FLOR)){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1] - 1, playerPosition[2]});

                }else if(deltaY < 0 && (labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] - 1] == Areas.PILLAR) && (labyrinth[playerPosition[0]+1][playerPosition[1]][playerPosition[2] - 1] == Areas.FLOR)){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1], playerPosition[2] - 1});

                }

            }else{

                if(labyrinth[playerPosition[0]][playerPosition[1] + 1][playerPosition[2]] == Areas.PRINCESS){
                playerPosition[1]++;

                }else if(labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] + 1] == Areas.PRINCESS){
                    playerPosition[2]++;

                }else if(labyrinth[playerPosition[0]][playerPosition[1] - 1][playerPosition[2]] == Areas.PRINCESS){
                    playerPosition[1]--;

                }else if(labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] - 1] == Areas.PRINCESS){
                    playerPosition[2]--;

                }else if(deltaX > 0 && labyrinth[playerPosition[0]][playerPosition[1] + 1][playerPosition[2]] == Areas.FLOR){
                    playerPosition[1]++;

                }else if(deltaX < 0 && labyrinth[playerPosition[0]][playerPosition[1] - 1][playerPosition[2]] == Areas.FLOR){
                    playerPosition[1]--;

                }else if(deltaY < 0 && labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] + 1] == Areas.FLOR){
                playerPosition[2]++;

                }else if(deltaY < 0 && labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] - 1] == Areas.FLOR){
                playerPosition[2]--;
                }else if(deltaX > 0 && labyrinth[playerPosition[0]][playerPosition[1] + 1][playerPosition[2]] == Areas.PILLAR){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1] + 1, playerPosition[2]});

                }else if(deltaX < 0 && labyrinth[playerPosition[0]][playerPosition[1] - 1][playerPosition[2]] == Areas.PILLAR){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1] - 1, playerPosition[2]});

                }else if(deltaY < 0 && labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] + 1] == Areas.PILLAR){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1], playerPosition[2] + 1});

                }else if(deltaY < 0 && labyrinth[playerPosition[0]][playerPosition[1]][playerPosition[2] - 1] == Areas.PILLAR){
                    brakePillar(new int[] {playerPosition[0], playerPosition[1], playerPosition[2] - 1});
                }

            }
            addTime();
        }
        return time;
    }

    public void printArea(){
        for (Areas ar[][]: labyrinth) {
            System.out.println();
            for (Areas ar2[]: ar) {
                System.out.println();
                for(Areas ar3 : ar2){
                    System.out.print(ar3 + ", ");
                }
            }

        }
    }

    private void areaInitialization(){
        try {
            File file = new File("src/sample/INPUT.TXT");
            Scanner fileScanner = new Scanner(file);
            int levels = fileScanner.nextInt();
            int height = fileScanner.nextInt();
            int width = fileScanner.nextInt();
            labyrinth = new Areas[levels][height + 2][width + 2];
            playerPosition = new int[3];
            princessPosition = new int[3];

            for(int levelCount = 0; levelCount < levels; levelCount++){

                for(int heightCount = 0; heightCount < height + 2; heightCount++){
                    String line ="";
                    if(heightCount == 0 || heightCount == height + 1){
                        for(int widthBorderCount = 0; widthBorderCount < width + 2; widthBorderCount++){
                            line += "w";
                        }
                    }else{
                        line = fileScanner.next();
                        line = "w" +line + "w";
                    }
                    int widthCount = 0;

                    for(char newChar : line.toCharArray()){
                        switch (newChar){
                            case('1'):
                                labyrinth[levelCount][heightCount][widthCount] = Areas.PLAYER;
                                playerPosition[0] = levelCount;
                                playerPosition[1] = heightCount;
                                playerPosition[2] = widthCount;
                                break;
                            case('.'):
                                labyrinth[levelCount][heightCount][widthCount] = Areas.FLOR;
                                break;
                            case('o'):
                                labyrinth[levelCount][heightCount][widthCount] = Areas.PILLAR;
                                break;
                            case('2'):
                                labyrinth[levelCount][heightCount][widthCount] = Areas.PRINCESS;
                                princessPosition[0] = levelCount;
                                princessPosition[1] = heightCount;
                                princessPosition[2] = widthCount;
                                break;
                            case('w'):
                                labyrinth[levelCount][heightCount][widthCount] = Areas.WALL;
                                break;
                        }
                        widthCount++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private  void brakeFlor(){
        playerPosition[0]++;
    }
    private void brakePillar(int[] position){
        labyrinth[position[0]][position[1]][position[2]] = Areas.FLOR;
    }

    private void addTime(){
        time += 5;
    }

}
