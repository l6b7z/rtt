package ReactionTime.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import ReactionTime.Controller.Controller;

public class ApplicationView {

	private final String WINDOW_NAME = "Reaction Time Test";
	private final Dimension WINDOW_SIZE = new Dimension(800, 600);
	private final Dimension PANEL_BAR_SIZE = new Dimension(40, 40);

	private final String CARD_NAME_START_PANEL = "startPanel";
	private final String CARD_NAME_GET_READY_PANEL = "getReadyPanel";
	private final String CARD_NAME_FALSE_START_PANEL = "falseStartPanel";
	private final String CARD_NAME_CLICK_PANEL = "clickPanel";

	private final String CARD_NAME_APP_PANEL = "appPanel";
	private final String CARD_NAME_RECORDS_PANEL = "RecordsPanel";

	private final String FONT_NAME = "Arial";
	private final int FONT_SIZE_SCREEN = 100;
	private final int FONT_SIZE_RECORDS_TITLES = 15;
	private final int FONT_SIZE_RECORDS_TABLE = 18;

	private final Dimension BUTTON_SIZE = new Dimension(105, 30);
	private final Dimension BUTTON_SIZE_WIDE = new Dimension(120, 30);
	private final int BUTTON_TEXT_SIZE = 15;

	private final String BUTTON_NAME_GO_TO_APP = "Application";
	private final String BUTTON_NAME_REMOVE_LAST = "Remove Last";
	private final String BUTTON_NAME_REMOVE_ALL = "Remove All";
	private final String BUTTON_NAME_GO_TO_RECORDS = "Records";

	private final String LABEL_NAME_START_SCREEN = "Start";
	private final String LABEL_NAME_GET_READY_SCREEN = "Get Ready";
	private final String LABEL_NAME_FALSE_START_SCREEN = "Too Soon";
	private final String LABEL_NAME_CLICK_NOW_SCREEN = "Click Now";

	private final String LABEL_TABLE_TITLE_NAME = "History";
	private final String LABEL_LAST_RECORD_NAME = "Last Record";
	private final String LABEL_AVERAGE_OF_THREE_RECORDS_NAME = "Average of 3";
	private final String LABEL_AVERAGE_OF_FIVE_RECORDS_NAME = "Average of 5";
	private final String LABEL_AVERAGE_OF_TEN_RECORDS_NAME = "Average of 10";

	private final Color COLOR_BUTTON = new Color(50, 50, 50);
	private final Color COLOR_BUTTON_TEXT = new Color(200, 200, 200);
	private final Color COLOR_FONT_SCREEN_TEXT = new Color(255, 255, 255, 150);

	private final Color COLOR_SCREEN_BAR = new Color(0, 0, 0);
	private final Color COLOR_SCREEN_SIDE_BAR = Color.GRAY;
	private final Color COLOR_SCREEN_START = new Color(50, 180, 220);
	private final Color COLOR_SCREEN_GET_READY = new Color(160, 160, 160);
	private final Color COLOR_SCREEN_FALSE_START = new Color(230, 0, 0);
	private final Color COLOR_SCREEN_CLICK_NOW = new Color(20, 220, 0);
	private final Color COLOR_RECORDS_BACKGROUND = new Color(50, 180, 220);

	private final Color COLOR_FONT_RECORDS_TITLES = Color.BLACK;
	private final Color COLOR_TOP_RESULTS_BAR = Color.GRAY;

	private Controller controller;
	private int randomDelayInMS;
	private int timeElapsedInMS;
	private long initialTimeStamp;
	private Timer randomDelayTimer;
	private Timer reactionTimeTimer;

	private JFrame frame;

	private CardLayout cardLayout;
	private JPanel pageController;

	private CardLayout appMidPanelCardLayout;
	private JPanel appMidPanelpageController;

	private JPanel appPanel;
	private JPanel topAppPanel;
	private JPanel midAppPanel;
	private JPanel botAppPanel;

	private JPanel recordsPanel;
	private JPanel topRecordsPanel;
	private JPanel midRecordsPanel;
	private JPanel botRecordsPanel;

	private JPanel startPanel;
	private JPanel getReadyPanel;
	private JPanel falseStartPanel;
	private JPanel clickPanel;

	private JPanel leftRecordsPanel;
	private JPanel rightRecordsPanel;
	private JPanel midLeftRecordsPanel;
	private JPanel resultsTopRecordsPanel;
	private JPanel trivaMidRecordsPanel;
	private JPanel tableRecordsPanel;
	private JPanel midRightRecordsPanel;

	private DefaultTableModel recordsTableModel;
	private JTable recordsTable;

	private JButton goToRecordsButton;
	private JButton goToAppButton;
	private JButton RemoveLastRecordButton;
	private JButton RemoveAllRecordsButton;

	private JLabel startLabel;
	private JLabel getReadyLabel;
	private JLabel falseStartLabel;
	private JLabel clickNowLabel;

	private JLabel lastRecordValueLabel;
	private JLabel averageOf3RecordsValueLabel;
	private JLabel averageOf5RecordsValueLabel;
	private JLabel averageOf10RecordsValueLabel;

	private JLabel lastResultLable;
	private JLabel averageOfThreeLabel;
	private JLabel averageOfFiveLabel;
	private JLabel averageOfTenLabel;
	private JLabel recordsTableTitleLabel;

	

	
	public ApplicationView(Controller controller) {

		this.controller = controller;

		frame = createFrame(WINDOW_NAME, WINDOW_SIZE);

		initializePanels();
		initializeButtons();

		initializeRandomDelayTimer();
		InitializeReactionSpeedTimer();

		setKeyListeners();
		setMouseActionListeners();
		setButtonActionListeners();

		setUpMidPanelCards();
		setUpFrameCards();

		setAppMidPanels();
		setAppPanelComponents();

		setUpTableAndItsComponents();

		setRecordsPanelComponents();

		frame.setVisible(true);
	}

	private JFrame createFrame(String name, Dimension WINDOW_SIZE) {

		frame = new JFrame(name);
		frame.setSize(WINDOW_SIZE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		return frame;
	}

	private void initializePanels() {

		pageController = new JPanel();
		appMidPanelpageController = new JPanel();

		appPanel = new JPanel();
		topAppPanel = new JPanel();
		botAppPanel = new JPanel();

		midAppPanel = new JPanel();

		startPanel = new JPanel();
		getReadyPanel = new JPanel();
		falseStartPanel = new JPanel();
		clickPanel = new JPanel();

		recordsPanel = new JPanel();
		topRecordsPanel = new JPanel();
		botRecordsPanel = new JPanel();
		leftRecordsPanel = new JPanel();
		rightRecordsPanel = new JPanel();

		midRecordsPanel = new JPanel();

		midRightRecordsPanel = new JPanel();
		resultsTopRecordsPanel = new JPanel();
		trivaMidRecordsPanel = new JPanel();

		midLeftRecordsPanel = new JPanel();
		tableRecordsPanel = new JPanel();
	}

	private void initializeButtons() {

		goToAppButton = createButton(BUTTON_NAME_GO_TO_APP, BUTTON_SIZE, BUTTON_TEXT_SIZE);
		goToRecordsButton = createButton(BUTTON_NAME_GO_TO_RECORDS, BUTTON_SIZE, BUTTON_TEXT_SIZE);

		RemoveLastRecordButton = createButton(BUTTON_NAME_REMOVE_LAST, BUTTON_SIZE_WIDE, BUTTON_TEXT_SIZE);
		RemoveAllRecordsButton = createButton(BUTTON_NAME_REMOVE_ALL, BUTTON_SIZE_WIDE, BUTTON_TEXT_SIZE);
	}

	private JButton createButton(String name, Dimension size, int textSize) {

		JButton button = new JButton(name);
		button.setPreferredSize(size);
		button.setBorder(new BevelBorder(0));

		button.setBorderPainted(true);
		button.setFocusable(false);

		button.setBackground(COLOR_BUTTON);

		button.setForeground(COLOR_BUTTON_TEXT);
		button.setFont(new Font(FONT_NAME, Font.BOLD, textSize));

		return button;
	}

	private void initializeRandomDelayTimer() {
		randomDelayTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeElapsedInMS = timeElapsedInMS + 100;
				if (timeElapsedInMS > randomDelayInMS) {

					appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_CLICK_PANEL);
					initialTimeStamp = System.currentTimeMillis();
					reactionTimeTimer.start();
					randomDelayTimer.stop();
					timeElapsedInMS = 0;
				}
			}
		});
	}

	private void InitializeReactionSpeedTimer() {

		reactionTimeTimer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clickNowLabel.setText(Long.toString((System.currentTimeMillis() - initialTimeStamp)));
			}
		});
	}

	private void setKeyListeners() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (startPanel.isShowing()) {
						
						setStartPanelEvent();
					}

					else if (getReadyPanel.isShowing()) {
						
						setGetReadyPanelEvent();
					}

					else if (falseStartPanel.isShowing()) {
						
						setFalsePanelEvent();
					}

					else if (clickPanel.isShowing()) {
						
						setClickPanelEvent();
					}

				}
			}
		});
	}

	private void setMouseActionListeners() {
		startPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				setStartPanelEvent();
			}
		});

		getReadyPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				setGetReadyPanelEvent();
			}
		});

		falseStartPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				setFalsePanelEvent();
			}
		});
		clickPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				setClickPanelEvent();
			}
		});
	}

	private void setStartPanelEvent() {
		randomDelayInMS = controller.getRandomDelay();
		randomDelayTimer.start();
		appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_GET_READY_PANEL);

	}

	private void setGetReadyPanelEvent() {
		appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_FALSE_START_PANEL);
		randomDelayTimer.stop();
		reactionTimeTimer.stop();
		timeElapsedInMS = 0;

	}

	private void setFalsePanelEvent() {
		randomDelayInMS = controller.getRandomDelay();
		randomDelayTimer.start();
		appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_GET_READY_PANEL);

	}

	private void setClickPanelEvent() {
		reactionTimeTimer.stop();
		appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_START_PANEL);
		startLabel.setText(clickNowLabel.getText());
		addRecordToTable(clickNowLabel.getText());

	}

	private void addRecordToTable(String reactionTime) {
		if (recordsTableModel.getRowCount() < controller.getRecordsMaxSize()) {
			
			controller.addRecord(Integer.parseInt(reactionTime));
			
			recordsTableModel.insertRow(0, new String[] { reactionTime });
			recordsTable.setModel(recordsTableModel);
			
		} else {
			recordsTableModel.insertRow(0, new String[] { reactionTime });
			recordsTableModel.removeRow(controller.getRecordsMaxSize());
			
			controller.addRecord(Integer.parseInt(reactionTime));
			
			recordsTable.setModel(recordsTableModel);
		}
	}

	private void setButtonActionListeners() {
		RemoveLastRecordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (recordsTableModel.getRowCount() > 0) {
					controller.clearLastResult();
					recordsTableModel.removeRow(0);
					recordsTable.setModel(recordsTableModel);
					updateCurrentAndTopRecords();
				}
			}
		});

		RemoveAllRecordsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.clearResults();
				recordsTableModel.setRowCount(0);
				recordsTable.setModel(recordsTableModel);
				updateCurrentAndTopRecords();
			}
		});

		goToRecordsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				resetTimers();
				
				updateCurrentAndTopRecords();
				appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_START_PANEL);
				cardLayout.show(pageController, CARD_NAME_RECORDS_PANEL);
			}
		});

		goToAppButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				resetTimers();
				
				startLabel.setText(LABEL_NAME_START_SCREEN);
				cardLayout.show(pageController, CARD_NAME_APP_PANEL);
			}
		});
	}

	
	private void resetTimers() {
		randomDelayTimer.stop();
		reactionTimeTimer.stop();
		timeElapsedInMS = 0;
	}
	
	private void setUpMidPanelCards() {

		appMidPanelCardLayout = new CardLayout();
		appMidPanelpageController.setLayout(appMidPanelCardLayout);
		midAppPanel.setLayout(new BorderLayout());
		midAppPanel.add(appMidPanelpageController);

		appMidPanelpageController.add(startPanel, CARD_NAME_START_PANEL);
		appMidPanelpageController.add(getReadyPanel, CARD_NAME_GET_READY_PANEL);
		appMidPanelpageController.add(falseStartPanel, CARD_NAME_FALSE_START_PANEL);
		appMidPanelpageController.add(clickPanel, CARD_NAME_CLICK_PANEL);

		appMidPanelCardLayout.show(appMidPanelpageController, CARD_NAME_START_PANEL);
	}

	private void setUpFrameCards() {

		cardLayout = new CardLayout();
		pageController.setLayout(cardLayout);
		frame.add(pageController);

		pageController.add(appPanel, CARD_NAME_APP_PANEL);
		pageController.add(recordsPanel, CARD_NAME_RECORDS_PANEL);

		cardLayout.show(pageController, CARD_NAME_APP_PANEL);

	}

	private void setAppMidPanels() {

		startLabel = createAppPanelLabel(LABEL_NAME_START_SCREEN);
		setPanel(startPanel, COLOR_SCREEN_START, startLabel);

		getReadyLabel = createAppPanelLabel(LABEL_NAME_GET_READY_SCREEN);
		setPanel(getReadyPanel, COLOR_SCREEN_GET_READY, getReadyLabel);

		falseStartLabel = createAppPanelLabel(LABEL_NAME_FALSE_START_SCREEN);
		setPanel(falseStartPanel, COLOR_SCREEN_FALSE_START, falseStartLabel);

		clickNowLabel = createAppPanelLabel(LABEL_NAME_CLICK_NOW_SCREEN);
		setPanel(clickPanel, COLOR_SCREEN_CLICK_NOW, clickNowLabel);
	}

	private void setPanel(JPanel panel, Color bg, JLabel label) {

		panel.setBackground(bg);
		panel.setLayout(new BorderLayout());
		panel.add(label);
	}

	private JLabel createAppPanelLabel(String name) {

		JLabel label = new JLabel(name);
		label.setForeground(COLOR_FONT_SCREEN_TEXT);
		label.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_SCREEN));
		label.setHorizontalAlignment(JLabel.CENTER);

		return label;
	}

	private void setAppPanelComponents() {

		appPanel.setLayout(new BorderLayout());
		topAppPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		topAppPanel.setBackground(COLOR_SCREEN_BAR);

		botAppPanel.setBackground(COLOR_SCREEN_BAR);

		topAppPanel.setPreferredSize(PANEL_BAR_SIZE);
		botAppPanel.setPreferredSize(PANEL_BAR_SIZE);

		topAppPanel.add(goToRecordsButton);

		appPanel.add(topAppPanel, BorderLayout.NORTH);
		appPanel.add(midAppPanel, BorderLayout.CENTER);
		appPanel.add(botAppPanel, BorderLayout.SOUTH);
	}

	private void setUpTableAndItsComponents() {
		recordsTable = new JTable();
		recordsTable.setEnabled(false);
		recordsTable.setForeground(COLOR_FONT_RECORDS_TITLES);
		recordsTable.setBackground(COLOR_TOP_RESULTS_BAR);
		recordsTable.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_RECORDS_TABLE));
		recordsTableModel = (DefaultTableModel) recordsTable.getModel();
		recordsTableModel.addColumn("0", controller.getAllRecords().toArray());
		recordsTable.setModel(recordsTableModel);
		tableRecordsPanel.setBackground(COLOR_TOP_RESULTS_BAR);
		tableRecordsPanel.setLayout(new BorderLayout(0, 20));
		tableRecordsPanel.setPreferredSize(new Dimension(60, 0));
	}

	private JLabel createRecordsPanelLabels(String name) {

		JLabel label = new JLabel(name);
		label.setForeground(COLOR_FONT_RECORDS_TITLES);
		label.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_RECORDS_TITLES));
		label.setHorizontalAlignment(JLabel.CENTER);

		return label;
	}

	private void updateCurrentAndTopRecords() {
		lastRecordValueLabel.setText(controller.getLastRecord());
		averageOf3RecordsValueLabel.setText(controller.getAverageOfRecordsInRange(3));
		averageOf5RecordsValueLabel.setText(controller.getAverageOfRecordsInRange(5));
		averageOf10RecordsValueLabel.setText(controller.getAverageOfRecordsInRange(10));
	}

	private void setRecordsPanelComponents() {

		recordsPanel.setLayout(new BorderLayout());
		topRecordsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		botRecordsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		midRecordsPanel.setBackground(COLOR_RECORDS_BACKGROUND);
		topRecordsPanel.setBackground(COLOR_SCREEN_BAR);
		botRecordsPanel.setBackground(COLOR_SCREEN_BAR);
		leftRecordsPanel.setBackground(COLOR_SCREEN_SIDE_BAR);
		rightRecordsPanel.setBackground(COLOR_SCREEN_SIDE_BAR);

		topRecordsPanel.setPreferredSize(PANEL_BAR_SIZE);
		botRecordsPanel.setPreferredSize(PANEL_BAR_SIZE);
		leftRecordsPanel.setPreferredSize(PANEL_BAR_SIZE);
		rightRecordsPanel.setPreferredSize(PANEL_BAR_SIZE);

		lastRecordValueLabel = createRecordsPanelLabels("");
		averageOf3RecordsValueLabel = createRecordsPanelLabels("");
		averageOf5RecordsValueLabel = createRecordsPanelLabels("");
		averageOf10RecordsValueLabel = createRecordsPanelLabels("");

		updateCurrentAndTopRecords();

		recordsTableTitleLabel = createRecordsPanelLabels(LABEL_TABLE_TITLE_NAME);
		averageOfThreeLabel = createRecordsPanelLabels(LABEL_AVERAGE_OF_THREE_RECORDS_NAME);
		averageOfFiveLabel = createRecordsPanelLabels(LABEL_AVERAGE_OF_FIVE_RECORDS_NAME);
		averageOfTenLabel = createRecordsPanelLabels(LABEL_AVERAGE_OF_TEN_RECORDS_NAME);
		lastResultLable = createRecordsPanelLabels(LABEL_LAST_RECORD_NAME);

		trivaMidRecordsPanel.setBackground(COLOR_TOP_RESULTS_BAR);
		resultsTopRecordsPanel.setBackground(COLOR_TOP_RESULTS_BAR);
		resultsTopRecordsPanel.setLayout(new GridLayout(0, 4, 75, 5));

		midRecordsPanel.setLayout(new BorderLayout());
		trivaMidRecordsPanel.setLayout(new BorderLayout());
		midLeftRecordsPanel.setLayout(new BorderLayout());
		midRightRecordsPanel.setLayout(new BorderLayout());

		topRecordsPanel.add(goToAppButton);
		botRecordsPanel.add(RemoveLastRecordButton);
		botRecordsPanel.add(RemoveAllRecordsButton);

		recordsPanel.add(topRecordsPanel, BorderLayout.NORTH);
		recordsPanel.add(botRecordsPanel, BorderLayout.SOUTH);
		recordsPanel.add(midRecordsPanel, BorderLayout.CENTER);
		midRecordsPanel.add(leftRecordsPanel, BorderLayout.WEST);
		midRecordsPanel.add(rightRecordsPanel, BorderLayout.EAST);

		midRecordsPanel.add(midLeftRecordsPanel);

		midRightRecordsPanel.add(resultsTopRecordsPanel, BorderLayout.NORTH);
		midRightRecordsPanel.add(trivaMidRecordsPanel);
		midLeftRecordsPanel.add(tableRecordsPanel, BorderLayout.WEST);
		midLeftRecordsPanel.add(midRightRecordsPanel);

		resultsTopRecordsPanel.add(lastResultLable);
		resultsTopRecordsPanel.add(averageOfThreeLabel);
		resultsTopRecordsPanel.add(averageOfFiveLabel);
		resultsTopRecordsPanel.add(averageOfTenLabel);

		resultsTopRecordsPanel.add(lastRecordValueLabel);
		resultsTopRecordsPanel.add(averageOf3RecordsValueLabel);
		resultsTopRecordsPanel.add(averageOf5RecordsValueLabel);
		resultsTopRecordsPanel.add(averageOf10RecordsValueLabel);

		tableRecordsPanel.add(recordsTableTitleLabel, BorderLayout.NORTH);
		tableRecordsPanel.add(recordsTable);
		trivaMidRecordsPanel.add(new JLabel(new ImageIcon("resources/graph.PNG")), BorderLayout.CENTER);

	}

}
