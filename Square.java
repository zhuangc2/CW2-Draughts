import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton{
    private int squareX;
    private int squareY;
    private SquareType squareType;
    private CheckerType checkerType;
    private boolean Highlight = false;

    public Square(int x, int y){
        this(x, y, CheckerType.None);
    }

    public Square(int x, int y, CheckerType checkerType){
        super();
        squareX = x;
        squareY = y;
        this.checkerType = checkerType;
    }

    public int getSquareX(){
        return squareX;
    }
    public void setSquareX(int squareX){
        this.squareX = squareX;
    }
    public int getSquareY(){
        return squareY;
    }
    public void setSquareY(int squareY){
        this.squareY = squareY;
    }
    public SquareType getSquareType(){
        return squareType;
    }
    public void setSquareType(SquareType squareType){
        this.squareType = squareType;
    }
    public CheckerType getCheckerType() {
		return checkerType;
	}

	public void setCheckerType(CheckerType checkerType) {
		this.checkerType = checkerType;
    }

    public boolean isHighlight() {
		return Highlight;
	}

	public void setHighlight(boolean highlight) {
		Highlight = highlight;
	}

    public void moveTo(Square square){
        if (!canMoveTo(square)) return;

        square.setCheckerType(getCheckerType());
        setCheckerType(CheckerType.None);
        setIcon(new ImageIcon(Source.WhiteSquare));

        setToKing(square);

    	switch (square.getCheckerType()){
			case White: square.setIcon(new ImageIcon(Source.WhiteKing)); break;
			case Red: square.setIcon(new ImageIcon(Source.RedKing)); break;
			case WhiteKing: square.setIcon(new ImageIcon(Source.WhiteKing)); break;
			case RedKing: square.setIcon(new ImageIcon(Source.RedKing)); break;
			default: break;
		}
    }

    //decide whether the checker can move to valid square
    public boolean canMoveTo(Square square){
    	if (square.getCheckerType() != CheckerType.None) return false;
    	switch (getCheckerType()){
            //White checkers can only move to square where xAxis-1 and yAxis+1 or xAxis-1 and yAxis-1
			case White:
				if ( getSquareX() - 1 == square.getSquareX()) {
					if ((getSquareY() + 1 == square.getSquareY()) || (getSquareY() - 1 == square.getSquareY()))
						return true; }

				if ( getSquareX() - 2 == square.getSquareX()) {
					if ((getSquareY() + 2 == square.getSquareY()) || (getSquareY() - 2 == square.getSquareY()))
						return true; }

                return false;
                
            case Red:
                if ( getSquareX() + 1 == square.getSquareX()) {
                    if ((getSquareY() + 1 == square.getSquareY()) || (getSquareY() - 1 == square.getSquareY()))
                    return true; }

                if ( getSquareX() + 2 == square.getSquareX()) {
                    if ((getSquareY() + 2 == square.getSquareY()) || (getSquareY() - 2 == square.getSquareY()))
                    return true; }
                return false;

            case WhiteKing:
                if ( getSquareX() + 1 == square.getSquareX() || (getSquareX() - 1 == square.getSquareX())) {
                    if ((getSquareY() + 1 == square.getSquareY()) || (getSquareY() - 1 == square.getSquareY()))
                        return true; }

                if ( getSquareX() + 2 == square.getSquareX() || (getSquareX() - 2 == square.getSquareX())) {
                    if ((getSquareY() + 2 == square.getSquareY()) || (getSquareY() - 2 == square.getSquareY()))
                        return true; }
                return false;
            
            
            case RedKing:
            default: return false;
		}

    }

    private void setToKing(Square square){

    	switch (square.getCheckerType()){

    		case RedKing: return;
			case WhiteKing: return;

			case Red:
				if (square.getSquareX() == 7){
					square.setCheckerType(CheckerType.RedKing);
					System.out.println("Red King"); }
				break;

			case White:
				if (square.getSquareX() == 0){
					square.setCheckerType(CheckerType.WhiteKing);
					System.out.println("White King"); }
				break;

			default: break;
		}

    }

    
}