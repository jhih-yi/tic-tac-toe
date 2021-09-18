package Tic_Tac_Toe;
//遊戲規則

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class gameProcess { 
	JButton[][] board = new JButton[3][3];
	char[][] place = new char[3][3];
	JLabel stateLabel;
	String mode = "two player mode";  //預設模式
	char player_turn = 'o';
	
	public gameProcess(JButton[][] board) {
		this.board = board;
		stateLabel = new JLabel();
		stateLabel.setText(mode);
		stateLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
		stateLabel.setForeground(Color.BLACK);
		stateLabel.setBounds(400, 280, 300, 30);
	}
	
	public void changeMode(String mode) {  //切換模式
		if(mode=="new game")
			clear();
		else {
			stateLabel.setText(mode);
			clear();
			this.mode = mode;
		}
			
	}
	
	public void check_step(String click) {
		String[] position = click.split("");
		if(place[Integer.valueOf(position[0])][Integer.valueOf(position[1])]!='\0') {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "repeat click");
		}
		else {
			go_step(Integer.valueOf(position[0]),Integer.valueOf(position[1]));
		}
			
	}
	
	public void go_step(int x,int y) {
		if(player_turn == 'o') {
			board[x][y].setText("o");
			place[x][y] = 'o';
			if(win(player_turn)) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, player_turn+" win! ");
				clear();
			}
			else if(isfull()) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "game over, no one player win! ");
				clear();
			}
			else {
				if(mode=="stupid computer mode")
					computerSelect();
				else if(mode=="ai computer mode"){
					aiSelect();
				}
				else
					player_turn = 'x';
			}

			
		}
		else {
			board[x][y].setText("x");
			place[x][y] = 'x';
			if(win(player_turn)) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, player_turn+" win! ");
				clear();
			}
			else if(isfull()) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "game over, no one player win! ");
				clear();
			}
			player_turn = 'o';
		}
	}
	
	public void computerSelect() {
		boolean select = false;
		player_turn = 'x';
		int x_position=0,y_position=0;
		Random rand = new Random();
		while (!select) {
			x_position = rand.nextInt(3);
			y_position = rand.nextInt(3);
			if(place[x_position][y_position]=='\0')
				select = true;
		}
		board[x_position][y_position].setText("x");
		place[x_position][y_position] = 'x';
		if(win(player_turn)) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, player_turn+" win! ");
			clear();
		}
		else if(isfull()) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "game over, no one player win! ");
			clear();
		}
		player_turn = 'o';
	}
	
	public void aiSelect() {
		player_turn = 'x';
		MinMax ai = new MinMax(place); 
		int[] ai_choose = ai.getBestMove();
		board[ai_choose[0]][ai_choose[1]].setText("x");
		place[ai_choose[0]][ai_choose[1]] = 'x';
		if(win(player_turn)) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, player_turn+" win! ");
			clear();
		}
		else if(isfull()) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "game over, no one player win! ");
			clear();
		}
		player_turn = 'o';
	}
	
	public boolean win(char player_turn) {
		boolean win_state = false;
		for(int i=0;i<3;i++) {
			if(place[i][0]==place[i][1] && place[i][1]==place[i][2] && place[i][2]==player_turn) {
				board[i][0].setForeground(Color.RED);
				board[i][1].setForeground(Color.RED);
				board[i][2].setForeground(Color.RED);
				win_state = true;
			}
			if(place[0][i]==place[1][i] && place[1][i]==place[2][i] && place[2][i]==player_turn){
				board[0][i].setForeground(Color.RED);
				board[1][i].setForeground(Color.RED);
				board[2][i].setForeground(Color.RED);
				win_state = true;
			}
		}
		if(place[0][0]==place[1][1] && place[1][1]==place[2][2] && place[2][2]==player_turn){
			board[0][0].setForeground(Color.RED);
			board[1][1].setForeground(Color.RED);
			board[2][2].setForeground(Color.RED);
			win_state = true;
		}
		if(place[0][2]==place[1][1] && place[1][1]==place[2][0] && place[2][0]==player_turn){
			board[0][2].setForeground(Color.RED);
			board[1][1].setForeground(Color.RED);
			board[2][0].setForeground(Color.RED);
			win_state = true;
		}
		
		return win_state;
	}
	
	public boolean isfull() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(place[i][j]=='\0')
					return false;
			}
		}
		return true;
	}
	
	public void clear() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j].setText("");
				board[i][j].setForeground(Color.BLUE);
				place[i][j] = '\0';
			}
		}
		player_turn = 'o';
	}
}
