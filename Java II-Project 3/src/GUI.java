/*
 * File:    GUI.java
 * Author:  Anthony Smith
 * Date:    2 December 2018
 * Purpose: Builds a GUI and implements functionality to compute a given number of a sequence
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame{
    //declare all variables and GUI components
    private ButtonGroup radioButtons;
    private JRadioButton iterativeButton;
    private JRadioButton recursiveButton;
    private JTextField inputField;
    private JTextField resultField;
    private JTextField efficiencyField;
    private JLabel resultLabel;
    private JLabel enterNLabel;
    private JLabel efficiencyLabel;
    private long input;
    private JButton blankButton;
    private JButton computeButton;
    private JOptionPane window;
    //array list to store logs
    private ArrayList<String> valuesList = new ArrayList<>();
    private static FileWriter writeFile;
    private File values = new File ("values.csv");

    //GUI method to create the GUI
    public GUI(){
        super("Project 3");
        setLayout(new GridLayout(5,2));
        Handle handler = new Handle();
        iterativeButton = new JRadioButton("Iterative");
        add(iterativeButton);

        recursiveButton = new JRadioButton("Recursive");
        add(recursiveButton);

        radioButtons = new ButtonGroup();
        radioButtons.add(iterativeButton);
        radioButtons.add(recursiveButton);

        enterNLabel = new JLabel("Enter n:");
        add(enterNLabel);

        inputField = new JTextField(15);
        add(inputField);

        //blank button for spacing issue
        blankButton = new JButton();
        add(blankButton);

        computeButton = new JButton("Compute");
        add(computeButton);
        //action listener for compute button
        computeButton.addActionListener(handler);

        resultLabel = new JLabel("Result:");
        add(resultLabel);

        resultField = new JTextField(15);
        add(resultField);

        efficiencyLabel = new JLabel("Efficiency");
        add(efficiencyLabel);

        efficiencyField = new JTextField(15);
        add(efficiencyField);

    }
    //method to clear input field
    public void clearField(){
        efficiencyField.setText("");
    }

    //method to get input from the user and check it against number format exception
    public long getInput(){
        try{
            input = Long.parseLong(inputField.getText());
            return input;
        }catch(NumberFormatException e){
            System.out.println("number format exception caught in getInput");
            JOptionPane.showMessageDialog(window, "enter a valid number.");
            clearField();
        }
        return 0;
    }

    //action listener for compute button
   private class Handle implements ActionListener{
       public void actionPerformed(ActionEvent e){
           //calculates the nth space in the sequence and out puts it to the user using the iterative method
           if(iterativeButton.isSelected()){
               resultField.setText(String.valueOf(Sequence.computeIterative(getInput())));
               efficiencyField.setText(String.valueOf(Sequence.getEfficiency()));
               //records n and the efficiency to the arraylist and writes to the csv file
               recordToArrayList("Iterative");
               writeToFile();
              //calculates the nth space in the sequence and out puts it to the user using the recursive method
           }else if (recursiveButton.isSelected()){
               resultField.setText(String.valueOf(Sequence.computeRecursive(getInput())));
               efficiencyField.setText(String.valueOf(Sequence.getEfficiency()));
             //records n and the efficiency to the arraylist and writes to the csv file
               recordToArrayList("Recursive");
               writeToFile();
           }
       }
   }

   //method adds n and efficiency to ArrayList
    public void recordToArrayList(String methodSelected)
    {
        valuesList.add(methodSelected + ", " + Sequence.getEfficiency() + ", " + input);
    }

    //method uses the ArrayList to write to the .csv file
    public void writeToFile()
    {

        try{

            writeFile = new FileWriter(values);
            writeFile.write("type, efficiency, n\n");
            for(String l: valuesList)
            {
                writeFile.write(l + "\n");
            }writeFile.close();
        }catch(IOException e){
            e.getMessage();
        }
    }

    //main method
    public static void main(String [] args){
        GUI frm = new GUI();
        frm.setSize(300, 250);
        frm.setVisible( true );
        Sequence n = new Sequence();

    }
}
