import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener, MouseListener{
    GridLayout grid = new GridLayout(8,8);
    private Square squareArr[][] = new Square[8][8];

    private Square currClicked = null;
	private ArrayList<Square> ValideMoves = new ArrayList<>();

    public Board(){
        super();
        createAndShowGUI();

        JFrame frame = new JFrame("Draughts");
        frame.setContentPane(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 640);
		frame.setVisible(true);
    }
    public void createAndShowGUI(){
        initialiseSquareArr();
        addSquareToPanel();
        setLayout(grid);

        addWhiteChecker();
        addRedChecker();
        addMouseListener(this);
        
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

    public Dimension getPreferredSize() {
		return new Dimension(640, 640);
    }
    
    public void addWhiteChecker(){
        for (int i = 5; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (squareArr[i][j].getSquareType() == SquareType.White){
                    squareArr[i][j].setIcon(new ImageIcon(Source.WhiteChecker));
                    squareArr[i][j].setCheckerType(CheckerType.White);
                }
            }
        }
    }

    public void addRedChecker(){
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (squareArr[i][j].getSquareType() == SquareType.White){
                    squareArr[i][j].setIcon(new ImageIcon(Source.RedChecker));
                    squareArr[i][j].setCheckerType(CheckerType.Red);
                }
            }
        }
    }
    
    // private Square currClicked = null;
    // private ArrayList<Square> ValideMoves = new ArrayList<>();

    public void actionPerformed(ActionEvent actionEvent) {
		Object src = actionEvent.getSource();
		if ( !(src instanceof Square) ) return;

		if (currClicked == null){
            if (((Square) src).getCheckerType() == CheckerType.None) 
                return;
			currClicked = (Square) src;
			System.out.println("Current Clicked " + ((Square) src).getSquareX() + " " + ((Square) src).getSquareY());
            highlightSquare();
            return;
		}

		if (!ValideMoves.contains(src)) return;

		currClicked.moveTo(((Square) src));
		System.out.println("Move to " + ((Square) src).getSquareX() + " " + ((Square) src).getSquareY());
		currClicked = null;
        cancelHighlight();
        repaint();

    }

    private void highlightSquare(){
		for (int i = currClicked.getSquareX() - 2; i <= currClicked.getSquareX() + 2; i++){
			if (i < 0 || i > 7) continue;

			for (int j = currClicked.getSquareY() -2; j <= currClicked.getSquareY() + 2; j++){
				if (j < 0 || j > 7) continue;

				if (currClicked.canMoveTo(squareArr[i][j])){
                    squareArr[i][j].setIcon(new ImageIcon(Source.Selected));
					squareArr[i][j].setHighlight(true);
					ValideMoves.add(squareArr[i][j]);
				}
			}
		}
    }
    
    private void cancelHighlight(){

    	for (Square square : ValideMoves){
			if (square.isHighlight()){
				square.setHighlight(false);
                if (square.getCheckerType() == CheckerType.None)
                {
					switch (square.getSquareType()){
						case White: square.setIcon(new ImageIcon(Source.WhiteSquare)); break;
						case Black: square.setIcon(new ImageIcon(Source.BlackSquare)); break;
						default: break;
					}
                } 
                else 
                {
					switch (square.getCheckerType()){
						case White: square.setIcon(new ImageIcon(Source.WhiteChecker)); break;
						case Red: square.setIcon(new ImageIcon(Source.RedChecker)); break;
						case WhiteKing: square.setIcon(new ImageIcon(Source.WhiteKing)); break;
						case RedKing: square.setIcon(new ImageIcon(Source.RedKing)); break;
						default: break;
					}
				}
			}
		}

    	ValideMoves.clear();
	}

    private int checkCheckerType(CheckerType checkerType){
        switch (checkerType){
            case Red:
            case RedKing: return 1;
            case White:
            case WhiteKing: return 2;
            default: return 0;
        }
    }

	public void mousePressed(MouseEvent mouseEvent) {

	}

	public void mouseReleased(MouseEvent mouseEvent) {

	}

    public void mouseClicked(MouseEvent mouseEvent) {

	}

	public void mouseEntered(MouseEvent mouseEvent) {

	}

	public void mouseExited(MouseEvent mouseEvent) {

    }


    public static void main(String[] arguments){
        Board board = new Board();
    }
}

