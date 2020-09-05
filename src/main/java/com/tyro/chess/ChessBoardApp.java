package com.tyro.chess;


import java.util.*;

public class ChessBoardApp {
    private final ChessBoard chessBoard = new ChessBoard();
    private List<Player> players = new ArrayList<>();
    static Map<String, Player> mapInsutructionCoordinate = new HashMap<String, Player>();


    public  ChessBoardApp(List<Player> players ){
        this.players = players;
    }

    public void play(){
        init();
        move();
        getResult();
    }

    private void init(){
        //Initialize start position
        players.stream()
                .forEach(player -> {
                    mapInsutructionCoordinate.put(player.getStartPosition(), player);
                });
    }

    private void move() {
        //Start playing
        players.stream()
                .forEach(
                        player -> {
                            if (player.getType().equalsIgnoreCase("R")) {
                                runRookie(player);
                            } else if (player.getType().equalsIgnoreCase("N")) {
                                runKnight(player);
                            }
                        }
                );
    }

    private void getResult() {
        Map<Player, List<String>> result = new HashMap<Player,List<String> >();
        for (Map.Entry<String, Player> entry : mapInsutructionCoordinate.entrySet()) {
            if(!result.containsKey(entry.getValue())){
                List<String> steps = new ArrayList<String>();
                steps.add(entry.getKey());
                result.put(entry.getValue(),steps);
            }else{
                result.get(entry.getValue()).add(entry.getKey());
            }
        }
        System.out.println("Valid moves");
        for (Map.Entry<Player, List<String>> entry : result.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }

    private void runRookie(Player player) {
        int coordinateX = chessBoard.columns.indexOf(player.getStartPosition().charAt(0));
        int coordinateY = Integer.parseInt(String.valueOf(player.getStartPosition().charAt(1)));
        String key = null;
        //move left
        for (int i = coordinateX - 1; i >= 0; i--) {
            key = String.valueOf(chessBoard.columns.charAt(i)) + coordinateY;
            if (moveFriendOrEnemy(player, key))
                break;
        }
        //move right
        for (int i = coordinateX; i < chessBoard.boardSize; i++) {
            key = String.valueOf(chessBoard.columns.charAt(i)) + coordinateY;
            if (moveFriendOrEnemy(player, key))
                break;
        }
        //move up
        for (int i = coordinateY + 1; i <= chessBoard.boardSize; i++) {
            key = String.valueOf(player.getStartPosition().charAt(0)) + i;
            if (moveFriendOrEnemy(player, key))
                break;
        }

        //move down
        for (int i = coordinateY - 1; i > 0; i--) {
            key = String.valueOf(player.getStartPosition().charAt(0)) + i;
            if (moveFriendOrEnemy(player, key))
                break;
        }
    }

    private void runKnight(Player player) {
        int coordinateX = chessBoard.columns.indexOf(player.getStartPosition().charAt(0));
        int coordinateX1 = coordinateX - 2;
        int coordinateX2 = coordinateX + 2;
        int coordinateY1 = Integer.parseInt(String.valueOf(player.getStartPosition().charAt(1))) - 2;
        int coordinateY2 = Integer.parseInt(String.valueOf(player.getStartPosition().charAt(1))) + 2;
        int countY = 0, countX = 0;
        String key = "";
        for (int i = coordinateY1; i <= coordinateY2; i++) {
            countY = 0;
            for (int j = coordinateX1; j <= coordinateX2; j++) {
                if (i == coordinateY1 || i == coordinateY2) {
                    if (countY == 1 || countY == 3) {
                        key = chessBoard.columns.charAt(j) + "" + i;
                        moveFriendOrEnemy(player, key);
                    }
                    countY++;
                }
                if ((j == coordinateX1 || j == (coordinateX2))) {
                    if (countX == 1 || countX == 3) {
                        key = chessBoard.columns.charAt(j) + "" + i;
                        moveFriendOrEnemy(player, key);
                    }
                }
            }
            countX++;
        }
    }

    private boolean moveFriendOrEnemy(Player player, String key) {
        boolean isKeyExist = mapInsutructionCoordinate.containsKey(key);
        if (!mapInsutructionCoordinate.containsKey(key)) {
            mapInsutructionCoordinate.put(key, player);
        } else {
            Player playerMap = mapInsutructionCoordinate.get(key);
            //enemy
            if (!playerMap.getColor().equalsIgnoreCase(player.getColor())) {
                mapInsutructionCoordinate.put(key, player);
            }
        }
        return isKeyExist;
    }


    public static void main(String args[]){

        Scanner keyboard = new Scanner(System.in);
        String data;
        String numberOfPlay;
        List<Player> players = new ArrayList<Player>();
        System.out.println("> FindValidMove");

        do {
            System.out.print("Enter number of pieces: ");
            numberOfPlay = keyboard.nextLine();

            for (int i = 0; i < Integer.parseInt(numberOfPlay); i++) {
                Player player = new Player();
                player.setNo(i + 1);
                System.out.println("Piece " + (i + 1));

                System.out.println("Enter colour (W/B):");
                data = keyboard.nextLine();
                player.setColor(data);

                System.out.println("Enter type (R/N):");
                data = keyboard.nextLine();
                player.setType(data);

                System.out.println("Enter position:");
                data = keyboard.nextLine();
                player.setStartPosition(data);
                players.add(player);
            }

            new ChessBoardApp(players).play();

            System.out.println("Continue (Y/N)?");
            if("N".equals(keyboard.nextLine()))
                break;

        }while (true);

    }
}
