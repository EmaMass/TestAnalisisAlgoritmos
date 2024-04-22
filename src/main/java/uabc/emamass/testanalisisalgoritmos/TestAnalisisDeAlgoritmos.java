/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.emamass.testanalisisalgoritmos;
import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.*;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/**
 *
 * @author poppe
 */
public class TestAnalisisDeAlgoritmos extends ApplicationFrame{
     
     public TestAnalisisDeAlgoritmos(String applicationTitle,String chartTitle) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle,
         "Valor N",
         "Tiempo (Nano)",
         createDataset(),
         PlotOrientation.VERTICAL,
         true ,true ,false);
         
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      chartPanel.setPreferredSize(new java.awt.Dimension(560 ,367));
      final XYPlot plot = xylineChart.getXYPlot();
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      renderer.setSeriesPaint(0 ,Color.RED );
      renderer.setSeriesPaint(1 ,Color.GREEN );
      renderer.setSeriesPaint(2 ,Color.YELLOW );
      renderer.setSeriesStroke(0 ,new BasicStroke( 4.0f ));
      renderer.setSeriesStroke(1 ,new BasicStroke( 3.0f ));
      renderer.setSeriesStroke(2 ,new BasicStroke( 2.0f ));
      plot.setRenderer(renderer); 
      setContentPane(chartPanel); 
   }
   
   private XYDataset createDataset() {
      final XYSeries iterativa = new XYSeries("Iterativa");          
        int i = 10;
        long MAX = 200;
        for(i = 10; i <= MAX; i *= 2){
            long startTimeIterativa = System.nanoTime();
            long n = sumatoria0aN(i);
            long endTimeIterativa = System.nanoTime();
            long resulTime = Math.abs(startTimeIterativa - endTimeIterativa);
            iterativa.add(n,resulTime);
        }
      
      final XYSeries gauss = new XYSeries("Gauss");
      for(i = 10; i <= MAX; i *= 2){
          long startTimeGauss = System.nanoTime();
          long n = sumatoriaGauss(i);
          long endTimeGauss = System.nanoTime();
          long resulTime = Math.abs(startTimeGauss - endTimeGauss);
          gauss.add(n,resulTime);
      }
      
      final XYSeries recursiva = new XYSeries("Recursiva");
      for(i = 10; i <= MAX; i *= 2){
          long startTimeRecursiva = System.nanoTime();
          long n = sumatoriaRecursiva(i,0);
          long endTimeRecursiva = System.nanoTime();
          long resulTime = Math.abs(startTimeRecursiva - endTimeRecursiva);
          recursiva.add(n,resulTime);
      }
      
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(iterativa);      
      dataset.addSeries(gauss);
      dataset.addSeries(recursiva);
      return dataset;
   }

   public static void main(String[] args) {
      TestAnalisisDeAlgoritmos chart = new TestAnalisisDeAlgoritmos("Graficas Analisis Algoritmos",
         "Tarea Analisis de Algoritmos");
      chart.pack();          
      RefineryUtilities.centerFrameOnScreen(chart);          
      chart.setVisible(true); 
   }
   
   public static long sumatoria0aN(long n){
        long sumatoria = 0;
        for(int i = 0; i <= n; i++){
            sumatoria += i;
        }
        return sumatoria; 
    }
   
   public static long sumatoriaGauss(long n){
       return (n*(n+1))/2;
   }
   
   public static long sumatoriaRecursiva(long n, long i){
       if(n == i){
           return i;
       } else{
           return i + sumatoriaRecursiva(n,i+1);
       }
   }
}
