/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.coretableview;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
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
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.asgteach.coretableview//TableView//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "TableViewTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.asgteach.coretableview.TableViewTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_TableViewAction",
        preferredID = "TableViewTopComponent")
@Messages({
    "salesAmountBadFormat=Bad Format for Sales Amount",
    "refreshData=Refresh Data",
    "filterLabel=Filter data",
    "filterPrompt=Provide Year or Company Filter",
    "company=Company",
    "year=Year",
    "unitsSold=Units Sold (M)",
    "unitsSoldDescription=Units Sold in Millions",
    "salesamount=Sales Amount",
    "smartphonetitle=SmartPhone Sales Data",
    "appTitle=SmartPhone Sales Data JavaFX Client",
    "webServiceFail=Web Service call failed. Check the SmartPhoneDataServer application.",
    "HINT_TableViewTopComponent=This is a TableView window",
    "CTL_TableViewAction=TableView",
    "CTL_TableViewTopComponent=TableView Window"
})
public final class TableViewTopComponent extends TopComponent {

    private static JFXPanel fxPanel;
    private TableViewController controller;
    private final ResourceBundle resources;

    public TableViewTopComponent() {
        initComponents();
        resources = ResourceBundle.getBundle(
                "com.asgteach.coretableview.Bundle", Locale.getDefault());
        setName(Bundle.CTL_TableViewTopComponent());
        setToolTipText(Bundle.HINT_TableViewTopComponent());

        setLayout(new BorderLayout());
        init();
    }

    public void init() {
        fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);
        Platform.setImplicitExit(false);
        Platform.runLater(() -> createScene());
    }

    private void createScene() {
        try {
            URL location = getClass().getResource("TableView.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();

            System.out.println("Default Locale = " + Locale.getDefault());
            fxmlLoader.setResources(resources);
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());

            Parent root = (Parent) fxmlLoader.load(location.openStream());
            fxPanel.setScene(new Scene(root));
            controller = (TableViewController) fxmlLoader.getController();

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
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
