import java.util.*;
import javax.swing.*;

public class Square extends JButton{
    private int squareX;
    private int squareY;
    private SquareType squareType;

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
}