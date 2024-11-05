package Hospital_Managment_System;

import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URI;

public class UserHomePage extends JFrame {

	private static final long serialVersionUID = 1L;

	class RoundedPanel extends JPanel {
		private Color backgroundColor;
		private int cornerRadius;

		public RoundedPanel(int radius, Color bgColor) {
			super();
			cornerRadius = radius;
			backgroundColor = bgColor;
			setOpaque(false); // Make the panel transparent
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setColor(backgroundColor);
			graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
		}
	}

	public class RoundedButton extends JButton {
		private int cornerRadius;
		private Color borderColor = new Color(0xF7EF8A);

		public RoundedButton(String label, int radius) {
			super(label);
			this.cornerRadius = radius;
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (getModel().isPressed()) {
				g2.setColor(getBackground().darker());
			} else if (getModel().isRollover()) {
				g2.setColor(getBackground().brighter());
			} else {
				g2.setColor(getBackground());
			}

			g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

			g2.setColor(borderColor);
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

			g2.dispose();

			super.paintComponent(g);
		}

		@Override
		protected void paintBorder(Graphics g) {

		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHomePage frame = new UserHomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String[] options = { "-Options-", "Diary", "Nuts", "Soy", "Shellfish", "Wheat" };
	// JScrollPane
	private JScrollPane scrollPane_1;

	// JTable
	private JTable Checkouttable;

	// JLabels
	private JLabel QtwLabel, PriceLabel, NameLabel, QtwLabel_1, PriceLabel_1, NameLabel_1, QtwLabel_2, PriceLabel_2,
			NameLabel_2, QtwLabel_3, PriceLabel_3, NameLabel_3, QtwLabel_4, PriceLabel_4, NameLabel_4, QtwLabel_5,
			PriceLabel_5, NameLabel_5, QtwLabel_6, PriceLabel_6, NameLabel_6, QtwLabel_7, PriceLabel_7, NameLabel_7,
			QtwLabel_8, PriceLabel_8, NameLabel_8, QtwLabel_9, PriceLabel_9, NameLabel_9, QtwLabel_10, PriceLabel_10,
			NameLabel_10, QtwLabel_11, PriceLabel_11, NameLabel_11, RefreshMenu, overallTotalLabel, PanelTimer,
			MenuWord, lmageBack, Menu, YOrder, Checkout, UserIcon, WelcomeUser, GrilledChickenImage, VegSoupImage,
			BakedSalmonImage, ChickenNoodleImage, QuinoaSaladImage, TurkeySandwichImage, MinestroneSoupdImage,
			BakedCodFishImage, VegetarianWrapImage, GrilledVegetablePlatterImage, ChickenCaesarSaladImage,
			TomatoBasilPaniniImage, circle1, circle2, timeLabel;

	// JPanels
	private JPanel MenuDish1, Namebar, PriceBar, QuantityBar, panel_1, panel_2, MenuDish2, Namebar_1, panel, PriceBar_1,
			panel_3, QuantityBar_1, MenuDish3, Namebar_2, panel_4, PriceBar_2, panel_5, QuantityBar_2, MenuDish4,
			Namebar_3, panel_6, PriceBar_3, panel_7, QuantityBar_3, MenuDish5, Namebar_4, panel_8, PriceBar_4, panel_9,
			QuantityBar_4, MenuDish6, Namebar_5, panel_10, PriceBar_5, panel_11, QuantityBar_5, MenuDish7, Namebar_6,
			panel_12, PriceBar_6, panel_13, QuantityBar_6, MenuDish8, Namebar_7, panel_14, PriceBar_7, panel_15,
			QuantityBar_7, MenuDish9, Namebar_8, panel_16, PriceBar_8, panel_17, QuantityBar_8, MenuDish10, Namebar_9,
			panel_18, PriceBar_9, panel_19, QuantityBar_9, MenuDish11, Namebar_10, panel_20, PriceBar_10, panel_21,
			QuantityBar_10, MenuDish12, Namebar_11, panel_22, PriceBar_11, panel_23, QuantityBar_11, TotalPriceAll,
			contentPane, PanelLeft, PanelRight;

	// JButtons
	private JButton btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6,
			btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10, btnNewButton_11;

	// JSpinners
	private JSpinner spinner_1, spinner_2, spinner_3, spinner_4, spinner_5, spinner_6, spinner_7, spinner_8, spinner_9,
			spinner_10, spinner_11;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	private String selectedAllergy = null;
	private JPanel TimeLabel;
	private JPanel panel_24;

	// public UserHomePage(String userEmail) {
	// this.userEmail = userEmail;
	// System.out.println("Email пользователя: " + userEmail); // Для отладки

	// }

	/**
	 * Create the frame.
	 */
	public UserHomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		RoundedButton MyProfile = new RoundedButton("My Profile", 50);
		MyProfile.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		MyProfile.setForeground(new Color(247, 239, 138));
		MyProfile.setBackground(new Color(204, 27, 27));
		MyProfile.setBounds(13, 220, 253, 60);
		// Signup.setBorder(roundedBorder);
		MyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfilePage enter = new UserProfilePage();
				dispose();

				enter.setVisible(true);

			}
		});

		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(50, 353, 179, 60);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != 0) { // Ignore "-Options-"
					selectedAllergy = comboBox.getSelectedItem().toString();
				} else {
					selectedAllergy = null; // No allergy selected or back to default
				}
			}
		});

		RoundedButton Allergies = new RoundedButton("Allergies", 50);
		Allergies.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Allergies.setForeground(new Color(247, 239, 138));
		Allergies.setBackground(new Color(204, 27, 27));
		Allergies.setBounds(13, 310, 253, 60);
		// Your code for RoundedButton creation

		// Add ActionListener to the Allergies button
		Allergies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Check if Desktop is supported by the platform
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						if (desktop.isSupported(Desktop.Action.BROWSE)) {
							// Construct a URI with the URL you want to open in the browser
							URI uri = new URI("https://en.wikipedia.org/wiki/Allergy");
							desktop.browse(uri);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		RoundedButton SwitchRole = new RoundedButton("Switch the role", 50);
		SwitchRole.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		SwitchRole.setForeground(new Color(247, 239, 138));
		SwitchRole.setBackground(new Color(204, 27, 27));
		SwitchRole.setBounds(13, 795, 253, 60);
		// Signup.setBorder(roundedBorder);
		SwitchRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchRolePage enter = new SwitchRolePage();
				dispose();

				enter.setVisible(true);

			}
		});

		RoundedButton Exit = new RoundedButton("Exit", 50);
		Exit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Exit.setForeground(new Color(247, 239, 138));
		Exit.setBackground(new Color(204, 27, 27));
		Exit.setBounds(13, 885, 253, 60);
		// Signup.setBorder(roundedBorder);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage enter = new LoginPage();
				dispose();

				enter.setVisible(true);

			}
		});

		RoundedButton Clear = new RoundedButton("Clear", 20);
		Clear.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Clear.setForeground(new Color(247, 239, 138));
		Clear.setBackground(new Color(204, 27, 27));
		Clear.setBounds(1136, 849, 165, 71);
		// Signup.setBorder(roundedBorder);
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();

				// Check if the table has at least one row
				if (model.getRowCount() > 0) {
					// Remove the last row
					model.removeRow(model.getRowCount() - 1);

					// Update the overall total
					updateOverallTotal();
				}
			}
		});
		RoundedButton Pay = new RoundedButton("Pay", 20);
		Pay.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Pay.setForeground(new Color(247, 239, 138));
		Pay.setBackground(new Color(52, 169, 81));
		Pay.setBounds(1325, 849, 165, 71);
		Pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		contentPane.setLayout(null);
		JPanel circlePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.setColor(Color.GRAY);
				// Draw a circle with width and height both set to 25
				g.fillOval(25, 25, 25, 25);
			}
		};
		circlePanel.setBounds(0, 0, 0, 0);

		// Add the circle panel to the frame
		getContentPane().add(circlePanel);

		// Make the frame visible
		setVisible(true);

		JLabel timerLabel = new JLabel("              Timer: 00:00");
		timerLabel.setBackground(new Color(13, 146, 118));
		timerLabel.setBounds(424, 29, 563, 65);
		timerLabel.setForeground(new Color(247, 239, 138)); // Color of text
		timerLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));

		WelcomeUser = new JLabel();
		WelcomeUser.setBounds(35, 157, 213, 30);
		WelcomeUser.setBorder(null);
		WelcomeUser.setForeground(new Color(247, 239, 138));
		WelcomeUser.setText("Welcome, User ...");
		WelcomeUser.setBackground(new Color(247, 239, 138));
		WelcomeUser.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 25));
		/*
		 * try {
		 * 
		 * Connection connect = database.connectDB(); String query =
		 * "SELECT firstname FROM user WHERE email = ?"; PreparedStatement prepare =
		 * connect.prepareStatement(query); prepare.setString(1, this.userEmail); //
		 * Replace with the actual user's email
		 * 
		 * ResultSet result = prepare.executeQuery(); if (result.next()) { String
		 * userName = result.getString("firstname");
		 * System.out.println("Username fetched: " + userName); // Debugging line
		 * WelcomeUser.setText("Welcome, User " + userName); } // Remember to close your
		 * resources result.close(); prepare.close(); } catch (Exception e) {
		 * e.printStackTrace(); JOptionPane.showMessageDialog(this,
		 * "An error occurred while fetching user data.", "Error",
		 * JOptionPane.ERROR_MESSAGE); }
		 */

		Menu = new JLabel();
		Menu.setBounds(640, 193, 151, 68);
		Menu.setBorder(null);
		Menu.setForeground(new Color(247, 239, 138));
		Menu.setText("Menu");
		Menu.setBackground(new Color(247, 239, 138));
		Menu.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 60));

		Checkout = new JLabel();
		Checkout.setBounds(1200, 139, 223, 45);
		Checkout.setBorder(null);
		Checkout.setForeground(new Color(247, 239, 138));
		Checkout.setText("--Checkout--");
		Checkout.setBackground(new Color(247, 239, 138));
		Checkout.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));

		YOrder = new JLabel();
		YOrder.setBounds(1229, 235, 176, 42);
		YOrder.setBorder(null);
		YOrder.setForeground(new Color(0, 0, 0));
		YOrder.setText("Your order");
		YOrder.setBackground(new Color(247, 239, 138));
		YOrder.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 35));

		PanelLeft = new JPanel();
		PanelLeft = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		PanelLeft.setBounds(0, 0, 280, 955);

		PanelRight = new JPanel();
		PanelRight = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		PanelRight.setBounds(1120, 111, 383, 843);

		// Initialize the DefaultTableModel without the "ID" column and data
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
				// Exclude the "ID" row from the data initialization if you don't plan to use it
				{ "Name", "Quantity", "Price", "Total Price" },

		}, new String[] { "Name", "Quantity", "Price", "Total Price" // Exclude "id" from the column names
		}) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Optional: Make table cells non-editable
			}
		};

		// Update the Checkouttable JTable to use this new model
		Checkouttable = new JTable(model);

		// Enable cell selection - consider if you want to keep this based on your UI
		// needs
		Checkouttable.setCellSelectionEnabled(true);
		Checkouttable.setColumnSelectionAllowed(true);

		// Set lines between rows and columns for better visibility
		Checkouttable.setShowHorizontalLines(true);
		Checkouttable.setShowVerticalLines(true);
		Checkouttable.setGridColor(Color.LIGHT_GRAY); // Set the color for the grid lines

		// Set the bounds for the table
		Checkouttable.setBounds(1148, 221, 330, 530);

		// Assuming 'contentPane' is your JPanel or container
		contentPane.add(Checkouttable);

		overallTotalLabel = new JLabel("Overall Total: £0.00");
		overallTotalLabel.setBounds(1148, 751, 330, 20);
		contentPane.add(overallTotalLabel);

		lmageBack = new JLabel("New label");
		lmageBack.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		UserIcon = new JLabel("New label");
		UserIcon.setIcon(new ImageIcon(
				UserHomePage.class.getResource("/testscene/ImagesHMS/7b93f6f63c57e7e568f98c79671af7f5_resized.png")));
		UserIcon.setBounds(77, 29, 125, 125);

		RefreshMenu = new JLabel("New label");
		RefreshMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sqlGetRandomFoodDetails;
				if (selectedAllergy == null || selectedAllergy.isEmpty()) {
					sqlGetRandomFoodDetails = "SELECT name, price, quantity FROM FoodCompleted ORDER BY RANDOM() LIMIT 12";
				} else {
					sqlGetRandomFoodDetails = "SELECT name, price, quantity FROM FoodCompleted WHERE NOT (',' || allergies || ',' LIKE ?) ORDER BY RANDOM() LIMIT 12";
				}

				try {
					Connection connect = database.connectDB();
					PreparedStatement prepareFoodDetails = connect.prepareStatement(sqlGetRandomFoodDetails);
					if (selectedAllergy != null && !selectedAllergy.isEmpty()) {
						prepareFoodDetails.setString(1, "%," + selectedAllergy + ",%");
					}
					ResultSet resultFoodDetails = prepareFoodDetails.executeQuery();

					int counter = 0;
					while (resultFoodDetails.next()) {
						String name = resultFoodDetails.getString("name");
						double price = resultFoodDetails.getDouble("price");
						int quantity = resultFoodDetails.getInt("quantity");

						// Update the labels manually based on counter
						switch (counter) {
						case 0:
							NameLabel.setText(name);
							PriceLabel.setText(String.format("£%.2f", price));
							QtwLabel.setText(String.valueOf(quantity));
							break;
						case 1:
							NameLabel_1.setText(name);
							PriceLabel_1.setText(String.format("£%.2f", price));
							QtwLabel_1.setText(String.valueOf(quantity));
							break;
						case 2:
							NameLabel_2.setText(name);
							PriceLabel_2.setText(String.format("£%.2f", price));
							QtwLabel_2.setText(String.valueOf(quantity));
							break;
						case 3:
							NameLabel_3.setText(name);
							PriceLabel_3.setText(String.format("£%.2f", price));
							QtwLabel_3.setText(String.valueOf(quantity));
							break;
						case 4:
							NameLabel_4.setText(name);
							PriceLabel_4.setText(String.format("£%.2f", price));
							QtwLabel_4.setText(String.valueOf(quantity));
							break;
						case 5:
							NameLabel_5.setText(name);
							PriceLabel_5.setText(String.format("£%.2f", price));
							QtwLabel_5.setText(String.valueOf(quantity));
							break;
						case 6:
							NameLabel_6.setText(name);
							PriceLabel_6.setText(String.format("£%.2f", price));
							QtwLabel_6.setText(String.valueOf(quantity));
							break;
						case 7:
							NameLabel_7.setText(name);
							PriceLabel_7.setText(String.format("£%.2f", price));
							QtwLabel_7.setText(String.valueOf(quantity));
							break;
						case 8:
							NameLabel_8.setText(name);
							PriceLabel_8.setText(String.format("£%.2f", price));
							QtwLabel_8.setText(String.valueOf(quantity));
							break;
						case 9:
							NameLabel_9.setText(name);
							PriceLabel_9.setText(String.format("£%.2f", price));
							QtwLabel_9.setText(String.valueOf(quantity));
							break;
						case 10:
							NameLabel_10.setText(name);
							PriceLabel_10.setText(String.format("£%.2f", price));
							QtwLabel_10.setText(String.valueOf(quantity));
							break;
						case 11:
							NameLabel_11.setText(name);
							PriceLabel_11.setText(String.format("£%.2f", price));
							QtwLabel_11.setText(String.valueOf(quantity));
							break;
						}

						counter++;
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		RefreshMenu
				.setIcon(new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/icons8-refresh-90.png")));
		RefreshMenu.setBounds(1380, 10, 90, 90);

		contentPane.add(circlePanel);

		TotalPriceAll = new JPanel();
		TotalPriceAll.setBackground(new Color(255, 255, 255));
		TotalPriceAll.setBounds(1148, 751, 330, 30);
		contentPane.add(TotalPriceAll);

		contentPane.add(comboBox);
		contentPane.add(MyProfile);
		contentPane.add(Allergies);
		contentPane.add(SwitchRole);
		contentPane.add(Exit);

		contentPane.add(Pay);
		contentPane.add(Clear);

		contentPane.add(Checkout);
		contentPane.add(UserIcon);
		contentPane.add(WelcomeUser);

		contentPane.add(timerLabel);

		PanelTimer = new JLabel("New label");
		PanelTimer.setIcon(new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/TimerPanel.png")));
		PanelTimer.setBounds(424, 29, 563, 65);
		contentPane.add(PanelTimer);

		JPanel CenterPanel = new JPanel();
		CenterPanel.setBounds(311, 160, 789, 756);
		CenterPanel.setLayout(null);

		// Add CenterPanel to contentPane
		contentPane.add(CenterPanel);

		// Your scrollable panel with content
		JPanel scrollablePanel = new JPanel();
		scrollablePanel.setBackground(new Color(13, 146, 118));
		scrollablePanel.setPreferredSize(new Dimension(800, 2000));

		// Set a preferred size larger than CenterPanel to ensure scrolling

		// Add your components to scrollablePanel here
		// Example: scrollablePanel.add(new JButton("Test Button"));

		// Initialize JScrollPane with your scrollable panel
		scrollPane_1 = new JScrollPane(scrollablePanel);
		scrollPane_1.setBounds(0, 0, 789, 756);
		scrollablePanel.setLayout(null);

		MenuDish1 = new JPanel();
		MenuDish1.setBackground(new Color(0, 0, 237));
		MenuDish1.setForeground(new Color(0, 0, 237));
		MenuDish1.setBounds(100, 145, 289, 274);
		scrollablePanel.add(MenuDish1);
		MenuDish1.setLayout(null);

		Namebar = new JPanel();
		Namebar.setBounds(0, 225, 289, 50);
		MenuDish1.add(Namebar);
		Namebar.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 245, 50);
		Namebar.add(panel_2);
		panel_2.setLayout(null);

		// Initializing the JSpinner with a SpinnerNumberModel
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

		spinner.setBounds(245, 6, 43, 40);
		Namebar.add(spinner);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBackground(new Color(52, 169, 81));
		btnNewButton.setBounds(70, 11, 140, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner.getValue(); // Assuming a spinner for MenuDish1
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish1, "You cannot add more than 10 items at once.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel.getText()); // Assuming QtwLabel for MenuDish1
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel.getText(); // Assuming NameLabel for MenuDish1
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel(); // Assuming
																								// Checkouttable is your
																								// JTable
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName != null && existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish1,
											"You cannot have more than 10 dishes of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {
									int secondsLeft = 180; // 3 minutes

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											// Restock logic
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel.getText()) + 100;
												QtwLabel.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});
											restockTimer.stop();
										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total }); // Add new item row
																										// to checkout
																										// table
							}

							spinner.setValue(1); // Reset spinner to 1
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish1,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				updateOverallTotal();
			}
		});

		panel_2.add(btnNewButton);

		PriceBar = new JPanel();
		PriceBar.setLayout(null);
		PriceBar.setBounds(0, 0, 289, 50);
		PriceBar.setBackground(new Color(238, 238, 238)); // Corrected line
		MenuDish1.add(PriceBar);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 55, 50);
		panel_1.setBackground(new Color(237, 237, 237)); // Corrected line
		PriceBar.add(panel_1);

		PriceLabel = new JLabel(" £6.5");
		PriceLabel.setBounds(0, 0, 55, 45);
		panel_1.add(PriceLabel);

		NameLabel = new JLabel("Grilled Chicken Salad");
		NameLabel.setBounds(100, 16, 165, 16);
		PriceBar.add(NameLabel);

		QuantityBar = new JPanel();
		QuantityBar.setBounds(249, 120, 40, 40);
		MenuDish1.add(QuantityBar);
		QuantityBar.setLayout(null);

		QtwLabel = new JLabel("25");
		QtwLabel.setBounds(12, 0, 45, 45);
		QuantityBar.add(QtwLabel);

		GrilledChickenImage = new JLabel("New label");
		GrilledChickenImage.setIcon(new ImageIcon(
				UserHomePage.class.getResource("/testscene/ImagesHMS/grilled_chicken_salad_resized.png")));
		GrilledChickenImage.setBounds(0, 50, 289, 176);
		MenuDish1.add(GrilledChickenImage);

		MenuDish2 = new JPanel();
		MenuDish2.setLayout(null);
		MenuDish2.setForeground(new Color(0, 0, 237));
		MenuDish2.setBackground(new Color(0, 0, 237));
		MenuDish2.setBounds(439, 145, 289, 274);
		scrollablePanel.add(MenuDish2);

		Namebar_1 = new JPanel();
		Namebar_1.setLayout(null);
		Namebar_1.setBounds(0, 225, 289, 50);
		MenuDish2.add(Namebar_1);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 245, 50);
		Namebar_1.add(panel);

		spinner_1 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_1.setBounds(245, 6, 43, 40);
		Namebar_1.add(spinner_1);

		btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(52, 169, 81));
		btnNewButton_1.setBounds(70, 11, 140, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_1.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish2, "You cannot add more than 10 items at once.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_1.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_1.getText();
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
						boolean exceedsLimit = false;

						// Check for quantity limits in the checkout table
						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName != null && existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish2,
											"You cannot have more than 10 dishes of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_1.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								// Implement the timer for restocking
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {
									int secondsLeft = 180; // 3 minutes

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											// Restock logic
											SwingUtilities.invokeLater(new Runnable() {
												@Override
												public void run() {
													int restockedQuantity = Integer.parseInt(QtwLabel_1.getText())
															+ 100;
													QtwLabel_1.setText(String.valueOf(restockedQuantity));
													timerLabel.setText("              Timer: 00:00"); // Reset timer
																										// display
													restockTimer.stop();
												}
											});
											restockTimer.stop();
										}
									}
								});
								restockTimer.start();
							}

							// Update or add the item to the checkout table
							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_1.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_1.setValue(1); // Reset the spinner to 1
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish2,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel.add(btnNewButton_1);

		PriceBar_1 = new JPanel();
		PriceBar_1.setLayout(null);
		PriceBar_1.setBounds(0, 0, 289, 50);
		MenuDish2.add(PriceBar_1);

		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 0, 55, 50);
		PriceBar_1.add(panel_3);

		PriceLabel_1 = new JLabel(" £4.5");
		PriceLabel_1.setBounds(0, 0, 55, 45);
		panel_3.add(PriceLabel_1);

		NameLabel_1 = new JLabel("Vegetable Soup");
		NameLabel_1.setBounds(100, 16, 165, 16);
		PriceBar_1.add(NameLabel_1);

		QuantityBar_1 = new JPanel();
		QuantityBar_1.setLayout(null);
		QuantityBar_1.setBounds(249, 120, 40, 40);
		MenuDish2.add(QuantityBar_1);

		QtwLabel_1 = new JLabel("73");
		QtwLabel_1.setBounds(12, 0, 45, 45);
		QuantityBar_1.add(QtwLabel_1);

		VegSoupImage = new JLabel("New label");
		VegSoupImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/vegetable_soup_resized.png")));
		VegSoupImage.setBounds(0, 50, 289, 176);
		MenuDish2.add(VegSoupImage);

		MenuDish3 = new JPanel();
		MenuDish3.setLayout(null);
		MenuDish3.setForeground(new Color(0, 0, 237));
		MenuDish3.setBackground(new Color(0, 0, 237));
		MenuDish3.setBounds(100, 450, 289, 274);
		scrollablePanel.add(MenuDish3);

		Namebar_2 = new JPanel();
		Namebar_2.setLayout(null);
		Namebar_2.setBounds(0, 225, 289, 50);
		MenuDish3.add(Namebar_2);

		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(0, 0, 245, 50);
		Namebar_2.add(panel_4);

		spinner_2 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_2.setBounds(245, 6, 43, 40);
		Namebar_2.add(spinner_2);

		btnNewButton_2 = new JButton("Add");
		btnNewButton_2.setBounds(70, 11, 140, 29);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_2.getValue(); // Get the selected spinner value
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish3, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_2.getText()); // Parse current quantity from
																					// QtwLabel_2
					if (currentQuantity >= spinnerValue) { // Check if enough quantity is available
						String foodName = NameLabel_2.getText(); // Get the food name from NameLabel_2
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel(); // Assuming
																								// Checkouttable is your
																								// JTable
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName != null && existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish3,
											"You cannot have more than 10 dishes of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_2.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {
									int secondsLeft = 180; // 3 minutes

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_2.getText()) + 100;
												QtwLabel_2.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_2.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total }); // Add new item row
																										// to checkout
																										// table
							}

							spinner_2.setValue(1); // Reset spinner_2 to 1
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish3,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_4.add(btnNewButton_2);

		PriceBar_2 = new JPanel();
		PriceBar_2.setLayout(null);
		PriceBar_2.setBounds(0, 0, 289, 50);
		MenuDish3.add(PriceBar_2);

		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(0, 0, 55, 50);
		PriceBar_2.add(panel_5);

		PriceLabel_2 = new JLabel(" £18.5");
		PriceLabel_2.setBounds(0, 0, 55, 45);
		panel_5.add(PriceLabel_2);

		NameLabel_2 = new JLabel("Baked Salmon");
		NameLabel_2.setBounds(100, 16, 165, 16);
		PriceBar_2.add(NameLabel_2);

		QuantityBar_2 = new JPanel();
		QuantityBar_2.setLayout(null);
		QuantityBar_2.setBounds(249, 120, 40, 40);
		MenuDish3.add(QuantityBar_2);

		QtwLabel_2 = new JLabel("5");
		QtwLabel_2.setBounds(12, 0, 45, 45);
		QuantityBar_2.add(QtwLabel_2);

		BakedSalmonImage = new JLabel("New label");
		BakedSalmonImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/baked_salmon_resized.png")));
		BakedSalmonImage.setBounds(0, 50, 289, 176);
		MenuDish3.add(BakedSalmonImage);

		MenuDish4 = new JPanel();
		MenuDish4.setLayout(null);
		MenuDish4.setForeground(new Color(0, 0, 237));
		MenuDish4.setBackground(new Color(0, 0, 237));
		MenuDish4.setBounds(439, 450, 289, 274);
		scrollablePanel.add(MenuDish4);

		Namebar_3 = new JPanel();
		Namebar_3.setLayout(null);
		Namebar_3.setBounds(0, 225, 289, 50);
		MenuDish4.add(Namebar_3);

		panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBounds(0, 0, 245, 50);
		Namebar_3.add(panel_6);

		spinner_3 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_3.setBounds(245, 6, 43, 40);
		Namebar_3.add(spinner_3);

		btnNewButton_3 = new JButton("Add");
		btnNewButton_3.setBounds(70, 11, 140, 29);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_3.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish4, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_3.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_3.getText();
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName != null && existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish4,
											"You cannot have more than 10 dishes of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_3.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_3.getText()) + 100;
												QtwLabel_3.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_3.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_3.setValue(1);
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish4,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_6.add(btnNewButton_3);

		PriceBar_3 = new JPanel();
		PriceBar_3.setLayout(null);
		PriceBar_3.setBounds(0, 0, 289, 50);
		MenuDish4.add(PriceBar_3);

		panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(0, 0, 55, 50);
		PriceBar_3.add(panel_7);

		PriceLabel_3 = new JLabel(" £18.0");
		PriceLabel_3.setBounds(0, 0, 55, 45);
		panel_7.add(PriceLabel_3);

		NameLabel_3 = new JLabel("Chicken Noodle Casserole");
		NameLabel_3.setBounds(100, 16, 165, 16);
		PriceBar_3.add(NameLabel_3);

		QuantityBar_3 = new JPanel();
		QuantityBar_3.setLayout(null);
		QuantityBar_3.setBounds(249, 120, 40, 40);
		MenuDish4.add(QuantityBar_3);

		QtwLabel_3 = new JLabel("99");
		QtwLabel_3.setBounds(12, 0, 45, 45);
		QuantityBar_3.add(QtwLabel_3);

		ChickenNoodleImage = new JLabel("New label");
		ChickenNoodleImage.setIcon(new ImageIcon(
				UserHomePage.class.getResource("/testscene/ImagesHMS/chicken_noodle_casserole_resized.png")));
		ChickenNoodleImage.setBounds(0, 50, 289, 176);
		MenuDish4.add(ChickenNoodleImage);

		MenuDish5 = new JPanel();
		MenuDish5.setLayout(null);
		MenuDish5.setForeground(new Color(0, 0, 237));
		MenuDish5.setBackground(new Color(0, 0, 237));
		MenuDish5.setBounds(100, 755, 289, 274);
		scrollablePanel.add(MenuDish5);

		Namebar_4 = new JPanel();
		Namebar_4.setLayout(null);
		Namebar_4.setBounds(0, 225, 289, 50);
		MenuDish5.add(Namebar_4);

		panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBounds(0, 0, 245, 50);
		Namebar_4.add(panel_8);

		btnNewButton_4 = new JButton("Add");
		btnNewButton_4.setBounds(70, 11, 140, 29);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_4.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish5, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_4.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_4.getText();
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel(); // Assuming
																								// Checkouttable is
																								// initialized elsewhere
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish5,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_4.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_4.getText()) + 100;
												QtwLabel_4.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_4.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total }); // Add new item row
																										// to checkout
																										// table
							}

							spinner_4.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish5,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_8.add(btnNewButton_4);

		spinner_4 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_4.setBounds(245, 6, 43, 40);
		Namebar_4.add(spinner_4);

		PriceBar_4 = new JPanel();
		PriceBar_4.setLayout(null);
		PriceBar_4.setBounds(0, 0, 289, 50);
		MenuDish5.add(PriceBar_4);

		panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBounds(0, 0, 55, 50);
		PriceBar_4.add(panel_9);

		PriceLabel_4 = new JLabel(" £17.0");
		PriceLabel_4.setBounds(0, 0, 55, 45);
		panel_9.add(PriceLabel_4);

		NameLabel_4 = new JLabel("Quinoa Salad");
		NameLabel_4.setBounds(100, 16, 150, 16);
		PriceBar_4.add(NameLabel_4);

		QuantityBar_4 = new JPanel();
		QuantityBar_4.setLayout(null);
		QuantityBar_4.setBounds(249, 120, 40, 40);
		MenuDish5.add(QuantityBar_4);

		QtwLabel_4 = new JLabel("99");
		QtwLabel_4.setBounds(12, 0, 45, 45);
		QuantityBar_4.add(QtwLabel_4);

		QuinoaSaladImage = new JLabel("New label");
		QuinoaSaladImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/quinoa_salad_resized.png")));
		QuinoaSaladImage.setBounds(0, 50, 289, 176);
		MenuDish5.add(QuinoaSaladImage);

		MenuDish6 = new JPanel();
		MenuDish6.setLayout(null);
		MenuDish6.setForeground(new Color(0, 0, 237));
		MenuDish6.setBackground(new Color(0, 0, 237));
		MenuDish6.setBounds(439, 755, 289, 274);
		scrollablePanel.add(MenuDish6);

		Namebar_5 = new JPanel();
		Namebar_5.setLayout(null);
		Namebar_5.setBounds(0, 225, 289, 50);
		MenuDish6.add(Namebar_5);

		panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBounds(0, 0, 245, 50);
		Namebar_5.add(panel_10);

		btnNewButton_5 = new JButton("Add");
		btnNewButton_5.setBounds(70, 11, 140, 29);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_5.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish6, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_5.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_5.getText();
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel(); // Assuming
																								// Checkouttable is
																								// already defined
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish6,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_5.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_5.getText()) + 100;
												QtwLabel_5.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_5.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total }); // Add new item row
																										// to checkout
																										// table
							}

							spinner_5.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish6,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_10.add(btnNewButton_5);

		spinner_5 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_5.setBounds(245, 6, 43, 40);
		Namebar_5.add(spinner_5);

		PriceBar_5 = new JPanel();
		PriceBar_5.setLayout(null);
		PriceBar_5.setBounds(0, 0, 289, 50);
		MenuDish6.add(PriceBar_5);

		panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBounds(0, 0, 55, 50);
		PriceBar_5.add(panel_11);

		PriceLabel_5 = new JLabel(" £15.5");
		PriceLabel_5.setBounds(0, 0, 55, 45);
		panel_11.add(PriceLabel_5);

		NameLabel_5 = new JLabel("Turkey Sandwich");
		NameLabel_5.setBounds(100, 16, 150, 16);
		PriceBar_5.add(NameLabel_5);

		QuantityBar_5 = new JPanel();
		QuantityBar_5.setLayout(null);
		QuantityBar_5.setBounds(249, 120, 40, 40);
		MenuDish6.add(QuantityBar_5);

		QtwLabel_5 = new JLabel("99");
		QtwLabel_5.setBounds(12, 0, 45, 45);
		QuantityBar_5.add(QtwLabel_5);

		TurkeySandwichImage = new JLabel("New label");
		TurkeySandwichImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/turkey_sandwich_resized.png")));
		TurkeySandwichImage.setBounds(0, 50, 289, 176);
		MenuDish6.add(TurkeySandwichImage);

		MenuDish7 = new JPanel();
		MenuDish7.setLayout(null);
		MenuDish7.setForeground(new Color(0, 0, 237));
		MenuDish7.setBackground(new Color(0, 0, 237));
		MenuDish7.setBounds(100, 1060, 289, 274);
		scrollablePanel.add(MenuDish7);

		Namebar_6 = new JPanel();
		Namebar_6.setLayout(null);
		Namebar_6.setBounds(0, 225, 289, 50);
		MenuDish7.add(Namebar_6);

		panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBounds(0, 0, 245, 50);
		Namebar_6.add(panel_12);

		btnNewButton_6 = new JButton("Add");
		btnNewButton_6.setBounds(70, 11, 140, 29);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_6.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish7, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_6.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_6.getText();
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish7,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_6.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_6.getText()) + 100;
												QtwLabel_6.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_6.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_6.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish7,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_12.add(btnNewButton_6);

		spinner_6 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_6.setBounds(245, 6, 43, 40);
		Namebar_6.add(spinner_6);

		PriceBar_6 = new JPanel();
		PriceBar_6.setLayout(null);
		PriceBar_6.setBounds(0, 0, 289, 50);
		MenuDish7.add(PriceBar_6);

		panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBounds(0, 0, 55, 50);
		PriceBar_6.add(panel_13);

		PriceLabel_6 = new JLabel(" £9.0");
		PriceLabel_6.setBounds(0, 0, 55, 45);
		panel_13.add(PriceLabel_6);

		NameLabel_6 = new JLabel("Minestrone Soup");
		NameLabel_6.setBounds(100, 16, 150, 16);
		PriceBar_6.add(NameLabel_6);

		QuantityBar_6 = new JPanel();
		QuantityBar_6.setLayout(null);
		QuantityBar_6.setBounds(249, 120, 40, 40);
		MenuDish7.add(QuantityBar_6);

		QtwLabel_6 = new JLabel("99");
		QtwLabel_6.setBounds(12, 0, 45, 45);
		QuantityBar_6.add(QtwLabel_6);

		MinestroneSoupdImage = new JLabel("New label");
		MinestroneSoupdImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/minestrone_soup_resized.png")));
		MinestroneSoupdImage.setBounds(0, 50, 289, 176);
		MenuDish7.add(MinestroneSoupdImage);

		MenuDish8 = new JPanel();
		MenuDish8.setLayout(null);
		MenuDish8.setForeground(new Color(0, 0, 237));
		MenuDish8.setBackground(new Color(0, 0, 237));
		MenuDish8.setBounds(439, 1060, 289, 274);
		scrollablePanel.add(MenuDish8);

		Namebar_7 = new JPanel();
		Namebar_7.setLayout(null);
		Namebar_7.setBounds(0, 225, 289, 50);
		MenuDish8.add(Namebar_7);

		panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBounds(0, 0, 245, 50);
		Namebar_7.add(panel_14);

		btnNewButton_7 = new JButton("Add");
		btnNewButton_7.setBounds(70, 11, 140, 29);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_7.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish8, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_7.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_7.getText(); // Update "New label" to actual dish name
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish8,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_7.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_7.getText()) + 100;
												QtwLabel_7.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_7.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_7.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish8,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_14.add(btnNewButton_7);

		spinner_7 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_7.setBounds(245, 6, 43, 40);
		Namebar_7.add(spinner_7);

		PriceBar_7 = new JPanel();
		PriceBar_7.setLayout(null);
		PriceBar_7.setBounds(0, 0, 289, 50);
		MenuDish8.add(PriceBar_7);

		panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBounds(0, 0, 55, 50);
		PriceBar_7.add(panel_15);

		PriceLabel_7 = new JLabel(" £16.5");
		PriceLabel_7.setBounds(0, 0, 55, 45);
		panel_15.add(PriceLabel_7);

		NameLabel_7 = new JLabel("Baked Cod Fish");
		NameLabel_7.setBounds(100, 16, 150, 16);
		PriceBar_7.add(NameLabel_7);

		QuantityBar_7 = new JPanel();
		QuantityBar_7.setLayout(null);
		QuantityBar_7.setBounds(249, 120, 40, 40);
		MenuDish8.add(QuantityBar_7);

		QtwLabel_7 = new JLabel("99");
		QtwLabel_7.setBounds(12, 0, 45, 45);
		QuantityBar_7.add(QtwLabel_7);

		BakedCodFishImage = new JLabel("New label");
		BakedCodFishImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/Baked_Cod_Fish_resized.png")));
		BakedCodFishImage.setBounds(0, 50, 289, 176);
		MenuDish8.add(BakedCodFishImage);

		MenuDish9 = new JPanel();
		MenuDish9.setLayout(null);
		MenuDish9.setForeground(new Color(0, 0, 237));
		MenuDish9.setBackground(new Color(0, 0, 237));
		MenuDish9.setBounds(100, 1365, 289, 274);
		scrollablePanel.add(MenuDish9);

		Namebar_8 = new JPanel();
		Namebar_8.setLayout(null);
		Namebar_8.setBounds(0, 225, 289, 50);
		MenuDish9.add(Namebar_8);

		panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBounds(0, 0, 245, 50);
		Namebar_8.add(panel_16);

		btnNewButton_8 = new JButton("Add");
		btnNewButton_8.setBounds(70, 11, 140, 29);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_8.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish9, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_8.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_8.getText(); // Ensure this is updated from "New label" to the
																	// actual dish name
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
						boolean exceedsLimit = false;

						// Check if adding the item exceeds the limit
						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish9,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_8.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_8.getText()) + 100;
												QtwLabel_8.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							// Update or add the item to the checkout table
							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_8.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_8.setValue(1); // Reset the spinner after the operation
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish9,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_16.add(btnNewButton_8);

		spinner_8 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_8.setBounds(245, 6, 43, 40);
		Namebar_8.add(spinner_8);

		PriceBar_8 = new JPanel();
		PriceBar_8.setLayout(null);
		PriceBar_8.setBounds(0, 0, 289, 50);
		MenuDish9.add(PriceBar_8);

		panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBounds(0, 0, 55, 50);
		PriceBar_8.add(panel_17);

		PriceLabel_8 = new JLabel(" £8.0");
		PriceLabel_8.setBounds(0, 0, 55, 45);
		panel_17.add(PriceLabel_8);

		NameLabel_8 = new JLabel("Vegetarian Wrap");
		NameLabel_8.setBounds(100, 16, 150, 16);
		PriceBar_8.add(NameLabel_8);

		QuantityBar_8 = new JPanel();
		QuantityBar_8.setLayout(null);
		QuantityBar_8.setBounds(249, 120, 40, 40);
		MenuDish9.add(QuantityBar_8);

		QtwLabel_8 = new JLabel("99");
		QtwLabel_8.setBounds(12, 0, 45, 45);
		QuantityBar_8.add(QtwLabel_8);

		VegetarianWrapImage = new JLabel("New label");
		VegetarianWrapImage.setIcon(new ImageIcon(
				UserHomePage.class.getResource("/testscene/ImagesHMS/vegetarian_wrap_realistic_resized.png")));
		VegetarianWrapImage.setBounds(0, 50, 289, 176);
		MenuDish9.add(VegetarianWrapImage);

		MenuDish10 = new JPanel();
		MenuDish10.setLayout(null);
		MenuDish10.setForeground(new Color(0, 0, 237));
		MenuDish10.setBackground(new Color(0, 0, 237));
		MenuDish10.setBounds(439, 1365, 289, 274);
		scrollablePanel.add(MenuDish10);

		Namebar_9 = new JPanel();
		Namebar_9.setLayout(null);
		Namebar_9.setBounds(0, 225, 289, 50);
		MenuDish10.add(Namebar_9);

		panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBounds(0, 0, 245, 50);
		Namebar_9.add(panel_18);

		btnNewButton_9 = new JButton("Add");
		btnNewButton_9.setBounds(70, 11, 140, 29);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_9.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish10,
							"Not enough items available. Please, choose available quantity and wait 3 minutes", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_9.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_9.getText(); // Update "New label" to the actual dish name
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish10,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_9.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_9.getText()) + 100;
												QtwLabel_9.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_9.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_9.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish10,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_18.add(btnNewButton_9);

		spinner_9 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_9.setBounds(245, 6, 43, 40);
		Namebar_9.add(spinner_9);

		PriceBar_9 = new JPanel();
		PriceBar_9.setLayout(null);
		PriceBar_9.setBounds(0, 0, 289, 50);
		MenuDish10.add(PriceBar_9);

		panel_19 = new JPanel();
		panel_19.setLayout(null);
		panel_19.setBounds(0, 0, 55, 50);
		PriceBar_9.add(panel_19);

		PriceLabel_9 = new JLabel(" £18.0");
		PriceLabel_9.setBounds(0, 0, 55, 45);
		panel_19.add(PriceLabel_9);

		NameLabel_9 = new JLabel("Grilled Vegetable Platter");
		NameLabel_9.setBounds(100, 16, 150, 16);
		PriceBar_9.add(NameLabel_9);

		QuantityBar_9 = new JPanel();
		QuantityBar_9.setLayout(null);
		QuantityBar_9.setBounds(249, 120, 40, 40);
		MenuDish10.add(QuantityBar_9);

		QtwLabel_9 = new JLabel("99");
		QtwLabel_9.setBounds(12, 0, 45, 45);
		QuantityBar_9.add(QtwLabel_9);

		GrilledVegetablePlatterImage = new JLabel("New label");
		GrilledVegetablePlatterImage.setIcon(new ImageIcon(
				UserHomePage.class.getResource("/testscene/ImagesHMS/grilled_vegetable_platter_resized.png")));
		GrilledVegetablePlatterImage.setBounds(0, 50, 289, 176);
		MenuDish10.add(GrilledVegetablePlatterImage);

		MenuDish11 = new JPanel();
		MenuDish11.setLayout(null);
		MenuDish11.setForeground(new Color(0, 0, 237));
		MenuDish11.setBackground(new Color(0, 0, 237));
		MenuDish11.setBounds(100, 1670, 289, 274);
		scrollablePanel.add(MenuDish11);

		Namebar_10 = new JPanel();
		Namebar_10.setLayout(null);
		Namebar_10.setBounds(0, 225, 289, 50);
		MenuDish11.add(Namebar_10);

		panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBounds(0, 0, 245, 50);
		Namebar_10.add(panel_20);

		btnNewButton_10 = new JButton("Add");
		btnNewButton_10.setBounds(70, 11, 140, 29);
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_10.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish11, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_10.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_10.getText(); // Ensure to update "New label" to the actual dish
																	// name
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel(); // Assuming
																								// Checkouttable is
																								// defined elsewhere
						boolean exceedsLimit = false;

						// Check if adding more exceeds the limit
						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish11,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_10.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_10.getText()) + 100;
												QtwLabel_10.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							// Update or add the item to the checkout table
							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_10.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_10.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish11,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_20.add(btnNewButton_10);

		spinner_10 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_10.setBounds(245, 6, 43, 40);
		Namebar_10.add(spinner_10);

		PriceBar_10 = new JPanel();
		PriceBar_10.setLayout(null);
		PriceBar_10.setBounds(0, 0, 289, 50);
		MenuDish11.add(PriceBar_10);

		panel_21 = new JPanel();
		panel_21.setLayout(null);
		panel_21.setBounds(0, 0, 55, 50);
		PriceBar_10.add(panel_21);

		PriceLabel_10 = new JLabel(" £11.0");
		PriceLabel_10.setBounds(0, 0, 55, 45);
		panel_21.add(PriceLabel_10);

		NameLabel_10 = new JLabel("Chicken Caesar Salad");
		NameLabel_10.setBounds(100, 16, 150, 16);
		PriceBar_10.add(NameLabel_10);

		QuantityBar_10 = new JPanel();
		QuantityBar_10.setLayout(null);
		QuantityBar_10.setBounds(249, 120, 40, 40);
		MenuDish11.add(QuantityBar_10);

		QtwLabel_10 = new JLabel("99");
		QtwLabel_10.setBounds(12, 0, 45, 45);
		QuantityBar_10.add(QtwLabel_10);

		ChickenCaesarSaladImage = new JLabel("New label");
		ChickenCaesarSaladImage.setIcon(new ImageIcon(
				UserHomePage.class.getResource("/testscene/ImagesHMS/chicken_caesar_salad_updated_resized.png")));
		ChickenCaesarSaladImage.setBounds(0, 50, 289, 176);
		MenuDish11.add(ChickenCaesarSaladImage);

		MenuDish12 = new JPanel();
		MenuDish12.setLayout(null);
		MenuDish12.setForeground(new Color(0, 0, 237));
		MenuDish12.setBackground(new Color(0, 0, 237));
		MenuDish12.setBounds(439, 1670, 289, 274);
		scrollablePanel.add(MenuDish12);

		Namebar_11 = new JPanel();
		Namebar_11.setLayout(null);
		Namebar_11.setBounds(0, 225, 289, 50);
		MenuDish12.add(Namebar_11);

		panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBounds(0, 0, 245, 50);
		Namebar_11.add(panel_22);

		btnNewButton_11 = new JButton("Add");
		btnNewButton_11.setBounds(70, 11, 140, 29);
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinnerValue = (Integer) spinner_11.getValue();
				if (spinnerValue > 10) {
					JOptionPane.showMessageDialog(MenuDish12, "You cannot add more than 10 items.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int currentQuantity = Integer.parseInt(QtwLabel_11.getText());
					if (currentQuantity >= spinnerValue) {
						String foodName = NameLabel_11.getText(); // Remember to replace "New label" with the actual
																	// dish name
						DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel(); // Assuming
																								// 'Checkouttable' is
																								// your JTable
						boolean exceedsLimit = false;

						for (int i = 0; i < model.getRowCount(); i++) {
							String existingFoodName = (String) model.getValueAt(i, 0);
							if (existingFoodName.equals(foodName)) {
								int existingQuantity = (Integer) model.getValueAt(i, 1);
								int totalQuantity = existingQuantity + spinnerValue;
								if (totalQuantity > 10) {
									JOptionPane.showMessageDialog(MenuDish12,
											"You cannot have more than 10 of " + foodName + ".", "Error",
											JOptionPane.ERROR_MESSAGE);
									exceedsLimit = true;
									break;
								}
							}
						}

						if (!exceedsLimit) {
							int newQuantity = currentQuantity - spinnerValue;
							QtwLabel_11.setText(String.valueOf(newQuantity));

							if (newQuantity < 1) {
								Timer restockTimer = new Timer(1000, null);
								restockTimer.addActionListener(new ActionListener() {

									int secondsLeft = 180;

									@Override
									public void actionPerformed(ActionEvent e) {
										if (secondsLeft > 0) {
											secondsLeft--;
											int minutes = secondsLeft / 60;
											int seconds = secondsLeft % 60;
											timerLabel.setText(
													String.format("              Timer: %02d:%02d", minutes, seconds));
										} else {
											SwingUtilities.invokeLater(() -> {
												int restockedQuantity = Integer.parseInt(QtwLabel_11.getText()) + 100;
												QtwLabel_11.setText(String.valueOf(restockedQuantity));
												timerLabel.setText("              Timer: 00:00"); // Reset timer display
												restockTimer.stop();
											});

										}
									}
								});
								restockTimer.start();
							}

							boolean itemExists = false;
							for (int i = 0; i < model.getRowCount(); i++) {
								String existingFoodName = (String) model.getValueAt(i, 0);
								if (existingFoodName.equals(foodName)) {
									int existingQuantity = (Integer) model.getValueAt(i, 1);
									double price = (Double) model.getValueAt(i, 2);
									model.setValueAt(existingQuantity + spinnerValue, i, 1);
									model.setValueAt((existingQuantity + spinnerValue) * price, i, 3);
									itemExists = true;
									break;
								}
							}

							if (!itemExists) {
								double price = Double.parseDouble(PriceLabel_11.getText().replaceAll("[^\\d.]", ""));
								double total = spinnerValue * price;
								model.addRow(new Object[] { foodName, spinnerValue, price, total });
							}

							spinner_11.setValue(1); // Reset the spinner to 1 after adding the item
						}
					} else {
						JOptionPane.showMessageDialog(MenuDish12,
								"Not enough items available. Please, choose available quantity and wait 3 minutes",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				updateOverallTotal();
			}
		});

		panel_22.add(btnNewButton_11);

		spinner_11 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinner_11.setBounds(245, 6, 43, 40);
		Namebar_11.add(spinner_11);

		PriceBar_11 = new JPanel();
		PriceBar_11.setLayout(null);
		PriceBar_11.setBounds(0, 0, 289, 50);
		MenuDish12.add(PriceBar_11);

		panel_23 = new JPanel();
		panel_23.setLayout(null);
		panel_23.setBounds(0, 0, 55, 50);
		PriceBar_11.add(panel_23);

		PriceLabel_11 = new JLabel(" £10.0");
		PriceLabel_11.setBounds(0, 0, 55, 45);
		panel_23.add(PriceLabel_11);

		NameLabel_11 = new JLabel("Tomato Basil Panini");
		NameLabel_11.setBounds(100, 16, 150, 16);
		PriceBar_11.add(NameLabel_11);

		QuantityBar_11 = new JPanel();
		QuantityBar_11.setLayout(null);
		QuantityBar_11.setBounds(249, 120, 40, 40);
		MenuDish12.add(QuantityBar_11);

		QtwLabel_11 = new JLabel("99");
		QtwLabel_11.setBounds(12, 0, 45, 45);
		QuantityBar_11.add(QtwLabel_11);

		MenuWord = new JLabel("Menu\n");
		MenuWord.setForeground(new Color(247, 239, 138));
		MenuWord.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 50));
		MenuWord.setBounds(340, 31, 151, 68);
		scrollablePanel.add(MenuWord);

		TomatoBasilPaniniImage = new JLabel("New label");
		TomatoBasilPaniniImage.setIcon(
				new ImageIcon(UserHomePage.class.getResource("/testscene/ImagesHMS/tomato_basil_panini_resized.png")));
		TomatoBasilPaniniImage.setBounds(0, 50, 289, 176);
		MenuDish12.add(TomatoBasilPaniniImage);

		circle1 = new JLabel("New label");
		circle1.setIcon(new ImageIcon(UserHomePage.class
				.getResource("/testscene/ImagesHMS/white_circle_with_F7EF8A_border_transparent_corrected.png")));
		circle1.setBounds(27, 250, 25, 25);
		scrollablePanel.add(circle1);

		circle2 = new JLabel("New label");
		circle2.setIcon(new ImageIcon(UserHomePage.class
				.getResource("/testscene/ImagesHMS/white_circle_with_F7EF8A_border_transparent_corrected.png")));
		circle2.setBounds(27, 558, 25, 25);
		scrollablePanel.add(circle2);

		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// Add the JScrollPane to CenterPanel
		CenterPanel.add(scrollPane_1);

		contentPane.add(Menu);

		timeLabel = new JLabel();
		timeLabel.setHorizontalAlignment(JLabel.CENTER);

		timeLabel.setForeground(Color.decode("#F7EF8A"));

		timeLabel.setBounds(1046, 0, 250, 50);

		contentPane.add(timeLabel);

		RoundedPanel timeLabel = new RoundedPanel(25, new Color(13, 146, 118)); 
		timeLabel.setBounds(1046, 0, 250, 50); 
		contentPane.add(timeLabel); 

		contentPane.add(RefreshMenu);
		contentPane.add(PanelLeft);
		contentPane.add(PanelRight);
		updateTime();

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTime();
			}
		});
		timer.start();

		contentPane.add(lmageBack);

	}

	private void updateOverallTotal() {
		DefaultTableModel model = (DefaultTableModel) Checkouttable.getModel();
		double total = 0.00;
		for (int i = 0; i < model.getRowCount(); i++) {
			Object value = model.getValueAt(i, 3); // Assuming the Total Price is in column 3
			if (value != null && value instanceof String) {
				String stringValue = (String) value;
				try {
					// Check if the string can be parsed to a double, excluding headers or
					// non-numeric values
					if (stringValue.matches("-?\\d+(\\.\\d+)?")) {
						double itemTotal = Double.parseDouble(stringValue);
						total += itemTotal;
					}
				} catch (NumberFormatException e) {
					System.out.println("Skipping non-numeric value: " + stringValue);
					// Optionally log the error or handle it as needed
				}
			} else if (value instanceof Double) {
				total += (Double) value;
			}
		}
		overallTotalLabel.setText(String.format("Overall Total: £%.2f", total));
	}

	private void updateTime() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss, EEEE, dd MMMM yyyy");
		timeLabel.setText(now.format(formatter));
	}
}
