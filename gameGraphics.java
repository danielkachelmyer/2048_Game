

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
public class gameGraphics implements ActionListener{
	
	//Declare Class Data
	private int [] boardData;
	private final int SIZE;
	public ArrayList<JPanel> panels;
	public ArrayList<JLabel> labels;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JPanel scorePanel;
	private JButton leftButton;
	private JButton rightButton;
	private JButton upButton;
	private JButton downButton;
	private JLabel scoreLabel;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	
	
	
	gameGraphics(int [] board){
		moveLeft = true;
		moveRight = true;
		moveUp = true;
		moveDown = true;
		SIZE = board.length;
		panels = new ArrayList<JPanel>();
		labels = new ArrayList<JLabel>();
		boardData = Arrays.copyOf(board, board.length);

		//create main frame
		mainFrame = new JFrame("Game");
		mainFrame.setSize(500,500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create main panel
	JPanel	mainPanel = new JPanel(new GridBagLayout());
	
	//create number tiles
	int x = 0;
	int y = 0;
	for(int tile : board) {
		
		JPanel panel = new JPanel();
	
		GridBagConstraints constant = new GridBagConstraints();
		constant.insets = new Insets(10,10,10,10);
		constant.gridx = x + 10;
		constant.gridy = y;
		constant.fill = GridBagConstraints.NORTH;
		String value = Integer.toString(tile);
		JLabel nameLabel = new JLabel(value);
		labels.add(nameLabel);
		panel.add(nameLabel);
		mainPanel.add(panel, constant);
		
		panels.add(panel);
		x+= 10;
		if(x == 40) {
			x = 0;
			y +=10;
		}
	}
	//add buttons for moves
		buttonPanel = new JPanel(new GridBagLayout());
		//left
		leftButton = new JButton();		
		GridBagConstraints left = new GridBagConstraints();
		left.gridx = 0;
		left.gridy = 20;
		JLabel leftLabel = new JLabel("Move Left");
		leftButton.add(leftLabel);
		leftButton.addActionListener(this);
		buttonPanel.add(leftButton, left);
		
		//right
		rightButton = new JButton();		
		GridBagConstraints right = new GridBagConstraints();
		right.gridx = 30;
		right.gridy = 20;
		JLabel rightLabel = new JLabel("Move Right");
		rightButton.add(rightLabel);
		rightButton.addActionListener(this);
		buttonPanel.add(rightButton, right);
		
		//up
		upButton = new JButton();		
		GridBagConstraints up = new GridBagConstraints();
		up.gridx = 20;
		up.gridy = 0;
		JLabel upLabel = new JLabel("Move Up");
		upButton.add(upLabel);
		upButton.addActionListener(this);
		buttonPanel.add(upButton, up);
			
		//down
		downButton = new JButton();		
		GridBagConstraints down = new GridBagConstraints();
		down.gridx = 20;
		down.gridy = 30;
		JLabel downLabel = new JLabel("Move Down");
		downButton.add(downLabel);
		downButton.addActionListener(this);
		buttonPanel.add(downButton, down);
	
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.add(buttonPanel, BorderLayout.EAST);
		mainFrame.setVisible(true);
		
	//Change board
		
		
	//score
		scorePanel = new JPanel();
		String score = "Score is " + Integer.toString(game2048.getScore(board));
		 scoreLabel = new JLabel(score);
		scorePanel.add(scoreLabel);
		mainFrame.add(scorePanel, BorderLayout.NORTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//[index, value]
		
		if(e.getSource() == leftButton) {
			
			try {
				// [index, value]
				int increment = 0;//keep track of what square you are changing
			//	boardData = Arrays.copyOf(game2048.moveLeft(boardData), SIZE);//move left

				boardData = Arrays.copyOf(game2048.move(boardData, 4, "LEFT"), SIZE);

				boardData = Arrays.copyOf(game2048.Randomize(boardData), SIZE);//then randomize one new square
		
				
				for(JLabel label: labels) {
					String newLabel = Integer.toString(boardData[increment]);
					label.setText(newLabel);
					increment++;
				}
				scoreLabel.setText(Integer.toString(game2048.getScore(boardData)));
				moveLeft = true;
			}
			catch(Exception error){
			   JOptionPane.showMessageDialog(
		                null,
		                "Can't Move Left!",
		                "Message Box",
		                JOptionPane.INFORMATION_MESSAGE
		        );
			   moveLeft = false;
		}
		
		}
		
		if(e.getSource() == rightButton) {
			
			try {
			
				int increment = 0;
				//boardData = Arrays.copyOf(game2048.moveRight(boardData), SIZE);
				boardData = Arrays.copyOf(game2048.move(boardData, 4, "RIGHT"), SIZE);

				boardData = Arrays.copyOf(game2048.Randomize(boardData), SIZE);

				for(JLabel label: labels) {
					String newLabel = Integer.toString(boardData[increment]);
					label.setText(newLabel);
					increment++;
				}
				scoreLabel.setText(Integer.toString(game2048.getScore(boardData)));
				moveRight = true;
			}
			catch(Exception error){
				   JOptionPane.showMessageDialog(
			                null,
			                "Can't Move Right!",
			                "Message Box",
			                JOptionPane.INFORMATION_MESSAGE
			        );
				   moveRight = false;
			}
		}

		if(e.getSource() == upButton) {
			
			try {
			
				int increment = 0;
				//boardData = Arrays.copyOf(game2048.moveUp(boardData), SIZE);
				boardData = Arrays.copyOf(game2048.move(boardData, 4, "UP"), SIZE);

				boardData = Arrays.copyOf(game2048.Randomize(boardData), SIZE);
			 		
				for(JLabel label: labels) {
					String newLabel = Integer.toString(boardData[increment]);
					label.setText(newLabel);
					increment++;
				}
				scoreLabel.setText(Integer.toString(game2048.getScore(boardData)));
				moveUp = true;
			}
			catch(Exception error){
				   JOptionPane.showMessageDialog(
			                null,
			                "Can't Move Up!",
			                "Message Box",
			                JOptionPane.INFORMATION_MESSAGE
			        );
				   moveUp = false;
			}
		}

		if(e.getSource() == downButton) {
			
			try {
			
				int increment = 0;
				//boardData = Arrays.copyOf(game2048.moveDown(boardData), SIZE);
				boardData = Arrays.copyOf(game2048.move(boardData, 4, "DOWN"), SIZE);
				boardData = Arrays.copyOf(game2048.Randomize(boardData), SIZE);
			 		
				for(JLabel label: labels) {
					String newLabel = Integer.toString(boardData[increment]);
					label.setText(newLabel);
					increment++;
				}
				scoreLabel.setText(Integer.toString(game2048.getScore(boardData)));
				moveDown = true;
			}
			catch(Exception error){
				   JOptionPane.showMessageDialog(
			                null,
			                "Can't Move Down!",
			                "Message Box",
			                JOptionPane.INFORMATION_MESSAGE
			        );
				   moveDown = false;
			}
		}
		
		if(e.getSource() == leftButton || e.getSource() == rightButton ||e.getSource() == upButton || e.getSource() == downButton) {
			if(moveLeft == false && moveRight == false && moveUp == false && moveDown == false) {
				JOptionPane.showMessageDialog(
		                null,
		                "GAME OVER!",
		                "Message Box",
		                JOptionPane.INFORMATION_MESSAGE
		        );
			}
			if(game2048.checkGameWon(boardData)) {
				JOptionPane.showMessageDialog(
		                null,
		                "YOU WON!",
		                "Message Box",
		                JOptionPane.INFORMATION_MESSAGE
		        );
			}
		}
		
	}

}

