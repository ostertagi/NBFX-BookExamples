/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.genderviewer;

import com.asgteach.familytree.capabilities.CalendarCapability;
import com.asgteach.familytree.model.FamilyTreeManager;
import com.asgteach.familytree.model.Person;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.LifecycleManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author gail
 */
@Messages({
    "HINT_PersonNode=Person",
    "CTL_CalendarTitle=Calendar Action"
})
public class PersonNode extends AbstractNode implements PropertyChangeListener {
    private static final Logger logger = Logger.getLogger(PersonNode.class.getName());
    private final InstanceContent instanceContent;

    public PersonNode(Person person) {
        this(person, new InstanceContent());      
    }
    
    private PersonNode(Person person, InstanceContent ic) {
        super(Children.LEAF, new AbstractLookup(ic));
        instanceContent = ic;
        instanceContent.add(person);
        
        setIconBaseWithExtension("com/asgteach/familytree/genderviewer/resources/personIcon.png");
        setName(String.valueOf(person.getId()));
        setDisplayName(person.toString());
        setShortDescription(Bundle.HINT_PersonNode());
//        setShortDescription(new StringBuilder("Notes: ").append(person.getNotes()).toString());
        
        // Add a Calendar Capability to this Node
        instanceContent.add(new CalendarCapability() {

            @Override
            public void doCalendar() {
                CalendarPanel panel = new CalendarPanel();
                panel.setText("<html>Calendar Action applied to<p/>"
                        + person + "</html>");
                DialogDescriptor dd = new DialogDescriptor(panel,
                        Bundle.CTL_CalendarTitle());
                DialogDisplayer.getDefault().notify(dd);  // display & block
            }
        });
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Action getPreferredAction() {
        List<Action> personActions = (List<Action>) Utilities.actionsForPath("Actions/Nodes");
        if (!personActions.isEmpty()) {
            return personActions.get(0);
        } else {
            return super.getPreferredAction();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Action[] getActions(boolean context) {
        List<Action> personActions = new ArrayList<>(Arrays.asList(super.getActions(context)));
        // add a separator
        personActions.add(null);
        personActions.addAll(Utilities.actionsForPath("Actions/Nodes")); 
        return personActions.toArray(
                new Action[personActions.size()]);
    }

    @Override
    public String getHtmlDisplayName() {
        Person person = getLookup().lookup(Person.class);
        StringBuilder sb = new StringBuilder();
        if (person == null) {
            return null;
        }       
        switch (person.getGender()) {
            case MALE:
                sb.append("<font color='#5588FF'><b>| ");
                break;
            case FEMALE:
                sb.append("<font color='#FF8855'><b>* ");
                break;
            case UNKNOWN:
                sb.append("<b>? ");
                break;
        }
        sb.append(person.toString()).append("</b></font>");
        return sb.toString();
    }
      
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Person person = (Person) evt.getSource();
        FamilyTreeManager ftm = Lookup.getDefault().lookup(FamilyTreeManager.class);
        if (ftm == null) {
            logger.log(Level.SEVERE, "Cannot get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        } else {
            ftm.updatePerson(person);
            logger.log(Level.INFO, "PropChangeListener for {0}", person);
            setShortDescription(new StringBuilder("Notes: ").append(person.getNotes()).toString());
            fireDisplayNameChange(null, getDisplayName());
        }
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();      
        Person person = getLookup().lookup(Person.class);
        
        // create a property set for the names (first, middle, last, suffix)
        Sheet.Set setNames = Sheet.createPropertiesSet();
        setNames.setDisplayName("Names");       
        
        // create a property set read-only id
        Sheet.Set readOnlySet = new Sheet.Set();
        readOnlySet.setDisplayName("Identification");
        // put it under its own tab
        readOnlySet.setValue("tabName", " Id Info ");       

        // create a property set for gender and notes
        Sheet.Set infoSet = new Sheet.Set();
        infoSet.setName("Additional Information");
        
        sheet.put(setNames);   
        sheet.put(infoSet);
        sheet.put(readOnlySet);
        
        try {
            // create property support for the Names
            Property<String> firstnameProp = new PropertySupport.Reflection<String>(person, String.class, "firstname");
            Property<String> middlenameProp = new PropertySupport.Reflection<String>(person, String.class, "middlename");
            Property<String> lastnameProp = new PropertySupport.Reflection<String>(person, String.class, "lastname");
            Property<String> suffixProp = new PropertySupport.Reflection<String>(person, String.class, "suffix");
            
            setNames.put(firstnameProp);
            setNames.put(middlenameProp);
            setNames.put(lastnameProp);
            setNames.put(suffixProp);
                     
            // create property support for gender and notes
            Property<Person.Gender> genderProp = new PropertySupport.Reflection<Person.Gender>(person, Person.Gender.class, "gender");
            Property<String> notesProp = new PropertySupport.Reflection<String>(person, String.class, "notes");            
            
            infoSet.put(genderProp);
            infoSet.put(notesProp);
            
            // create read-only property support for id (the setter is null)
            Property<Long> idProp = new PropertySupport.Reflection<Long>(person, Long.class, "getId", null);
            readOnlySet.put(idProp);
        } catch (NoSuchMethodException ex) {
            ErrorManager.getDefault();
        }
        return sheet;
    }
}
