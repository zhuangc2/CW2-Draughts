import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener{
    GridLayout grid = new GridLayout(8,8);
    private Square squareArr[][] = new Square[8][8];

    public Board(){
        super();
        createAndShowGUI();
        JFrame frame = new JFrame("Draughts");
        frame.setContentPane(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 640);
		frame.pack();
		frame.setVisible(true);
    }
    public void createAndShowGUI(){
        initialiseSquareArr();
        addSquareToPanel();
        setLayout(grid);
    }
    private void initialiseSquareArr(){
        for(int i = 0; i < 8; i++)						
	   {
		   for(int j = 0; j < 8; j++)					
		   {
            Square s = new Square(i,j);
            s.setVisible(true);
            s.setSquareX(i);
            s.setSquareY(j);
            s.addActionListener(this);
            squareArr[i][j] = s;
           }
        }   
    }
    private void addSquareToPanel(){
        for(int i = 0; i < 8; i++)						
	   {
		   for(int j = 0; j < 8; j++)					
		   {
               if (i%2 ==0)
               {
                   if (j%2 ==0)
                   {
                       squareArr[i][j].setIcon(new ImageIcon(Source.BlackSquare));
                       squareArr[i][j].setSquareType(SquareType.Black);
                   }
                   else
                   {
                        squareArr[i][j].setIcon(new ImageIcon(Source.WhiteSquare));
                        squareArr[i][j].setSquareType(SquareType.White);
                   }

               }
               else
               {
                   if (j%2 == 0)
                   {
                       squareArr[i][j].setIcon(new ImageIcon(Source.WhiteSquare));
                       squareArr[i][j].setSquareType(SquareType.White);

                   }
                   else
                   {
                        squareArr[i][j].setIcon(new ImageIcon(Source.BlackSquare));
                        squareArr[i][j].setSquareType(SquareType.Black);
                   }
               }
               add(squareArr[i][j]);
            }
        }

    }

    public void actionPerformed(ActionEvent actionEvent) {
		Object src = actionEvent.getSource();
        if ( !(src instanceof Square) ) return;
    }

    public Dimension getPreferredSize() {
		return new Dimension(640, 640);
	}


    public static void main(String[] arguments){
        Board board = new Board();
    }
}

