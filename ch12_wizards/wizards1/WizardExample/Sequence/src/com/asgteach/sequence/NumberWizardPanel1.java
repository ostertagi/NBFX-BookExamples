/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.sequence;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

public class NumberWizardPanel1 implements
        WizardDescriptor.Panel<WizardDescriptor>,
        PropertyChangeListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private NumberVisualPanel1 component;
    private WizardDescriptor wizard = null;
    private boolean isValid = false;
    private Integer firstNumber;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public NumberVisualPanel1 getComponent() {
        if (component == null) {
            component = new NumberVisualPanel1();
            this.component.addPropertyChangeListener(this);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return isValid;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }
    private final EventListenerList listeners = new EventListenerList();

    @Override
    public void addChangeListener(ChangeListener l) {
        listeners.add(ChangeListener.class, l);
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        listeners.remove(ChangeListener.class, l);
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
        // readSettings() is called when the visual panel is opened
        // You can read in values from a previous panel
        this.wizard = wiz;
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
        // The storeSettings() method is called when the step finishes
        wiz.putProperty(NumberVisualPanel1.PROP_FIRST_NUMBER, firstNumber);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(NumberVisualPanel1.PROP_FIRST_NUMBER)) {
            boolean oldState = isValid;
            isValid = checkValidity();
            fireChangeEvent(this, oldState, isValid);
        }
    }

    private void setMessage(String message) {
        wizard.getNotificationLineSupport().setErrorMessage(message);
    }

    @NbBundle.Messages({
        "CTL_Panel1NegativeNumber= Number must be non-negative",
        "CTL_Panel1InputRequired= Number field input is required",
        "CTL_Panel1BadNumber= Bad number format"
    })
    private boolean checkValidity() {
        if (getComponent().getFirstNumber().isEmpty()) {
            setMessage(Bundle.CTL_Panel1InputRequired());
            return false;
        }
        try {
            firstNumber = Integer.parseInt(getComponent().getFirstNumber());
            if (firstNumber < 0) {
                setMessage(Bundle.CTL_Panel1NegativeNumber());
                return false;
            }
            setMessage(null);
            return true;
        } catch (NumberFormatException e) {
            setMessage(Bundle.CTL_Panel1BadNumber());
            return false;
        }
    }

    protected final void fireChangeEvent(Object source, boolean oldState, boolean newState) {
        if (oldState != newState) {
            ChangeEvent ev = new ChangeEvent(source);
            for (ChangeListener listener : listeners.getListeners(ChangeListener.class)) {
                listener.stateChanged(ev);
            }
        }
    }
}
