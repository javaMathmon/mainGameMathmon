package main;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class mathmonChooseAPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
    private CustomPanel contentPane; // Custom JPanel for background drawing
	private Image lioraImage;
    private Image axionImage;
    public JButton btnBack;
    public JButton btnStart;
    public JLabel lblLiora= new JLabel("");
	
    public JLabel lblAxion= new JLabel("");
    private JLabel label;
    public static String selectedPlayer;
    public static JLabel selectedPlayerLabel = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mathmonChooseAPlayer frame = new mathmonChooseAPlayer();
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
	public mathmonChooseAPlayer() {
		setSize(1080,630);
		setResizable(false);
		setTitle("Choose a Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		 Image backgroundImage = null;
	        try {
	            backgroundImage = ImageIO.read(getClass().getResource("/picBackgrounds/picChoose.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Custom panel to draw background
	        contentPane = new CustomPanel(backgroundImage);
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
		btnBack = new JButton();
        btnStart = new JButton();

		btnStart.setEnabled(false);
		 try {
	            lioraImage = ImageIO.read(getClass().getResource("/picBackgrounds/picLiora.png"));
	            axionImage = ImageIO.read(getClass().getResource("/picBackgrounds/picAxion.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
        try {
            Image originalImage = ImageIO.read(getClass().getResource("/picBackgrounds/picBtnBack.png"));
            Image scaledImage = originalImage.getScaledInstance(
                    162, 40, Image.SCALE_SMOOTH // Adjust size as needed
                );
            btnBack.setIcon(new ImageIcon(scaledImage));
            btnBack.setHorizontalTextPosition(SwingConstants.CENTER); // Center the text over the icon
            btnBack.setVerticalTextPosition(SwingConstants.CENTER);
            btnBack.setContentAreaFilled(false); // Make the background transparent
            btnBack.setBorderPainted(false);    // Remove the border
            btnBack.setFocusPainted(false);     // Remove focus rectangle
        } catch (IOException e) {
            e.printStackTrace(); // Log the error if image loading fails
        }
        
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the About frame
                MainMenuFrame mainMenu = new MainMenuFrame();
                mainMenu.setVisible(true); // Show the main menu
            }
        });
	
        try {
            Image originalImage = ImageIO.read(getClass().getResource("/picBackgrounds/picBtnStart.png"));
            Image scaledImage = originalImage.getScaledInstance(
                    362, 150, Image.SCALE_SMOOTH // Adjust size as needed
                );
            btnStart.setIcon(new ImageIcon(scaledImage));
            btnStart.setHorizontalTextPosition(SwingConstants.CENTER); // Center the text over the icon
            btnStart.setVerticalTextPosition(SwingConstants.CENTER);
            btnStart.setContentAreaFilled(false); // Make the background transparent
            btnStart.setBorderPainted(false);    // Remove the border
            btnStart.setFocusPainted(false);     // Remove focus rectangle
        } catch (IOException e) {
            e.printStackTrace(); // Log the error if image loading fails
        }
		
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the About frame
                mathmonMain mainMenu = new mathmonMain();
                mainMenu.setVisible(true); // Show the main menu
            }
        });
        lblAxion = createPlayerLabel(axionImage); // Create label for Axion player
	    lblAxion.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            selectPlayer("Axion", lblAxion); // Handle selection for Axion
	        }
	    });
        
		lblLiora = createPlayerLabel(lioraImage);
		lblLiora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	selectPlayer("Liora", lblLiora);
            }
        });

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(240)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAxion, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(280, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addComponent(btnBack)
					.addPreferredGap(ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addGap(108))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(119)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAxion, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack)
						.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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
	private JLabel createPlayerLabel(Image image) {
        label = new JLabel();
        Image scaledImage = image.getScaledInstance(
                175, 300, Image.SCALE_SMOOTH // Adjust size as needed
            );
        label.setIcon(new ImageIcon(scaledImage));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setSize(50, 100);
        return label;
    }
	private void selectPlayer(String player, JLabel selectedLabel) {
        // If the same player is clicked again, do nothing
        if (selectedPlayerLabel == selectedLabel) {
            return;
        }

        // Deselect the previously selected player
        if (selectedPlayerLabel != null) {
            // Reset the previous player's appearance (e.g., removing border or changing image)
            selectedPlayerLabel.setBorder(null);  // Reset border (if you add borders)
        }

        // Select the new player
        selectedPlayerLabel = selectedLabel;
        selectedPlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLUE, 5));  // Add a border to show it's selected
        selectedPlayer = player;
        // Enable the "Start" button once a player is selected
        btnStart.setEnabled(true);
    }


}
