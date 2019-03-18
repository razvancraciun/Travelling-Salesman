package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	
	private JPanel _mainPanel;
	private ControlPanel _controlPanel;
	private Plot _plot;
	
	public MainWindow() {
		super("Travelling Salesman");
		initGUI();
	}

	private void initGUI() {
		_mainPanel=new JPanel();
		_controlPanel=new ControlPanel();
		_plot=new Plot();
		
		setContentPane(_mainPanel);
		
		_mainPanel.add(_controlPanel);
		_mainPanel.add(_plot);
	}
}
