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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controller.Controller;
import model.cross.Cross;
import model.cross.NoCross;
import model.cross.PMXCross;
import model.entities.GeneticAlgorithm;
import model.entities.Population;
import model.mutation.ExchangeMutation;
import model.mutation.InversionMutation;
import model.mutation.Mutation;
import model.mutation.NoMutation;
import model.selection.NoSelection;
import model.selection.RouletteSelection;
import model.selection.Selection;

public class ControlPanel extends JPanel{
	
	private JSpinner _noGenerations;
	private JSpinner _populationSize;
	private JSpinner _crossChance;
	private JComboBox _selection;
	private JComboBox _cross;
	private JSpinner _mutationChance;
	private JComboBox _mutation;
	private JSpinner _elitism;
	private JButton _start;
	
	private Controller _controller;
	
	
	public ControlPanel() {
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		JLabel generationsLabel=new JLabel("No. generations:");
		_noGenerations=new JSpinner(new SpinnerNumberModel(100,1,10000,10));
		_noGenerations.setValue(100); //default value
		add(generationsLabel);
		add(_noGenerations);
		
		JLabel populationLabel=new JLabel("Population size:");
		_populationSize=new JSpinner(new SpinnerNumberModel(100,2,10000,10));
		//_populationSize.setValue(100); //default value
		add(populationLabel);
		add(_populationSize);
		
		//add(new JSeparator(SwingConstants.HORIZONTAL));
		Selection[] selections = { new RouletteSelection(), new NoSelection()} ;
		JLabel selectionLabel=new JLabel("Selection:");
		_selection=new JComboBox(selections);
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
		
		_start=new JButton("Start");
		_start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//should not be like this
				//create an initial controller on startup
				//update variables as they are changed in here
				_controller=new Controller(new GeneticAlgorithm(
						new Population((int) _populationSize.getValue()),
						(Mutation)_mutation.getItemAt(_mutation.getSelectedIndex()),
						(Selection)_selection.getItemAt(_selection.getSelectedIndex()),
						(Cross) _cross.getItemAt(_cross.getSelectedIndex()),
						(double)_crossChance.getValue(),
						(double)_mutationChance.getValue(),
						(double)_elitism.getValue()
						
								));
				
				_controller.run(10);
			}
			
		});
		add(_start);
	}
}
