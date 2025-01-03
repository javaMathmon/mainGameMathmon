package battle;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.mathmonGamePanel;
import entity.NPC_Monster1;
import entity.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class mathmonBattle extends JFrame {
	public static final int CANVAS_WIDTH = 400;
	public static final int CANVAS_HEIGHT = 140;
	//public int enemyHealth = 100;//temporary

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	mathmonGamePanel gp;
	Player player;
	Thread battleThread;
	NPC_Monster1 monster;
	
	public int battleChoice = 0, questionCount = 0, randomNum, attackDamage, receivedDamage, damageBoost, surgeUsed, healUsed, warpUsed, monIndex, addedTime = 0; //0 if wala pa, 1 if fight, 2 if power up, 3 if answering
	public String questions[] = new String[40], answers[] = new String[40], answerInput, statusText = "Pick your next move.";
	private JTextField txtAnswer;
	public double battleTime = 30;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//mathmonGamePanel gp = new mathmonGamePanel();
					//mathmonBattle frame = new mathmonBattle(gp);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mathmonBattle(mathmonGamePanel gp, int index, int monNum, Player player) throws IOException {
		this.gp = gp;
		this.player = player;
		monIndex = index;
		importQuestions();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 800, 630);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 450, 786, 150);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton btnAttack1 = new JButton("QUICK ATTACK");
		btnAttack1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAttack1.setBounds(10, 10, 240, 60);
		buttonPanel.add(btnAttack1);
		
		JButton btnAttack2 = new JButton("HEADBUTT");
		btnAttack2.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAttack2.setBounds(255, 10, 240, 60);
		buttonPanel.add(btnAttack2);
		
		JButton btnAttack3 = new JButton("MEGA PUNCH");
		btnAttack3.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAttack3.setBounds(10, 75, 240, 60);
		buttonPanel.add(btnAttack3);
		
		JButton btnAttack4 = new JButton("HYPER BEAM");
		btnAttack4.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAttack4.setBounds(255, 75, 240, 60);
		buttonPanel.add(btnAttack4);
		
		JButton btnFight = new JButton("FIGHT");
		btnFight.setFont(new Font("Arial", Font.PLAIN, 20));
		btnFight.setBounds(505, 10, 130, 125);
		buttonPanel.add(btnFight);
		
		JButton btnPowerUp = new JButton("POWER UP");
		btnPowerUp.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPowerUp.setBounds(645, 10, 130, 125);
		buttonPanel.add(btnPowerUp);
		
		JButton btnSurge = new JButton("\nSURGE");
		btnSurge.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSurge.setBounds(10, 10, 155, 125);
		buttonPanel.add(btnSurge);
		
		JButton btnTime = new JButton("TIME");
		btnTime.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTime.setBounds(340, 10, 155, 125);
		buttonPanel.add(btnTime);
		
		JButton btnHeal = new JButton("HEAL");
		btnHeal.setFont(new Font("Arial", Font.PLAIN, 20));
		btnHeal.setBounds(175, 10, 155, 125);
		buttonPanel.add(btnHeal);
		
		JLabel lblQuestion = new JLabel("QUESTION");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuestion.setBounds(10, 10, 485, 60);
		buttonPanel.add(lblQuestion);
		
		JLabel lblTimer = new JLabel("New label");
		lblTimer.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTimer.setBounds(10, 100, 100, 35);
		buttonPanel.add(lblTimer);
		
		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setBounds(185, 91, 145, 36);
		buttonPanel.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSubmit.setBounds(376, 100, 119, 35);
		buttonPanel.add(btnSubmit);
		
		JPanel battlePanel = new JPanel();
		battlePanel.setBounds(0, 0, 800, 450);
		contentPane.add(battlePanel);
		battlePanel.setLayout(null);
		
		JLabel lblPlayer = new JLabel("");
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setIcon(new ImageIcon("C:\\github\\mainGameMathmon\\mathmonGame\\res\\player\\girl_up_2.png"));
		lblPlayer.setBounds(150, 304, 387,  581);
		battlePanel.add(lblPlayer);

		JLabel lblEnemy = new JLabel("");
		lblEnemy.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemy.setIcon(new ImageIcon(gp.monster[index].image));
		lblEnemy.setBounds(474, 94, 251, 200);
		battlePanel.add(lblEnemy);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setVerticalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon("C:\\github\\mainGameMathmon\\mathmonGame\\res\\battle\\battleBG.png"));
		lblBackground.setBounds(0, 0, 800, 450);
		battlePanel.add(lblBackground);
		
		Thread battleThread = new Thread() {
	         @Override
	         public void run() {
	            while (true) {
	               update(btnAttack1, btnAttack2, btnAttack3, btnAttack4, btnSurge, btnHeal, btnTime, btnSubmit, lblQuestion, lblTimer, txtAnswer, randomNum);   // update the (x, y) position
	               repaint();  // Refresh the JFrame. Called back paintComponent()
	               try {
	                  // Delay and give other thread a chance to run
	                  Thread.sleep(1000/60);  // milliseconds
	               } catch (InterruptedException ignore) {}
	            }
	         }
	      };
		battleThread.start();
		
		btnAttack1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomNum = 0 + (int) (Math.random() * (((9) - 0) + 1));
				//answerInput = JOptionPane.showInputDialog(questions[randomNum]);
				battleTime = 30 + addedTime;
				battleChoice = 3;
			}
		});
		
		btnAttack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomNum = 10 + (int) (Math.random() * (((19) - 10) + 1));
				//answerInput = JOptionPane.showInputDialog(questions[randomNum]);
				battleTime = 30 + addedTime;
				battleChoice = 3;
			}
		});
		
		btnAttack3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomNum = 20 + (int) (Math.random() * (((29) - 20) + 1));
				//answerInput = JOptionPane.showInputDialog(questions[randomNum]);
				battleTime = 30 + addedTime;
				battleChoice = 3;
			}
		});
		
		btnAttack4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomNum = 30 + (int) (Math.random() * (((39) - 30) + 1));
				//answerInput = JOptionPane.showInputDialog(questions[randomNum]);
				battleTime = 30 + addedTime;
				battleChoice = 3;
			}
		});
		
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleChoice = 1;
				
			}
		});
		

		btnPowerUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battleChoice = 2;
			}
		});

		btnSurge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surgeUsed = 1;
				gp.player.powerSurge--;
				battleChoice = 0;
				statusText = "You used a power surge. Your next attack will deal extra damage";
			}
		});

		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warpUsed = 1;
				addedTime = 15+(gp.player.level)*5;
				gp.player.timeWarp--;
				battleChoice = 0;
				statusText = "You used a time warp. You will have more time for your next attack";
			}
		});

		btnHeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				healUsed = 1;
				gp.player.healingPotion--;
				gp.player.HP += (gp.player.level)*30;
				battleChoice = 0;
				statusText = "You used a time warp. You gained " + (gp.player.level)*30;
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attackInput(txtAnswer.getText(), randomNum);
//				try {
//					  Thread.sleep(3000);
//				} catch (InterruptedException e3) {
//					  Thread.currentThread().interrupt();
//				}
				enemyAttackInput(randomNum);
			}
		});
		
		txtAnswer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if(!Character.isDigit(input) && input != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
			}
		});
	}
	
	public void update(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5, JButton btn6, JButton btn7, JButton submit, JLabel question, JLabel timer, JTextField answer, int random) {
		if(battleChoice==0) {
	    	 btn1.setVisible(false);
	    	 btn2.setVisible(false);
	    	 btn3.setVisible(false);
	    	 btn4.setVisible(false);
	    	 btn5.setVisible(false);
	    	 btn6.setVisible(false);
	    	 btn7.setVisible(false);
	    	 submit.setVisible(false);
	    	 question.setVisible(true);
	    	 question.setText(statusText);
	    	 timer.setVisible(false);
	    	 txtAnswer.setVisible(false);
	    }else if(battleChoice==1) {
	    	 btn1.setVisible(true);
	    	 btn2.setVisible(true);
	    	 btn3.setVisible(true);
	    	 btn4.setVisible(true);
	    	 btn5.setVisible(false);
	    	 btn6.setVisible(false);
	    	 btn7.setVisible(false);
	    	 submit.setVisible(false);
	    	 question.setVisible(false);
	    	 question.setText(statusText);
	    	 timer.setVisible(false);
	    	 txtAnswer.setVisible(false);
	    }else if(battleChoice==2) {
	    	 btn1.setVisible(false);
	    	 btn2.setVisible(false);
	    	 btn3.setVisible(false);
	    	 btn4.setVisible(false);
	    	 btn5.setVisible(true);
	    	 btn6.setVisible(true);
	    	 btn7.setVisible(true);
	    	 submit.setVisible(false);
	    	 question.setVisible(false);
	    	 question.setText(statusText);
	    	 timer.setVisible(false);
	    	 txtAnswer.setVisible(false);
	    	 if(gp.player.powerSurge>0) {
	    		 btn5.setEnabled(true);
	    	 }else {
	    		 btn5.setEnabled(false);
	    	 }
	    	 if(gp.player.healingPotion>0) {
	    		 btn6.setEnabled(true);
	    	 }else {
	    		 btn6.setEnabled(false);
	    	 }
	    	 if(gp.player.timeWarp>0) {
	    		 btn7.setEnabled(true);
	    	 }else {
	    		 btn7.setEnabled(false);
	    	 }
	    }else if(battleChoice==3) {
	    	statusText = questions[random];
	    	 btn1.setVisible(false);
	    	 btn2.setVisible(false);
	    	 btn3.setVisible(false);
	    	 btn4.setVisible(false);
	    	 btn5.setVisible(false);
	    	 btn6.setVisible(false);
	    	 btn7.setVisible(false);
	    	 submit.setVisible(true);
	    	 question.setVisible(true);
	    	 question.setText(statusText);
	    	 timer.setVisible(true);
	    	 txtAnswer.setVisible(true);
	    	 
	    	 if(battleTime > 0) {
	    		 battleTime -= (double)1/60;
				Formatter format = new Formatter();
				format.format("%.2f", battleTime%60);
				String battleMin, battleSec;
				if(battleTime/60 < 10) {
					battleMin = "Time: 0" + (int)battleTime/60;
				}else {
					battleMin = "Time: " + (int)battleTime/60;
				}
				if(battleTime%60 < 10) {
					battleSec = ":0" + format;
				}else {
					battleSec = ":" + format;
				}
	    	 }else {
	    		 statusText = "You ran out of time to attack!";
	    		 attackDamage = 0;
	    		 enemyAttackInput(randomNum);
	    		 battleChoice = 0;
	    	 }
	    	 timer.setText("" + battleTime);
	    }
	}
	
	public void importQuestions()  throws IOException {
		BufferedReader input1 = new BufferedReader (new FileReader("C:\\github\\mainGameMathmon\\mathmonGame\\res\\battle\\questions.txt"));
		String question = input1.readLine();
		BufferedReader input2 = new BufferedReader (new FileReader("C:\\github\\mainGameMathmon\\mathmonGame\\res\\battle\\answers.txt"));
		String answer = input2.readLine();
		
		while(questionCount<40) {
			questions[questionCount] = question;	
			answers[questionCount] = answer;	
			question = input1.readLine();
			answer = input2.readLine();
			questionCount++;
		}
		input1.close();
		input2.close();
	}
	
	public void attackInput(String answer, int random) {
		if(answer.equals(answers[random])) {
			if(random >= 0 && random <= 9) {
				attackDamage = 20;
				//statusText = "You successfully used quick attack for "; 
			}else if(random >= 10 && random <= 19) {
				attackDamage = 30;
				//statusText = "You successfully used headbutt for "; 
			}else if(random >= 20 && random <= 29) {
				attackDamage = 40;
				//statusText = "You successfully used mega punch for "; 
			}else {
				attackDamage = 50;
				//statusText = "<html>You successfully used hyper beam for "; 
			}
			attackDamage += ((gp.player.level-1)*5);
			if(surgeUsed==1) {
				attackDamage += ((gp.player.level-1)*5);
				surgeUsed = 0;
			}
			statusText = "Dealt " + attackDamage + " damage! ";
		}else {
			attackDamage = 0;
			statusText = "You missed!" + answers[random] + "</html>"; 
		}
		//System.out.println("You dealt " + attackDamage + " damage!\n The enemy has " + enemyHealth + " remaining.");
		//enemyHealth -= attackDamage;
		//System.out.println("You dealt " + attackDamage + " damage!\nThe enemy has " + enemyHealth + " HP remaining.");
		gp.monster[monIndex].HP -= attackDamage;
		if(gp.monster[monIndex].HP<=0) {
			gp.monster[monIndex] = null;
			gp.gameState = gp.playState;
			gp.music.play();
			this.dispose();
			//close form, gamestate = playstate, play music uli.
		}
	}
	
	public void enemyAttackInput(int random) {
		if(attackDamage > 0) {
			if(random >= 0 && random <= 9) {
				if(gp.monster[monIndex].minDamage < 26) {
					receivedDamage = gp.monster[monIndex].minDamage + (int) (Math.random() * (((26) - gp.monster[monIndex].minDamage) + 1));
				}else {
					receivedDamage = 26 + (int) (Math.random() * (((gp.monster[monIndex].minDamage) - 26) + 1));
				}
			}else if(random >= 10 && random <= 19) {
				if(gp.monster[monIndex].minDamage < 35) {
					receivedDamage = gp.monster[monIndex].minDamage + (int) (Math.random() * (((35) - gp.monster[monIndex].minDamage) + 1));
				}else {
					receivedDamage = 35 + (int) (Math.random() * (((gp.monster[monIndex].minDamage) - 35) + 1));
				}
			}else if(random >= 20 && random <= 29) {
				if(gp.monster[monIndex].minDamage < 44) {
					receivedDamage = gp.monster[monIndex].minDamage + (int) (Math.random() * (((44) - gp.monster[monIndex].minDamage) + 1));
				}else {
					receivedDamage = 44 + (int) (Math.random() * (((gp.monster[monIndex].minDamage) - 44) + 1));
				}
			}else {
				if(gp.monster[monIndex].minDamage < 53) {
					receivedDamage = gp.monster[monIndex].minDamage + (int) (Math.random() * (((53) - gp.monster[monIndex].minDamage) + 1));
				}else {
					receivedDamage = 53 + (int) (Math.random() * (((gp.monster[monIndex].minDamage) - 53) + 1));
				}
			}
		}else {
			if(random >= 0 && random <= 9) {
				receivedDamage = 20;
			}else if(random >= 10 && random <= 19) {
				receivedDamage = 30;
			}else if(random >= 20 && random <= 29) {
				receivedDamage = 40;
			}else {
				receivedDamage = 50;
			}
		}
		player.HP -= receivedDamage;
		statusText += "Received " + receivedDamage + " damage!";
		addedTime = 0;
		battleChoice = 0;
		if(player.HP <= 0) {
			player.lives--;
			gp.gameState = gp.playState;
			gp.music.play();
			this.dispose();
			//or game over muna agad for now
		}
	}
	
//	public void answerMoment(JLabel question, int random) {
//		question.setText(questions[random]);
//		
//	}
}
