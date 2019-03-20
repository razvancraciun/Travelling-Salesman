package view;

import java.awt.FlowLayout;

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
		_mainPanel=new JPanel(new FlowLayout());
		_controlPanel=new ControlPanel(_ctrl);
		_plot=new Plot();
		
		_ctrl.getAlgorithm().addObserver(_controlPanel);
		_ctrl.getAlgorithm().addObserver(_plot);
		setContentPane(_mainPanel);
		
		_mainPanel.add(_controlPanel);
		_mainPanel.add(_plot);
	}
}
