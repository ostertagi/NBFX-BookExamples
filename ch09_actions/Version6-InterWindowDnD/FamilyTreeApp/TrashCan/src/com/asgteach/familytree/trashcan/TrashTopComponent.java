/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.trashcan;

import java.awt.BorderLayout;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.asgteach.familytree.trashcan//Trash//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "TrashTopComponent",
        iconBase = "com/asgteach/familytree/trashcan/resources/delete.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "com.asgteach.familytree.trashcan.TrashTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_TrashAction",
        preferredID = "TrashTopComponent"
)
@Messages({
    "CTL_TrashAction=Trash",
    "CTL_TrashTopComponent=Trash",
    "HINT_TrashTopComponent=This is a Trash window",
    "CTL_TrashTitle=Trash"
})
public final class TrashTopComponent extends TopComponent implements ExplorerManager.Provider {
    private final ExplorerManager em = new ExplorerManager();

    public TrashTopComponent() {
        initComponents();
        setName(Bundle.CTL_TrashTopComponent());
        setToolTipText(Bundle.HINT_TrashTopComponent());
        BeanTreeView view = new BeanTreeView();
        add(view, BorderLayout.CENTER);
        em.setRootContext(new TrashNode(Bundle.CTL_TrashTitle()));
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
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

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
}
