/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smartphone.chart.bubble;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.smartphone.chart.utilities.ChartSaveCapability;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//org.smartphone.chart.bubble//BubbleChart//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "BubbleChartTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.smartphone.chart.bubble.BubbleChartTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_BubbleChartAction",
preferredID = "BubbleChartTopComponent")
@Messages({
    "CTL_BubbleChartAction=BubbleChart",
    "CTL_BubbleChartTopComponent=BubbleChart Window",
    "HINT_BubbleChartTopComponent=This is a BubbleChart window"
})
public final class BubbleChartTopComponent extends TopComponent {
    
    private static JFXPanel chartFxPanel;
    private BubbleChartController controller;    
    private BufferedImage image = null;
    // The bag of stuff we add/remove the ChartInfo from 
    private final InstanceContent content = new InstanceContent();

    public BubbleChartTopComponent() {
        initComponents();
        setName(Bundle.CTL_BubbleChartTopComponent());
        setToolTipText(Bundle.HINT_BubbleChartTopComponent());
        // Connect our lookup to the rest of the system, so that
        // Save Chart action can access the image
        associateLookup(new AbstractLookup(content));
        
        setLayout(new BorderLayout());
        //Enable the Print action for the TopComponent:
        putClientProperty("print.printable", true);
        init();
        content.add(new ChartSaveCapabilityImpl());
    }

    public void init() {
        chartFxPanel = new JFXPanel();
        add(chartFxPanel, BorderLayout.CENTER);
        Platform.setImplicitExit(false);
        Platform.runLater(() -> createScene());
    }

    private void createScene() {
        try {
            URL location = getClass().getResource("bubblechart.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

            Parent root = (Parent) fxmlLoader.load(location.openStream());
            chartFxPanel.setScene(new Scene(root));
            controller = (BubbleChartController) fxmlLoader.getController();
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private class ChartSaveCapabilityImpl implements ChartSaveCapability {

        @Override
        public String getChartName() {
            return Bundle.CTL_BubbleChartAction();
        }

        @Override
        public BufferedImage getImage() {
            if (controller == null) {
                return null;
            }
            final CountDownLatch latch = new CountDownLatch(1);
            Platform.runLater(() -> {
                // get the JavaFX image from the controller
                // must be in JavaFX Application Thread
                try {
                    image = controller.getImage();
                } finally {
                    latch.countDown();
                }
            });
            try {
                latch.await();
                return image;
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
                return null;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }
 
    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
