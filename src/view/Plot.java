package view;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.entities.Individual;
import model.misc.AlgorithmObserver;

public class Plot extends JPanel implements AlgorithmObserver{

	private JFreeChart _chart;
	private ChartPanel _chartPanel;
	private XYSeriesCollection _dataset;
	private XYSeries _best;
	private XYSeries _generationBest;
	private XYSeries _generationAverage;
	
	public Plot() {
		_dataset = new XYSeriesCollection();
		_best=new XYSeries("Best");
		_generationBest=new XYSeries("Generation best");
		_generationAverage=new XYSeries("Generation average");
		_dataset.addSeries(_best);
		_dataset.addSeries(_generationBest);
		_dataset.addSeries(_generationAverage);
		 _chart = ChartFactory.createXYLineChart(
		        "Title", 
		        "xlabel", 
		        "ylabel", _dataset);
		_chartPanel = new ChartPanel(_chart);
		add(_chartPanel);
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
