package minesweeper;

import components.GridComponent;
import entity.GridStatus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    private GridComponent[][] mineField;
    private int[][] chessboard;  //chessboard数组中-1代表地雷,0-8代表周围地雷个数
    private final Random random = new Random();
    private int xCount = 9;
    private int yCount = 9;
    private int mineCount = 10;

    public int[][] getChessboard() {
        return chessboard;
    }

    public int getxCount() {
        return xCount;
    }

    public int getyCount() {
        return yCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */
    public GamePanel(int xCount, int yCount, int mineCount) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);

        initialGame(xCount, yCount, mineCount);

        repaint();
    }

    public void initialGame(int xCount, int yCount, int mineCount) {
        mineField = new GridComponent[xCount][yCount];

        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                gridComponent.setStatus(chessboard[i][j]);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
            }
        }
    }


    public void generateChessBoard(int xCount, int yCount, int mineCount) {
        //todo: generate chessboard by your own algorithm
        boolean overDense = false;
        int number = 0;
        chessboard = new int[xCount][yCount];
        int[][] temp = new int[xCount+2][yCount+2];
        for (int i = 0; i < xCount+2; i++) {
            for (int j = 0; j < yCount+2; j++) {
                temp[i][j]=0;
            }
        }
        for (int i = 0; i < mineCount; i++) { //生成两个在1-xCount之间的数
            int m = random.nextInt(xCount)+1;
            int n = random.nextInt(yCount)+1;
            while (temp[m][n]!=0){
                m = random.nextInt(xCount)+1;
                n = random.nextInt(yCount)+1;
            }
            temp[m][n]=-1;
        }
        for (int i = 1; i < xCount+1; i++) {
            for (int j = 1; j < yCount+1; j++) {
                if(temp[i][j]!=-1){
                    if(temp[i-1][j-1]==-1)
                        number+=1;
                    if(temp[i-1][j]==-1)
                        number+=1;
                    if(temp[i-1][j+1]==-1)
                        number+=1;
                    if(temp[i][j-1]==-1)
                        number+=1;
                    if(temp[i][j+1]==-1)
                        number+=1;
                    if(temp[i+1][j-1]==-1)
                        number+=1;
                    if(temp[i+1][j]==-1)
                        number+=1;
                    if(temp[i+1][j+1]==-1)
                        number+=1;
                    temp[i][j]=number;
                    number = 0;
                }
            }
        }
        for (int i = 1; i < xCount+1; i++) {
            for (int j = 1; j < yCount+1; j++) {
                chessboard[i-1][j-1] = temp[i][j];
            }
        }
        for (int i = 1; i < xCount+1; i++) {
            for (int j = 1; j < yCount+1; j++) {
                if(temp[i-1][j-1]==-1&&temp[i-1][j]==-1&&temp[i-1][j+1]==-1
                        &&temp[i][j-1]==-1&&temp[i][j]==-1&&temp[i][j+1]==-1
                        &&temp[i+1][j-1]==-1&&temp[i+1][j]==-1&&temp[i+1][j+1]==-1)
                    overDense = true;
            }
        }
        if(overDense)
            generateChessBoard(xCount,yCount,mineCount);
    }


    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void autoOpenGrid(int x, int y) { //未考虑边界条件,改!!
        if (getGrid(x, y).getContent() == 0 && getGrid(x, y).getStatus().equals(GridStatus.CoverNo)) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    getGrid(x + i, y + j).leftOpenGrid();
                    autoOpenGrid(x + i, y + j);
                }
            }
        }
    }

    public List<String> chessBoardToString(){
        List<String> lines = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                stringBuilder.append(String.format(" %d",chessboard[i][j]));
            }
            lines.add(stringBuilder.toString());
            stringBuilder.delete(0,stringBuilder.length());
        }
        return lines;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
