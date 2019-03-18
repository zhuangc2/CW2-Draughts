import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    public Board(){
        super("Draughts");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel panel = new JPanel();
        add(panel);
        GridLayout grid = new GridLayout(8,8);
        panel.setLayout(grid);

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
}

