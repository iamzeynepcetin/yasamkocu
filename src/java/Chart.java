import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;


@ManagedBean
@RequestScoped
public class Chart implements Serializable{
        
    private PieChartModel pieModel1;
    private BarChartModel barModel;
    private LineChartModel lineModel1;
    
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
 

 
    public PieChartModel createPieModel1(int u) {
        pieModel1 = new PieChartModel();
        
        Profil p = new Profil();
        p.getRecipeNumbersByLabel(u);
        
        pieModel1.set("Vegan", p.getTriedRecipesList()[0]);
        pieModel1.set("Vejetaryen", p.getTriedRecipesList()[1]);
        pieModel1.set("Diğer", p.getTriedRecipesList()[2]);
 
        pieModel1.setTitle("Denediğin Tarifler");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
        
        return pieModel1;
    }
 
   
    public BarChartModel getBarModel() {
        return barModel;
    }
    
    public BarChartModel initBarModel(int id) {
        BarChartModel model = new BarChartModel();


        Profil p = new Profil();

        int alinankalori = p.calculateDailyReceivedCalories(id);
        int verilenkalori = p.calculateDailyGivenCalories(id);


        ChartSeries alinan = new ChartSeries();
        alinan.setLabel("Bugün Alınan Kalori");
        alinan.set("", alinankalori);


        ChartSeries verilen = new ChartSeries();
        verilen.setLabel("Bugün Verilen Kalori");
        verilen.set("", verilenkalori);


        model.addSeries(alinan);
        model.addSeries(verilen);

        return model;

    }
    
    public BarChartModel createBarModel(int id) {
        barModel = initBarModel(id);
 
        barModel.setTitle("Kalori Cetveli");
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Kalori Yönü");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Kalori Miktarı");
        yAxis.setMin(0);
        yAxis.setMax(5000);
        
        return barModel;
    }
  
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
    
    
    
    public LineChartModel initLinearModel(int id) {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("haftaliktariftakip");
        
        Profil p = new Profil();
        p.getRecipeNumbersByLabelAndDate(id);
        
        int bir = p.getWeeklyTriedRecipe()[0];
        int iki = p.getWeeklyTriedRecipe()[1];
        int uc = p.getWeeklyTriedRecipe()[2];
        int dort = p.getWeeklyTriedRecipe()[3];
        int bes = p.getWeeklyTriedRecipe()[4];
        int alti = p.getWeeklyTriedRecipe()[5];
        int yedi = p.getWeeklyTriedRecipe()[6];
        
        series1.set(1, yedi);
        series1.set(2, alti);
        series1.set(3, bes);
        series1.set(4, dort);
        series1.set(5, uc);
        series1.set(6, iki);
        series1.set(7, bir);
        
        
 
 
        model.addSeries(series1);
       
 
        return model;
    }
 
 
    public LineChartModel createLineModel(int id) {
        lineModel1 = initLinearModel(id);
        lineModel1.setTitle("Bir Hafta İçinde Denenen Tarifler");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);
        return lineModel1;
 
    }
    
}
