/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package personswingapp;

import com.asgteach.familytree.model.FamilyTreeManager;
import com.asgteach.familytree.model.Person;
import com.asgteach.familytree.model.Person.Gender;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author gail
 */
public class PersonJFrame extends javax.swing.JFrame {

    private final DefaultMutableTreeNode top
            = new DefaultMutableTreeNode("People");
    private final FamilyTreeManager ftm = FamilyTreeManager.getInstance();
    Person thePerson = null;
    private static final Logger logger = Logger.getLogger(PersonJFrame.class.getName());

    /**
     * Creates new form PersonJFrame
     */
    private PersonJFrame() {
        // Configure Logger: set its level to FINE 
        // (default threshold level is CONFIG)
        logger.setLevel(Level.FINE);
        // Define a handler to display messages on the console
        // Set level to FINE
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);

        // Define a second handler to write messages to a file
        try {
            FileHandler fileHandler = new FileHandler();
            // records sent to file javaN.log in user's home directory
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
            logger.log(Level.FINE, "Created File Handler");
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, "Couldn't create FileHandler", ex);
        }

        buildData();
        initComponents();
        personTree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        createNodes(top);
    }

    private void configureListeners() {
        // Listeners are registered after constructor returns
        // for thread safety
        ftm.addPropertyChangeListener(familyTreeListener);
        personTree.addTreeSelectionListener(treeSelectionListener);
        updateButton.addActionListener(updateListener);
    }

    public static PersonJFrame newInstance() {
        PersonJFrame pjf = new PersonJFrame();
        pjf.configureListeners();
        return pjf;
    }

    private void createNodes(DefaultMutableTreeNode top) {
        ftm.getAllPeople().forEach(p -> top.add(new DefaultMutableTreeNode(p)));
    }

    private void buildData() {
        ftm.addPerson(new Person("Homer", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Marge", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Bart", "Simpson", Person.Gender.MALE));
        ftm.addPerson(new Person("Lisa", "Simpson", Person.Gender.FEMALE));
        ftm.addPerson(new Person("Maggie", "Simpson", Person.Gender.FEMALE));
        logger.log(Level.FINE, ftm.getAllPeople().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        personTree = new JTree(top);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstTextField = new javax.swing.JTextField();
        middleTextField = new javax.swing.JTextField();
        lastTextField = new javax.swing.JTextField();
        suffixTextField = new javax.swing.JTextField();
        maleButton = new javax.swing.JRadioButton();
        femaleButton = new javax.swing.JRadioButton();
        unknownButton = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Person Application");

        jScrollPane1.setViewportView(personTree);

        jLabel1.setLabelFor(firstTextField);
        jLabel1.setText("First");

        jLabel2.setLabelFor(middleTextField);
        jLabel2.setText("Middle");

        jLabel3.setLabelFor(lastTextField);
        jLabel3.setText("Last");

        jLabel4.setLabelFor(notesTextArea);
        jLabel4.setText("Notes");

        jLabel5.setLabelFor(suffixTextField);
        jLabel5.setText("Suffix");

        genderButtonGroup.add(maleButton);
        maleButton.setText("Male");

        genderButtonGroup.add(femaleButton);
        femaleButton.setText("Female");

        genderButtonGroup.add(unknownButton);
        unknownButton.setText("Unknown");

        notesTextArea.setColumns(20);
        notesTextArea.setRows(5);
        jScrollPane2.setViewportView(notesTextArea);

        updateButton.setText("Update");
        updateButton.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(3, 3, 3)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel2)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel3)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(firstTextField)
                    .add(middleTextField)
                    .add(lastTextField)
                    .add(suffixTextField))
                .add(22, 22, 22))
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(updateButton))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel4))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(32, 32, 32)
                        .add(maleButton)
                        .add(18, 18, 18)
                        .add(femaleButton)
                        .add(18, 18, 18)
                        .add(unknownButton)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel1)
                    .add(firstTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel2)
                    .add(middleTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel3)
                    .add(lastTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel5)
                    .add(suffixTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(maleButton)
                    .add(femaleButton)
                    .add(unknownButton))
                .add(8, 8, 8)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(updateButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(8, 8, 8)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 275, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(()
                -> PersonJFrame.newInstance().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JTextField firstTextField;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lastTextField;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JTextField middleTextField;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JTree personTree;
    private javax.swing.JTextField suffixTextField;
    private javax.swing.JRadioButton unknownButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    private void editPerson(Person person) {
        thePerson = person;
        updateForm();
    }

    private void updateForm() {
        firstTextField.setText(thePerson.getFirstname());
        middleTextField.setText(thePerson.getMiddlename());
        lastTextField.setText(thePerson.getLastname());
        suffixTextField.setText(thePerson.getSuffix());
        if (thePerson.getGender().equals(Gender.MALE)) {
            maleButton.setSelected(true);
        } else if (thePerson.getGender().equals(Gender.FEMALE)) {
            femaleButton.setSelected(true);
        } else if (thePerson.getGender().equals(Gender.UNKNOWN)) {
            unknownButton.setSelected(true);
        }
        notesTextArea.setText(thePerson.getNotes());
        updateButton.setEnabled(true);
    }

    private void updateModel() {
        thePerson.setFirstname(firstTextField.getText());
        thePerson.setMiddlename(middleTextField.getText());
        thePerson.setLastname(lastTextField.getText());
        thePerson.setSuffix(suffixTextField.getText());
        if (maleButton.isSelected()) {
            thePerson.setGender(Gender.MALE);
        } else if (femaleButton.isSelected()) {
            thePerson.setGender(Gender.FEMALE);
        } else if (unknownButton.isSelected()) {
            thePerson.setGender(Gender.UNKNOWN);
        }
        thePerson.setNotes(notesTextArea.getText());
    }
    // Define listeners
    // ActionListener for Update button
    private final ActionListener updateListener = (ActionEvent e) -> {
        updateModel();
        ftm.updatePerson(thePerson);
    };
    // PropertyChangeListener for FamilyTreeManager
    private final PropertyChangeListener familyTreeListener = (PropertyChangeEvent evt) -> {
        if (evt.getNewValue() != null
                && evt.getPropertyName().equals(FamilyTreeManager.PROP_PERSON_UPDATED)) {
            Person person = (Person) evt.getNewValue();
            DefaultTreeModel model = (DefaultTreeModel) personTree.getModel();
            for (int i = 0; i < model.getChildCount(top); i++) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) model.getChild(top, i);
                if (person.equals(node.getUserObject())) {
                    node.setUserObject(person);
                    // Let the model know we made a change
                    model.nodeChanged(node);
                    logger.log(Level.FINE, "Node updated: {0}", node);
                    break;
                }
            }
        }
    };
    // TreeSelectionListener for JTree
    private final TreeSelectionListener treeSelectionListener = (TreeSelectionEvent e) -> {
        DefaultMutableTreeNode node
                = (DefaultMutableTreeNode) personTree.getLastSelectedPathComponent();
        if (node == null) {
            updateButton.setEnabled(false);
            return;
        }
        if (node.isLeaf()) {
            Person person = (Person) node.getUserObject();
            logger.log(Level.FINE, "{0} selected", person);
            editPerson(person);
        } else {
            updateButton.setEnabled(false);
        }
    };
}
