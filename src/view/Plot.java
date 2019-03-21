package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.entities.Individual;
import model.misc.AlgorithmObserver;

public class Plot extends JPanel implements AlgorithmObserver{

	private static final long serialVersionUID = -5432146133350790070L;
	private JFreeChart _chart;
	private ChartPanel _chartPanel;
	private XYSeriesCollection _dataset;
	private XYSeries _best;
	private XYSeries _generationBest;
	private XYSeries _generationAverage;
	
	public Plot() {
		setLayout(new BorderLayout());
		_dataset = new XYSeriesCollection();
		_best=new XYSeries("Best");
		_generationBest=new XYSeries("Generation best");
		_generationAverage=new XYSeries("Generation average");
		_dataset.addSeries(_best);
		_dataset.addSeries(_generationBest);
		_dataset.addSeries(_generationAverage);
		 _chart = ChartFactory.createXYLineChart(
		        "Travelling Salesman", 
		        "Generations", 
		        "Distance", _dataset);
		_chartPanel = new ChartPanel(_chart);
		add(_chartPanel,BorderLayout.CENTER);
	}

	@Override
	public void onNewGeneration(int generation,int best, int bestThisGeneration, int averageThisGeneration) {
		_best.add(generation,best);
		_generationBest.add(generation, bestThisGeneration);
		_generationAverage.add(generation, averageThisGeneration);
		
	}

	@Override
	public void onNewBest(int bestValue, Individual best) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart() {
		_best.clear();
		_generationBest.clear();
		_generationAverage.clear();
	}

	@Override
	public void onEnd() {
	}
	
}
