package nl.ns.demoswing.impl;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class DemoSwing extends JFrame {
    private static final String gapList[] = {"0", "10", "15", "20"};
    private final static int maxGap = 20;
    private static int abstractCounter = 0;

    private static ArrayList<String> itemList = new ArrayList<String>();
    private GridLayout gridLayout1 = new GridLayout(0,2);
    private GridLayout gridLayout2 = new GridLayout(0,3);
    private GridLayout gridLayout3 = new GridLayout(10,2);
    private GridLayout gridLayout4 = new GridLayout(0,2);

    private JPanel jPanel1 = new JPanel(); // For the JLabel, JTextfields, JButtons
    private JPanel jPanel2 = new JPanel(); // for the JComboxbox apply gaps
    private JPanel jPanel3 = new JPanel(); // For JCheckbox
    private JPanel jPanel4 = new JPanel(); // For JList

    private JTable jTable;

    private JComboBox horGapComboBox;
    private JComboBox verGapComboBox;

    private JLabel label1Button = new JLabel("Button 1: ");
    private JLabel label2Button = new JLabel("Button 2: ");
    private JLabel label3Button = new JLabel("Button 3: ");
    private JLabel label4Button = new JLabel("Button 4: ");
    private JLabel label5Button = new JLabel("Button 5: ");
    private JLabel resultLabel = new JLabel("Result: ");
    private JLabel label1Checkbox = new JLabel("Checkbox 1: ");
    private JLabel label2Checkbox = new JLabel("Checkbox 2: ");
    private JLabel label3Checkbox = new JLabel("Checkbox 3: ");
    private JLabel label1RadioButton = new JLabel("RadioButton 1: ");
    private JLabel label2RadioButton = new JLabel("RadioButton 2: ");
    private JLabel label3RadioButton = new JLabel("RadioButton 3: ");
    private JLabel formattedFieldLabel;

    private JButton button1 = new JButton("Button1");
    private JButton button2 = new JButton("Button2");
    private JButton button3 = new JButton("Button3");
    private JButton button4 = new JButton("Long-Named Button 4");
    private JButton button5 = new JButton("Button5");
    private JButton applyButton = new JButton("Apply gaps");
    private JButton fakeButton = new JButton("Just fake button");

    private JTextField field1 = new JTextField("Result");


    private JFormattedTextField formattedTextField;

    private JCheckBox checkbox1 = new JCheckBox("Checkbox1");
    private JCheckBox checkbox2 = new JCheckBox("Checkbox2");
    private JCheckBox checkbox3 = new JCheckBox("Checkbox3");

    private JRadioButton radiobutton1 = new JRadioButton("RadioButton1");
    private JRadioButton radiobutton2 = new JRadioButton("RadioButton2");
    private JRadioButton radiobutton3 = new JRadioButton("RadioButton3");

    private DefaultListModel lm = new DefaultListModel();
    private JList list = new JList(lm);

    private JTree jtree1;
    private JTree jtree2;
    private JScrollPane pane1;
    private JScrollPane pane2;

    private AbstractButton abstractButton = new JButton("AbstractButton");

    public DemoSwing(String name) {
        super(name);
        setResizable(false);
    }

    private void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
        horGapComboBox.setName("horGap");
        verGapComboBox.setName("verGap");
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        /* Use an appropriate Look and Feel */
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void addComponentsToPane(final Container pane) throws IOException, ParseException {
        initGaps();
        jPanel1.setLayout(gridLayout1);
        jPanel2.setLayout(gridLayout2);
        jPanel3.setLayout(gridLayout3);
        jPanel4.setLayout(gridLayout4);

        //Set up components preferred size
        Dimension buttonSize = fakeButton.getPreferredSize();
        jPanel1.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));

        //Add labels + buttons to experiment with Grid Layout
        jPanel1.add(label1Button);
        jPanel1.add(label2Button);
        jPanel1.add(label3Button);
        jPanel1.add(label4Button);
        jPanel1.add(label5Button);

        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);
        jPanel1.add(button5);

        jPanel3.add(resultLabel);
        field1.setName("Result");
        jPanel3.add(field1);

        //Add controls to set up horizontal and vertical gaps
        jPanel2.add(new Label("Horizontal gap:"));
        jPanel2.add(new Label("Vertical gap:"));
        jPanel2.add(new Label(" "));
        jPanel2.add(horGapComboBox);
        jPanel2.add(verGapComboBox);
        jPanel2.add(applyButton);

        jPanel3.add(label1Checkbox);
        jPanel3.add(checkbox1);
        jPanel3.add(label2Checkbox);
        jPanel3.add(checkbox2);
        jPanel3.add(label3Checkbox);
        jPanel3.add(checkbox3);

        jPanel3.add(label1RadioButton);
        jPanel3.add(radiobutton1);
        jPanel3.add(label2RadioButton);
        jPanel3.add(radiobutton2);
        jPanel3.add(label3RadioButton);
        jPanel3.add(radiobutton3);

        jPanel3.add(addSomeElementsToTheTable());
        tableListener();

        // JTree
        fillTheJTree();
        jPanel3.add(pane1);
        jPanel3.add(pane2);
        treeListener(jtree1);
        treeListener(jtree2);

        abstractButton.setName("AbstractButton");
        // When you start the application firstly set the Abstract Button Label
        setTheAbstractButtonLabel("<html>Abstract Button<br><font face='courier new'"
                + " color=red>(You Have to click me!)</font></html>");
        jPanel3.add(abstractButton);
        createAbstractButtonListener(); // Listen to the button in order to decide which message will be displayed

        createJFormattedTextField();
        jPanel3.add(formattedFieldLabel);
        jPanel3.add(formattedTextField);

        addSomeElementsToList(lm);
        list.setName("List");
        jPanel4.add(list);

        list.addListSelectionListener(listListener());// Add the Lists to some listeners

        applyGapsListener(applyButton); // Do some action with Apply Gaps button

        createButtonListener(button1); // Add the Button Listeners to the application
        createButtonListener(button2);
        createButtonListener(button3);
        createButtonListener(button4);
        createButtonListener(button5);

        checkboxListener(checkbox1); // Add the checkbox Listeners to the Application
        checkboxListener(checkbox2);
        checkboxListener(checkbox3);

        radioButtonListener(radiobutton1);
        radioButtonListener(radiobutton2);
        radioButtonListener(radiobutton3);

        pane.add(jPanel1, BorderLayout.NORTH);
        pane.add(jPanel2, BorderLayout.SOUTH);
        pane.add(jPanel3, BorderLayout.CENTER);
        pane.add(jPanel4, BorderLayout.EAST);

        setPreferredSize(new Dimension(1024,768));
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() throws IOException, ParseException {
        //Create and set up the window.
        DemoSwing frame = new DemoSwing("Demo Swing Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void createJFormattedTextField() throws ParseException {
        formattedFieldLabel = new JLabel("FormattedTextField");
        MaskFormatter formatter = new MaskFormatter("###'-##'-####");
        formattedTextField = new JFormattedTextField(formatter);
        formattedTextField.setName("FormattedTextField");
        formattedTextField.setValue("123-45-6789");
        formattedTextField.setColumns(20);

    }

    public String setTheAbstractButtonLabel(String s) throws IOException {
        // Set the buttonText with the new HTML content
        abstractButton.setText(s);
        org.jsoup.nodes.Document doc = Jsoup.parse(s);
        Elements fontAttribute = doc.select("font");
        String fa = fontAttribute.text();
        System.out.println(" fontAttribute: " + fa);
        // Return a nicely formed value text instead of HTML content
        return fa;
    }

    private void fillTheJTree() {
        Vector<String> vector1 = new Vector<String>();
        vector1.add("V1 One");
        vector1.add("V1 Two");
        vector1.add("V1 Three");
        jtree1 = new JTree(vector1);
        jtree1.setRootVisible(true);
        jtree1.setName("JTree1");
        pane1 = new JScrollPane(jtree1);

        Vector<String> vector2 = new Vector<String>();
        vector2.add("V2 One");
        vector2.add("V2 Two");
        vector2.add("V2 Three");
        jtree2 = new JTree(vector2);
        jtree2.setRootVisible(true);
        jtree2.setName("JTree2");
        pane2 = new JScrollPane(jtree2);
    }


    private void createAbstractButtonListener() {
        abstractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(actionEvent.getID() == ActionEvent.ACTION_FIRST && abstractCounter == 0) {
                    try {
                        setTheAbstractButtonLabel("<html>Abstract Button<br><font face='courier new'"
                                + " color=red>(Good Job! You have clicked me the first time!)</font></html>");
                        abstractCounter++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        setTheAbstractButtonLabel("<html>Abstract Button<br><font face='courier new'"
                                + " color=red>(What!? You have clicked me again??)</font></html>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void createButtonListener(JButton buttonObject) {
        final String someText = "You have clicked " + buttonObject.getText();

        buttonObject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                field1.setText("");
                field1.setText(someText);
                System.out.println("Field getName(): " + field1.getName());
            }
        });

    }

    private void treeListener(final JTree tree) {
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                Object nodeInfo = node.getUserObject();
                String data = nodeInfo.toString();
                field1.setText("");
                field1.setText("You have selected " + data);
                System.out.println("Data: " + data);
            }
        });
    }

    private JTable tableListener() {
        jTable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = jTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String selectedData = null;
                int[] selectedRow = jTable.getSelectedRows();
                int[] selectedColumns = jTable.getSelectedColumns();
                for (int aSelectedRow : selectedRow) {
                    for (int selectedColumn : selectedColumns) {
                        selectedData = (String) jTable.getValueAt(aSelectedRow, selectedColumn);
                    }
                }
                field1.setText("");
                field1.setText("You have selected " + selectedData);

            }
        });
        return jTable;
    }

    private ListSelectionListener listListener() {
        return new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                if (!adjust) {
                    JList list1 = (JList) listSelectionEvent.getSource();
                    int selections[] = list1.getSelectedIndices();
                    Object selectionValues[] = list1.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        field1.setText("");
                        field1.setText("You have selected " + selectionValues[i]);
                        System.out.println("  Selections: " + selections[i] + "/" + selectionValues[i] + " ");
                    }
                }
            }
        };
    }

    private void checkboxListener(final JCheckBox checkbox) {
        final String checkboxSelectedText = "You have selected " + checkbox.getText();
        final String checkboxUnselectedText = "You have Unselected " + checkbox.getText();
        checkbox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(checkbox.isSelected()){
                    field1.setText("");
                    field1.setText(checkboxSelectedText);
                }else{
                    field1.setText("");
                    field1.setText(checkboxUnselectedText);
                }

            }
        });
    }

    private void radioButtonListener(final JRadioButton radiobutton) {
        final String radioSelectedText = "You have selected " + radiobutton.getText();
        final String radioUnselectedText = "You have Unselected " + radiobutton.getText();
        radiobutton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(radiobutton.isSelected()){
                    field1.setText("");
                    field1.setText(radioSelectedText);
                }else{
                    field1.setText("");
                    field1.setText(radioUnselectedText);
                }

            }
        });
    }

    private void applyGapsListener(JButton applyButton) {
        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String horGap = (String)horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String)verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value

                gridLayout1.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                gridLayout1.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                gridLayout1.layoutContainer(jPanel1);
                field1.setText("");
                field1.setText("You have set the Hgap: " + gridLayout1.getHgap() + " and the Vgap to " + gridLayout1.getVgap());
            }
        });
    }

    private void addSomeElementsToList(DefaultListModel lm) {
        for (int i = 0; i <= 8; i++) {
            itemList.add("Item " + i);
            lm.addElement(itemList.get(i));
        }
    }

    private JScrollPane addSomeElementsToTheTable() {
        Object columnNames[] = { "Column One", "Column Two", "Column Three" };

        DefaultTableModel model = new DefaultTableModel(columnNames,3);
        jTable = new JTable(model);
        jTable.setName("Table Example");

        jTable.setValueAt("Row1-Column1",0,0);
        jTable.setValueAt("Row1-Column2",0,1);
        jTable.setValueAt("Row1-Column3",0,2);

        jTable.setValueAt("Row2-Column1",1,0);
        jTable.setValueAt("Row2-Column2",1,1);
        jTable.setValueAt("Row2-Column3",1,2);

        jTable.setValueAt("Row3-Column1",2,0);
        jTable.setValueAt("Row3-Column2",2,1);
        jTable.setValueAt("Row3-Column3",2,2);

        return new JScrollPane(jTable);
    }


}