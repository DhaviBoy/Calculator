import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;





public class Calculator {
    // UI Dimensions
    int boardWidth = 360;
    int boardHeight = 480;

    // Colors
    Color customLightGray = new Color(212, 212, 212);
    Color customDarkGray = new Color(80, 80, 80);
    Color customOrange = new Color(255, 149, 0);
    Color customBlack = new Color(20, 20, 20);

    // Button and Display Setup
     String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");
    JLabel dispLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    // Calculation Variables
    String A = "0";
    String operator = null;
    String B = null;

    // Constructor - Setup UI
    Calculator() {
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        dispLabel.setBackground(customBlack);
        dispLabel.setForeground(Color.WHITE);
        dispLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        dispLabel.setHorizontalAlignment(JLabel.RIGHT);
        dispLabel.setOpaque(true);
        dispLabel.setText("0");

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(dispLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5, 4));
        buttonPanel.setBackground(customBlack);
        frame.add(buttonPanel);

        for (int i = 0; i < buttonValues.length; i++) {
          JButton button = new JButton();
          String buttonValue = buttonValues[i];
          button.setFont(new Font ("Arial", Font.PLAIN, 30));
          button.setText(buttonValue);
          button.setFocusable(false);
          button.setBorder(new LineBorder(customBlack));
          if (Arrays.asList(topSymbols).contains(buttonValue)) {
              button.setBackground(customLightGray);
              button.setForeground(customBlack);
          } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
              button.setBackground(customOrange);
              button.setForeground(Color.WHITE);
          } else {
              button.setBackground(customDarkGray);
              button.setForeground(Color.WHITE);
          }
          buttonPanel.add(button);

          // Button Click Event Handler
          button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonValue = button.getText();
                if(Arrays.asList(rightSymbols).contains(buttonValue)){
                    if (buttonValue.equals("=")){
                        if (A != null){
                            B = dispLabel.getText();
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(B);

                            if (operator.equals("+")) {
                                
                                dispLabel.setText(removeZeroDecimal(numA + numB));
                            } else if (operator.equals("-")) {
                                
                                dispLabel.setText(removeZeroDecimal(numA - numB));
                            } else if (operator.equals("×")) {
                                
                                dispLabel.setText(removeZeroDecimal(numA * numB));
                            } else if (operator.equals("÷")) {
                                    dispLabel.setText(removeZeroDecimal(numA / numB));
                              
                            }
                                clearAll();
                        }


                    }
                    else if ("÷×-+".contains(buttonValue)) {
                        if (operator == null){
                            A = dispLabel.getText();
                            operator = buttonValue;
                            dispLabel.setText("0");
                            B = "0";
                        }

                    }
                    
                } else if (Arrays.asList(topSymbols).contains(buttonValue)){
                    if (buttonValue.equals("AC")) {
                        clearAll();
                        dispLabel.setText("0");
                    } 
                    else if  (buttonValue.equals("+/-")) {
                        double numDisplay = Double.parseDouble(dispLabel.getText());
                        numDisplay *= -1;
                        dispLabel.setText(removeZeroDecimal(numDisplay));
                    }
                    else if (buttonValue.equals("%")) {
                        double numDisplay = Double.parseDouble(dispLabel.getText());
                        numDisplay /= 100;
                        dispLabel.setText(removeZeroDecimal(numDisplay));
                    }
                    
                } else {
                    if(buttonValue.equals(".")) {
                        if (!dispLabel.getText().contains(buttonValue)) {
                            dispLabel.setText(dispLabel.getText() + buttonValue);
                        }
                    }
                    else if ("0123456789".contains(buttonValue)) {
                        if (dispLabel.getText().equals("0")) {
                            dispLabel.setText(buttonValue);
                        } else {
                            dispLabel.setText(dispLabel.getText() + buttonValue);
                        }
                    }
                }
            }
          });
          frame.setVisible(true);
        }


    }

    // Utility Methods
    void clearAll() {
        A = "0";
        operator = null;
        B = null;
        
    }

    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
