import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener, MouseListener{
    //define variables
    public static final int HEIGHT = 640;
    public static final int WIDTH = 640;
    public static final int GRID_HEIGHT = 8;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_X = 8;
    public static final int GRID_Y = 8;
    public static final int FIRSTROW_WHITE = 5;
    public static final int FIRSTROW_RED = 0;
    public static final int LASTROW_WHITE = 8;
    public static final int LASTROW_RED = 3;
    public static final int FIRST_CLN = 0;
    public static final int LAST_CLN = 8;


    GridLayout grid = new GridLayout(GRID_HEIGHT, GRID_WIDTH);
    private Square squareArr[][] = new Square[GRID_X][GRID_Y];

    private Square currClicked = null;
	private ArrayList<Square> ValideMoves = new ArrayList<>();

    public Board(){
        super();
        createAndShowGUI();

        JFrame frame = new JFrame("Draughts");
        frame.setContentPane(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Board.HEIGHT, Board.WIDTH);
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
        for(int i = 0; i < Board.GRID_X; i++)						
	   {
		   for(int j = 0; j < Board.GRID_Y; j++)					
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
        for(int i = 0; i < Board.GRID_X; i++)						
	   {
		   for(int j = 0; j < Board.GRID_Y; j++)					
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
		return new Dimension(Board.HEIGHT, Board.WIDTH);
    }
    
    public void addWhiteChecker(){
        for (int i = Board.FIRSTROW_WHITE; i < Board.LASTROW_WHITE; i++)
        {
            for (int j = Board.FIRST_CLN; j < Board.FIRST_CLN; j++)
            {
                if (squareArr[i][j].getSquareType() == SquareType.White){
                    squareArr[i][j].setIcon(new ImageIcon(Source.WhiteChecker));
                    squareArr[i][j].setCheckerType(CheckerType.White);
                }
            }
        }
    }

    public void addRedChecker(){
        for (int i = Board.FIRSTROW_RED; i < Board.LASTROW_RED; i++)
        {
            for (int j = Board.FIRST_CLN; j < Board.LAST_CLN; j++)
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

