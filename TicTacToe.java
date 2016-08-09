/**
 * Created by Sasha on 09.08.2016.
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class TicTacToe  implements ActionListener{

    JFrame frame = new JFrame();
    JPanel jPanel = new JPanel();
    Button [] squares;
    Button [][] squares2;
    Button newGameButton;
    Label score;
    Label scoreYou;
    Label scoreComp;
    static int you = 1;
    static int comp = 1;
    int emptySquaresLeft = 9;

    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe();
        toe.go();
    }





    public void go()
    {

        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.CYAN);

        Font appletFont = new Font("Monospased", Font.BOLD, 20);
        jPanel.setFont(appletFont);
        scoreYou = new Label("You ");
        scoreComp = new Label("Comp");
        newGameButton = new Button("New Game");

        newGameButton.addActionListener(this);


        Panel topPanel = new Panel();
        Panel topPanel2 = new Panel();
        topPanel2.setLayout(new GridLayout());
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(newGameButton);
        topPanel2.add(scoreYou, "West");
        topPanel2.add(scoreComp, "East");
        topPanel.setLayout(new GridLayout(2, 2));

        topPanel.add(topPanel2);

        jPanel.add(topPanel, "North");

        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(3, 3));
        jPanel.add(centerPanel, "Center");

        score = new Label("Your turn");
        jPanel.add(score, "South");

        squares2 = new Button[3][3];

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {

                squares2[i][j] = new Button();
                squares2[i][j].addActionListener(this);
                squares2[i][j].setBackground(Color.ORANGE);
                centerPanel.add(squares2[i][j]);
            }
        }
        frame.add(jPanel);

        frame.getDefaultCloseOperation();
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){

        Button theButton = (Button) e.getSource();
        String clickedButtonLabel = theButton.getLabel();


        if(theButton == newGameButton){
            for(int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {

                    squares2[i][j].setEnabled(true);
                    squares2[i][j].setLabel("");
                    squares2[i][j].setBackground(Color.ORANGE);


                }
            }
            emptySquaresLeft = 9;
            score.setText("Your turn !");
            newGameButton.setEnabled(false);

            return;
        }

        String winner = "";





        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (clickedButtonLabel == "x" || clickedButtonLabel == "0")
                {

                } else if (theButton == squares2[i][j])
                {


                    squares2[i][j].setLabel("x");

                    winner = lookForWinner();

                    if (!"".equals(winner))
                    {

                        endTheGame();

                    } else
                    {

                        computerMove();
                        winner = lookForWinner();

                        if (!"".equals(winner))
                        {
                            endTheGame();
                        }
                    }
                    break;
                }
            }
        }
        if(winner.equals("x")){
            score.setText("You won ");
            scoreYou.setText("You " + you++);

        }else  if(winner.equals("0")){
            score.setText("You lost !");
            scoreComp.setText("Comp " + comp++);

        }else  if(winner.equals("T")){
            score.setText("It's a tie !");
        }
    }




    String lookForWinner(){

        String theWinner = "";
        emptySquaresLeft--;

        if(emptySquaresLeft == 0){
            return "T";
        }

        if(!squares2[0][0].getLabel().equals("") &&

                squares2[0][0].getLabel().equals(squares2[0][1].getLabel()) &&
                squares2[0][0].getLabel().equals(squares2[0][2].getLabel())){

            theWinner = squares2[0][0].getLabel();
            highlightWinner(0,0,0,1,0,2);

        }else if(!squares2[1][0].getLabel().equals("") &&

                squares2[1][0].getLabel().equals(squares2[1][1].getLabel()) &&
                squares2[1][0].getLabel().equals(squares2[1][2].getLabel())){

            theWinner = squares2[1][0].getLabel();
            highlightWinner(1,0,1,1,1,2);

        }else if(!squares2[2][0].getLabel().equals("") &&

                squares2[2][0].getLabel().equals(squares2[2][1].getLabel()) &&
                squares2[2][0].getLabel().equals(squares2[2][2].getLabel())){

            theWinner = squares2[2][0].getLabel();
            highlightWinner(2,0,2,1,2,2);

        }else if(!squares2[0][0].getLabel().equals("") &&

                squares2[0][0].getLabel().equals(squares2[1][0].getLabel()) &&
                squares2[0][0].getLabel().equals(squares2[2][0].getLabel())){

            theWinner = squares2[2][0].getLabel();
            highlightWinner(0,0,1,1,2,0);

        }else if(!squares2[0][1].getLabel().equals("") &&

                squares2[0][1].getLabel().equals(squares2[1][1].getLabel()) &&
                squares2[0][1].getLabel().equals(squares2[2][1].getLabel())){

            theWinner = squares2[0][1].getLabel();
            highlightWinner(0,1,1,1,2,1);

        }else if(!squares2[0][2].getLabel().equals("") &&

                squares2[0][2].getLabel().equals(squares2[1][2].getLabel()) &&
                squares2[0][2].getLabel().equals(squares2[2][2].getLabel())){

            theWinner = squares2[0][2].getLabel();
            highlightWinner(0,2,1,2,2,2);

        }else if(!squares2[0][0].getLabel().equals("") &&

                squares2[0][0].getLabel().equals(squares2[1][1].getLabel()) &&
                squares2[0][0].getLabel().equals(squares2[2][2].getLabel())){

            theWinner = squares2[0][0].getLabel();
            highlightWinner(0,0,1,1,2,2);

        }else if(!squares2[0][2].getLabel().equals("") &&

                squares2[0][2].getLabel().equals(squares2[1][1].getLabel()) &&
                squares2[0][2].getLabel().equals(squares2[2][0].getLabel())){

            theWinner = squares2[0][2].getLabel();
            highlightWinner(0,2,1,1,0,2);
        }
        return theWinner;
    }



    void computerMove(){

        int[] selectedSquare;

        selectedSquare = findEmptySquare("0");

        if(selectedSquare[0] == -1){

            selectedSquare = findEmptySquare("x");

        }

        if((selectedSquare[0] == -1)
                &&(squares2[1][1].getLabel().equals("")) ){

            selectedSquare = new int[]{1, 1};
        }

        if((selectedSquare[0] == -1)){

            selectedSquare = getRandomSquare();
        }

        squares2[selectedSquare[0]][selectedSquare[1]].setLabel("0");
    }

    int [] findEmptySquare(String player){

        int weight[] = new int[9];
        int weight2[][] = new int[3][3];

        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {

                if (squares2[i][j].getLabel().equals("0"))
                {
                    weight2[i][j] = -1;
                } else if (squares2[i][j].getLabel().equals("x"))
                {
                    weight2[i][j] = 1;
                } else
                    weight2[i][j] = 0;
            }
        }
        int twoWeights = player.endsWith("0") ? -2 : 2;

        if(weight2[0][0] + weight2[0][1] + weight2[0][2] == twoWeights){
            if(weight2[0][0] == 0)
                return new int[] {0,0};
            else if(weight2[0][1] == 0)
                return new int[] {0,1};
            else
                return new int[] {0,2};
        }

        if(weight2[1][0] + weight2[1][1] + weight2[1][2] == twoWeights){
            if(weight2[1][0] == 0)
                return new int[] {1,0};
            else if(weight2[1][1] == 0)
                return new int[] {1,1};
            else
                return new int[] {1,2};
        }

        if(weight2[2][0] + weight2[2][1] + weight2[2][2] == twoWeights){
            if(weight2[2][0] == 0)
                return new int[] {2,0};
            else if(weight2[2][1] == 0)
                return new int[] {2,1};
            else
                return new int[] {2,2};
        }

        if(weight2[0][0] + weight2[1][0] + weight2[2][0] == twoWeights){
            if(weight2[0][0] == 0)
                return new int[] {0,0};
            else if(weight2[1][0] == 0)
                return new int[] {1,0};
            else
                return new int[] {2,0};
        }

        if(weight2[0][1] + weight2[1][1] + weight2[2][1] == twoWeights){
            if(weight2[0][1] == 0)
                return new int[] {0,1};
            else if(weight2[1][1] == 0)
                return new int[] {1,1};
            else
                return new int[] {2,1};
        }

        if(weight2[0][2] + weight2[1][2] + weight2[2][2] == twoWeights){
            if(weight2[0][2] == 0)
                return new int[] {0,2};
            else if(weight2[1][2] == 0)
                return new int[] {1,2};
            else
                return new int[] {2,2};
        }

        if(weight2[0][0] + weight2[1][1] + weight2[2][2] == twoWeights){
            if(weight2[0][0] == 0)
                return new int[] {0,0};
            else if(weight2[1][1] == 0)
                return new int[] {1,1};
            else
                return new int[] {2,2};
        }

        if(weight2[0][2] + weight2[1][1] + weight2[2][0] == twoWeights){
            if(weight2[0][2] == 0)
                return new int[] {0,2};
            else if(weight2[1][1] == 0)
                return new int[] {1,1};
            else
                return new int[] {2,0};
        }
        return new int[]{-1};
    }

    int[] getRandomSquare(){

        boolean gotRandomSquare = false;

        int selectedSquarerow = -1;
        int selectedSquarecoll = -1;

        do{
            selectedSquarerow = (int) (Math.random() * 3);
            selectedSquarecoll = (int) (Math.random() * 3);

            if(squares2[selectedSquarerow][selectedSquarecoll].getLabel().equals("")){

                gotRandomSquare = true;
            }
        }while (!gotRandomSquare);

        return new int[]{selectedSquarerow, selectedSquarecoll};
    }



    void highlightWinner(int win1row,int win1coll,int win2row,int win2coll,int win3row,int win3coll){

        squares2[win1row][win1coll].setBackground(Color.CYAN);
        squares2[win2row][win2coll].setBackground(Color.CYAN);
        squares2[win3row][win3coll].setBackground(Color.CYAN);
    }

    void endTheGame()
    {
        newGameButton.setEnabled(true);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
            {

                squares2[i][j].setEnabled(false);
            }
        }
    }



}




