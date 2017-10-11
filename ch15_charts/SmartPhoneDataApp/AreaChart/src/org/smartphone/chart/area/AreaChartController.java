/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smartphone.chart.area;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javax.swing.event.TableModelEvent;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;
import org.smartphone.tabledata.api.MyTableDataModel;

/**
 *
 * @author gail
 */
public class AreaChartController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private AreaChart<String, Number> chart;
    private ObservableList<AreaChart.Series<String, Number>> acData;
    private MyTableDataModel tableModel;
    private static final Logger logger = Logger.getLogger(AreaChartController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createAreaChart();
    }

    private void createAreaChart() {
        tableModel = Lookup.getDefault().lookup(MyTableDataModel.class);
        if (tableModel == null) {
            logger.log(Level.SEVERE, "Cannot get TableModel object");
            LifecycleManager.getDefault().exit();
        }
        xAxis.setCategories(FXCollections.observableArrayList(
                tableModel.getColumnNames()));
        xAxis.setLabel(tableModel.getNameDescription());
        double tickUnit = tableModel.getTickUnit();

        yAxis.setTickUnit(tickUnit);
        yAxis.setLabel(tableModel.getDataDescription());
        chart.setTitle(tableModel.getTitle());
        chart.setData(getAreaChartData());

        // set up the event handler to respond to changes in the table data
        // This is a swing event, so we must use Platform.runLater()
        // to update JavaFX components
        tableModel.addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                final int row = e.getFirstRow();
                final int column = e.getColumn();
                final Number value = (Number) ((MyTableDataModel) e.getSource()).getValueAt(row, column);

                Platform.runLater(() -> {
                    XYChart.Series<String, Number> s
                            = chart.getData().get(row);
                    AreaChart.Data<String, Number> data
                            = s.getData().get(column);
                    data.setYValue(value);
                });
            }
        });
    }

    private ObservableList<AreaChart.Series<String, Number>> getAreaChartData() {
        if (acData == null) {
            acData = FXCollections.observableArrayList();
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(tableModel.getCategoryName(row));
                for (int column = 0;
                        column < tableModel.getColumnCount(); column++) {
                    series.getData().add(new AreaChart.Data<>(
                            tableModel.getColumnName(column),
                            (Number) tableModel.getValueAt(row, column)));
                }
                acData.add(series);
            }
        }
        return acData;
    }

    public BufferedImage getImage() {
        // must be in JavaFX Application Thread
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.ALICEBLUE);
        Image jfximage = stackpane.snapshot(params, null);
        return SwingFXUtils.fromFXImage(jfximage, null);
    }
}