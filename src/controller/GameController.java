package controller;

import minesweeper.GamePanel;
import entity.Player;
import minesweeper.ScoreBoard;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GameController {

    private Player p1;
    private Player p2;

    private Player onTurn;

    private GamePanel gamePanel;
    private ScoreBoard scoreBoard;

    public GameController(Player p1, Player p2) {
        this.init(p1, p2);
        this.onTurn = p1;
    }

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;

        //TODO: 在初始化游戏的时候，还需要做什么？
    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {
        if (onTurn == p1) {
            onTurn = p2;
        } else if (onTurn == p2) {
            onTurn = p1;
        }
        System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
        scoreBoard.update();
        //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)

    }


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        return onTurn;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }


    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try {
            //read byte from hard dist -> FileInputStream ( obtains input bytes
            // * from a file in a file system.)
            //switch bytes to characters -> InputStreamReader (An InputStreamReader is a bridge from byte streams to character streams)
            //  BufferedReader
            // Reads text from a character-input stream, buffering characters so as to
            // * provide for the efficient reading of characters, arrays, and lines.

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

//            BufferedReader bufferedReader1= new BufferedReader(new FileReader(path));
            String line;
            //read characters from bufferedReader
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (String line : lines
            ) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
