package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import model.cross.Cross;
import model.cross.NoCross;
import model.cross.PMXCross;
import model.entities.GeneticAlgorithm;
import model.entities.Individual;
import model.entities.Population;
import model.misc.AlgorithmObserver;
import model.mutation.ExchangeMutation;
import model.mutation.InversionMutation;
import model.mutation.Mutation;
import model.mutation.NoMutation;
import model.selection.NoSelection;
import model.selection.RouletteSelection;
import model.selection.Selection;

public class ControlPanel extends JPanel implements AlgorithmObserver {
	
	private JSpinner _noGenerations;
	private JSpinner _populationSize;
	private JSpinner _crossChance;
	private JComboBox _selection;
	private JComboBox _cross;
	private JSpinner _mutationChance;
	private JComboBox _mutation;
	private JSpinner _elitism;
	private JButton _start;
	
	private Controller _ctrl;
	private JTextField _bestDistance;
	private JTextArea _bestRoute;
	
	
	public ControlPanel(Controller ctrl) {
		_ctrl=ctrl;
		
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel generationsLabel=new JLabel("No. generations:");
		_noGenerations=new JSpinner(new SpinnerNumberModel(100,1,10000,10));
		_noGenerations.setValue(100); //default value
		add(generationsLabel);
		add(_noGenerations);
		
		JLabel populationLabel=new JLabel("Population size:");
		_populationSize=new JSpinner(new SpinnerNumberModel(_ctrl.getAlgorithm().getPopulationSize(),2,10000,10));
		add(populationLabel);
		add(_populationSize);
		
		//add(new JSeparator(SwingConstants.HORIZONTAL));
		Selection[] selections = {new NoSelection(),new RouletteSelection()} ;
		JLabel selectionLabel=new JLabel("Selection:");
		_selection=new JComboBox(selections);
		for(Selection s : selections) {
			if(s.toString().equals(_ctrl.getAlgorithm().getSelection().toString())) {
				_selection.setSelectedItem(s);
			}
		}
		add(selectionLabel);
		add(_selection);
		
		
		JLabel crossChanceLabel=new JLabel("Cross chance:");
		_crossChance=new JSpinner(new SpinnerNumberModel(0.6,0.0,1,0.01));
		add(crossChanceLabel);
		add(_crossChance);
		
		Cross[] crosses = {new PMXCross(), new NoCross() };
		JLabel crossLabel= new JLabel("Cross:");
		_cross=new JComboBox(crosses);
		add(crossLabel);
		add(_cross);
		
		JLabel mutationChanceLabel=new JLabel("Mutation chance:");
		_mutationChance=new JSpinner(new SpinnerNumberModel(0.05,0.0,1,0.01));
		add(mutationChanceLabel);
		add(_mutationChance);

		JLabel mutationLabel=new JLabel("Mutation:");
		Mutation[] mutations = { new InversionMutation(), new ExchangeMutation(), new NoMutation() } ;
		_mutation=new JComboBox(mutations);
		add(mutationLabel);
		add(_mutation);
		
		JLabel elitismLabel=new JLabel("Elitism");
		_elitism=new JSpinner(new SpinnerNumberModel(0,0,1,0.01));
		add(elitismLabel);
		add(_elitism);
		
		JLabel bestDistanceLabel=new JLabel("Best distance:");
		_bestDistance=new JTextField("N/A");
		_bestDistance.setEditable(false);
		add(bestDistanceLabel);
		add(_bestDistance);	
		
		JLabel bestRouteLabel=new JLabel("Best route:");
		_bestRoute=new JTextArea("N/A");
		_bestRoute.setEditable(false);
		add(bestRouteLabel);
		add(_bestRoute);
		
		_start=new JButton("Start");
		add(_start);
		
		addListeners();
	}


	private void addListeners() {
		_start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_ctrl.run((int)_noGenerations.getValue());
			}
			
		});
		_populationSize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				_ctrl.setPopulationSize((int)_populationSize.getValue());
			}
			
		});
		_crossChance.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				_ctrl.setCrossChance((double)_crossChance.getValue());
			}
			
		});
		_mutationChance.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				_ctrl.setMutationChance((double)_mutationChance.getValue());
			}
			
		});
		
		_elitism.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				_ctrl.setElitism((double)_elitism.getValue());
			}
			
		});
		
		_selection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.setSelection((Selection) _selection.getSelectedItem());
			}
			
		});
		
		_cross.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.setCross((Cross) _cross.getSelectedItem());
			}
			
		});
		
		_mutation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_ctrl.setMutation((Mutation) _mutation.getSelectedItem());
			}
			
		});
		
	}


	@Override
	public void onNewGeneration(int best, int bestThisGeneration, int averageThisGeneration) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNewBest(int bestValue, Individual best) {
		_bestDistance.setText(""+bestValue);
		_bestRoute.setText(best.toString());
		
	}
}
