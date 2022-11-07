package salaried;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class taxCalculator extends JDialog {
//    JFrame frame = new JFrame("Tax Calculator");
//
//    JPanel pan = new JPanel();
    private JPanel pan;
    private JTextField txt_ma;
    private JTextField txt_hr;
    private JTextField txt_bs;
    private JTextField txt_con;
    private JTextField txt_fb;
    private JTextField txt_ot;
    private JLabel lbl_bs;
    private JLabel lbl_hr;
    private JLabel lbl_ma;
    private JLabel lbl_con;
    private JLabel lbl_ot;
    private JPanel taxPan;
    private JLabel lbl_total;
    private JLabel lbl_fb;
    private JLabel txt_total;
    private JButton btn_calculate;
    private JButton btn_reset;
    private void createUI(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(pan);
        pack();
        setVisible(true);
        //set window size
        setSize(500, 500);

    }


    private void calculate(){
        double maxExemptionHR = 250000;
        double maxExemptionMA = 50000;
        double maxExemptionCON = 30000;
        //check if the text fields are empty
        if(txt_ma.getText().isEmpty() || txt_hr.getText().isEmpty() || txt_bs.getText().isEmpty() || txt_con.getText().isEmpty() || txt_fb.getText().isEmpty() || txt_ot.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
        }else{
            // check if the text fields are numbers
            try{
                double ma = Double.parseDouble(txt_ma.getText());
                double hr = Double.parseDouble(txt_hr.getText());
                double bs = Double.parseDouble(txt_bs.getText());
                double con = Double.parseDouble(txt_con.getText());
                double fb = Double.parseDouble(txt_fb.getText());
                double ot = Double.parseDouble(txt_ot.getText());
                double newCon = 0;
                double newMa = 0;
                double newHr = 0;

                //check if the values are greater than the maximum exemption
                if(ma > maxExemptionMA){
                     newMa = ma-maxExemptionMA;
                     ma=0;
                    System.out.println("newMa "+newMa);
                }
                if(hr > maxExemptionHR){
                     newHr = hr-maxExemptionHR;
                        hr=0;

                    System.out.println( "newCon "+ newCon);
                }
                if(con > maxExemptionCON){
                     newCon = con-maxExemptionCON;
                     System.out.println( "newCon "+ newCon);
                     con=0;
                }
                //taxable income calculation
                System.out.println("hi ma "+ma);
                System.out.println("hr "+hr);
                System.out.println("con "+con);
                System.out.println("bs "+bs);
                System.out.println("ot "+ot);
                System.out.println("fb "+fb);
                System.out.println( "newHR "+ newHr);
                System.out.println( "newCon "+ newCon);
                System.out.println("newMa "+newMa);

                //taxable income after deductions
                double taxableIncomeAfterDeductions = bs + fb + ot + newCon + newMa + newHr;
                System.out.println( "taxableIncomeAfterDeductions   "+taxableIncomeAfterDeductions);
                //tax calculation
                double tax1 = 0;
                double tax2 = 0;
                double tax3 = 0;
                double tax4 = 0;
                double tax5 = 0;
                double taxableAmount = 0;
                if (taxableIncomeAfterDeductions>300000){
                    taxableAmount = taxableIncomeAfterDeductions - 300000;
                    if(taxableAmount>100000){
                        tax1 = 100000*0.05;
                        taxableAmount = taxableAmount - 100000;
                        if(taxableAmount>300000){
                            tax2 = 300000*0.1;
                            taxableAmount = taxableAmount - 400000;
                            if(taxableAmount>400000){
                                tax3 = 400000*0.15;
                                taxableAmount = taxableAmount - 400000;
                                if(taxableAmount>500000){
                                    tax4 = 500000*0.2;
                                    taxableAmount = taxableAmount - 500000;
                                    if(taxableAmount>100000){
                                        tax5 = 100000*0.25;
                                        taxableAmount = taxableAmount - 100000;
                                    }else{
                                        tax5 = taxableAmount*0.25;
                                    }
                                }else{
                                    tax4 = taxableAmount*0.2;
                                }
                            }else{
                                tax3 = taxableAmount*0.15;
                            }
                        }else{
                            System.out.println( "taxableAmount   "+taxableAmount);
                            tax2 = taxableAmount*0.1;
                        }
                }else {
                        tax1 = taxableAmount*0.05;
                    }
                }

                double totalTax = tax1 + tax2 + tax3 + tax4 + tax5  ;


                //calculate the total
                txt_total.setText(String.valueOf(totalTax));
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter numbers only");
                System.out.println(e);
            }
        }
    }
    private void reset(){
        txt_bs.setText("");
        txt_hr.setText("");
        txt_ma.setText("");
        txt_con.setText("");
        txt_ot.setText("");
        txt_fb.setText("");
        txt_total.setText("0");
    }

    public taxCalculator(JFrame parent) {
        super(parent);
        setTitle("Tax Calculator");
        setContentPane(taxPan);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btn_calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        setVisible(true);

    }


    public static void main(String[] args) {
        taxCalculator tax = new taxCalculator(null);
    }
}
