/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.sequence;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.*;
import org.openide.util.NbBundle;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
// @ActionID(category="...", id="com.asgteach.sequence.NumberWizardAction")
// @ActionRegistration(displayName="Open Number Wizard")
// @ActionReference(path="Menu/Tools", position=...)
@ActionID(category = "Tools",
id = "org.asgteach.sequence.NumberWizardAction")
@ActionRegistration(displayName = "#CTL_NumberWizardAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools",
    position = 0, separatorAfter = 50)
})
@NbBundle.Messages({
    "CTL_NumberWizardAction=New Number Sequence",
    "CTL_NumberDialogTitle=Create New Number Sequence"
})
public final class NumberWizardAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new NumberWizardPanel1());
        panels.add(new NumberWizardPanel2());
        panels.add(new NumberWizardPanel3());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle(Bundle.CTL_NumberDialogTitle());
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            // do something
            StatusDisplayer.getDefault().setStatusText("Wizard Finished");

            // retrieve and display the numbers
            StringBuilder message = new StringBuilder("Number Sequence =  \n(");
            message.append(wiz.getProperty(NumberVisualPanel1.PROP_FIRST_NUMBER)).append(", ");
            message.append(wiz.getProperty(NumberVisualPanel2.PROP_SECOND_NUMBER)).append(", ");
            message.append(wiz.getProperty(NumberVisualPanel3.PROP_THIRD_NUMBER)).append(")");


            DialogDisplayer.getDefault().notify(
                    new NotifyDescriptor.Message(message.toString()));

        } else {
            StatusDisplayer.getDefault().setStatusText("Wizard Canceled");
        }

    }
}
