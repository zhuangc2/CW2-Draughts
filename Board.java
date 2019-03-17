import javax.swing.*;
import java.awt.*;

public class Board {
    
    private JFrame f = new JFrame();
    private JPanel panel = new JPanel();

    public Board(){
        //panel.add();
        f.setContentPane(panel);
        f.setTitle("Draughts");
        f.setSize(640, 640);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        GridLayout grid = new GridLayout(8,8);
        f.setLayout(grid);

        //create a black and white squares board
        for(int i=0; i<8; i++)						
	   {
		   for(int j=0; j<8; j++)					
		   {
			   JLabel l = new JLabel();
			   if((i+j)%2==0)
			   {
				   l.setBackground(Color.black);
				   l.setOpaque(true);
			   }
			   else
			   {
				   l.setBackground(Color.white);
				   l.setOpaque(true);
			   }
			   panel.add(l);
           }
        }   
    }
    public static void main(String[] arguments){
        Board frame = new Board();
    }
}

