package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import battle.mathmonBattle;

public class mathmonOpening extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	 private CustomPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mathmonOpening frame = new mathmonOpening();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mathmonOpening() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080,630);
		setResizable(false);
		setTitle("Math Quest : Monster Battles");
		setLocationRelativeTo(null);
		Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/picBackgrounds/picOpening.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Custom panel to draw background
        contentPane = new CustomPanel(backgroundImage);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        this.addKeyListener(this);
	}
	
	private static class CustomPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private final Image backgroundImage;

        public CustomPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {			 
			dispose(); // Close the Main Menu
            EventQueue.invokeLater(() -> {
//                MainMenuFrame mainFrame = new MainMenuFrame();
                mathmonBattle mainFrame;
				try {
					mainFrame = new mathmonBattle(null);

	                mainFrame.setVisible(true);
	                mainFrame.toFront(); // Bring About Frame to front
	                mainFrame.requestFocus(); // Ensure About Frame receives focus
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            });
		}
	}
}
