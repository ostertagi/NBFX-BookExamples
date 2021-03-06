/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.personeditor;

import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.asgteach.personeditor//MyMode//EN",
autostore = false)
@TopComponent.Description(preferredID = "MyModeTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.asgteach.personeditor.MyModeTopComponent")
@ActionReference(path = "Menu/Window", position = 444)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MyModeAction",
preferredID = "MyModeTopComponent")
@Messages({
    "CTL_MyModeAction=MyMode",
    "CTL_MyModeTopComponent=MyMode Window",
    "HINT_MyModeTopComponent=This is a MyMode window"
})
public final class MyModeTopComponent extends TopComponent {


    public MyModeTopComponent() {
        initComponents();
        setName(Bundle.CTL_MyModeTopComponent());
        setToolTipText(Bundle.HINT_MyModeTopComponent());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(title, org.openide.util.NbBundle.getMessage(MyModeTopComponent.class, "MyModeTopComponent.title.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(title)
                .addContainerGap(289, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(title)
                .addContainerGap(253, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening

    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    @Override
    protected void componentActivated() {
        System.out.println("MyModeTopComponent activated");
        super.componentActivated();
        WindowManager wm = WindowManager.getDefault();
        title.setText(wm.findMode(this).getName());
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
