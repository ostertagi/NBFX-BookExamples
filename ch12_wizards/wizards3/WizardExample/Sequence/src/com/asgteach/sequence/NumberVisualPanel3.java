/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.sequence;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.NbBundle;

public final class NumberVisualPanel3 extends JPanel {

    public static final String PROP_THIRD_NUMBER = "thirdNumber";

    /**
     * Creates new form NumberVisualPanel3
     */
    public NumberVisualPanel3() {
        initComponents();
        thirdNumber.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                fireChange(de);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                fireChange(de);
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                fireChange(de);
            }

            private void fireChange(DocumentEvent de) {
                if (thirdNumber.getDocument() == de.getDocument()) {
                    firePropertyChange(PROP_THIRD_NUMBER, 0, 1);
                }
            }
        });
    }

    @NbBundle.Messages({"CTL_Panel3Name=Provide Sequence Third Value"})
    @Override
    public String getName() {
        return Bundle.CTL_Panel3Name();
    }

    public String getThirdNumber() {
        return thirdNumber.getText();
    }

    public void setCurrentSequence(String text) {
        currentSequence.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        thirdNumber = new javax.swing.JTextField();
        currentSequence = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NumberVisualPanel3.class, "NumberVisualPanel3.jLabel1.text")); // NOI18N

        thirdNumber.setText(org.openide.util.NbBundle.getMessage(NumberVisualPanel3.class, "NumberVisualPanel3.thirdNumber.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(currentSequence, org.openide.util.NbBundle.getMessage(NumberVisualPanel3.class, "NumberVisualPanel3.currentSequence.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(thirdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(currentSequence, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(thirdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(currentSequence)
                .addContainerGap(75, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currentSequence;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField thirdNumber;
    // End of variables declaration//GEN-END:variables
}
