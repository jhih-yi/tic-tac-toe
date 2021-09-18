package Tic_Tac_Toe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class gui implements ActionListener{
	JFrame frame;
	JPanel panelLeft,panelRight;
	JButton[][] board = new JButton[3][3];
	JButton rePlay,twoPlayer,stupidComputer,aiComputer;
	
	gameProcess gameProcess = new gameProcess(board);
	
	public gui() {   //介面建立
		frame = new JFrame("TIC TAC TOE");
		frame.setSize(800,400);//width,height
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		
		panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(3, 3));
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j] = new JButton();
				board[i][j].setActionCommand(Integer.toString(i)+Integer.toString(j));
				board[i][j].addActionListener(this);
				board[i][j].setSize(100, 100);
				board[i][j].setFont(new Font("Times New Roman",Font.BOLD,50));
				board[i][j].setForeground(Color.BLUE);
				board[i][j].setBackground(Color.WHITE);
				panelLeft.add(board[i][j]);
			}
		}
		panelLeft.setBounds(50, 50, 300, 300);//x,y,width,height
		frame.getContentPane().add(panelLeft);
		
		panelRight = new JPanel(); 
		panelRight.setLayout(new GridLayout(4,1));
		rePlay = new JButton("new game");
		rePlay.setActionCommand("new game");
		rePlay.addActionListener(this);
		panelRight.add(rePlay);
		twoPlayer = new JButton("two player mode");
		twoPlayer.setActionCommand("two player mode");
		twoPlayer.addActionListener(this);
		panelRight.add(twoPlayer);
		stupidComputer = new JButton("stupid computer mode");
		stupidComputer.setActionCommand("stupid computer mode");
		stupidComputer.addActionListener(this);
		panelRight.add(stupidComputer);
		aiComputer = new JButton("ai computer mode");
		aiComputer.setActionCommand("ai computer mode");
		aiComputer.addActionListener(this);
		panelRight.add(aiComputer);
		panelRight.setBounds(400, 50, 300, 200);
		frame.getContentPane().add(panelRight);		
		
		frame.getContentPane().add(gameProcess.stateLabel);
		
	}
	
	public void show() {
		frame.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {  //事件監聽
		String click = e.getActionCommand();
		if(click=="new game" || click=="two player mode" || click=="stupid computer mode" || click=="ai computer mode") {
			gameProcess.changeMode(click);
		}
		else {
			gameProcess.check_step(click);
		}
		
	}
}
