import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton{
    private int squareX;
    private int squareY;
    private SquareType squareType;
    private CheckerType checkerType;

    public Square(int x, int y){
        
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
}