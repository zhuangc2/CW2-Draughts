import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    GridLayout grid = new GridLayout(8,8);
    private Square squareArr[][] = new Square[8][8];

    public Board(){
        //super("Draughts");
        createAndShowGUI();
        JFrame frame = new JFrame("Draughts");
        //frame.setContentpane(this);
        frame.setSize(640, 640);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void createAndShowGUI(){
        initialiseSquareArr();
        addSquareToPanel();
        setLayout(grid);
    }
    public void initialiseSquareArr(){
        for(int i = 0; i < 8; i++)						
	   {
		   for(int j = 0; j < 8; j++)					
		   {
               Square square = new Square(i,j);
               square.setVisible(true);
               square.setSquareX(i);
               square.setSquareY(j);
               squareArr[i][j] = square;
           }
        }   
    }
    public void addSquareToPanel(){

    }

    public static void main(String[] arguments){
        Board board = new Board();
    }
}

