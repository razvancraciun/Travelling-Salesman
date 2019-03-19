package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

public class MainWindow extends JFrame {
	
	private JPanel _mainPanel;
	private ControlPanel _controlPanel;
	private Plot _plot;
	
	private Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Travelling Salesman");
		_ctrl=ctrl;
		initGUI();
	}

	private void initGUI() {
		_mainPanel=new JPanel();
		_controlPanel=new ControlPanel(_ctrl);
		_plot=new Plot();
		
		_ctrl.getAlgorithm().addObserver(_controlPanel);
		setContentPane(_mainPanel);
		
		_mainPanel.add(_controlPanel);
		_mainPanel.add(_plot);
	}
}
