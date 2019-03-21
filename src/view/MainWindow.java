package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.Controller;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 5848159615633462334L;
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
		
		_mainPanel=new JPanel(new BorderLayout());
		setContentPane(_mainPanel);
		_controlPanel=new ControlPanel(_ctrl);
		_plot=new Plot();
		
		_ctrl.getAlgorithm().addObserver(_controlPanel);
		_ctrl.getAlgorithm().addObserver(_plot);
		setContentPane(_mainPanel);
		
		JPanel cpContainer = new JPanel(new BorderLayout());
		cpContainer.add(_controlPanel,BorderLayout.CENTER);
		_controlPanel.setBorder(BorderFactory.createEmptyBorder(10,5,5,10));
		cpContainer.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.EAST);
		
		_mainPanel.add(cpContainer,BorderLayout.WEST);
		_mainPanel.add(_plot,BorderLayout.CENTER);
	}
}
