package view;

import core.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;

public class LoginUI  extends JFrame {
    private JPanel container;
    private JPanel panel_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JTextField fld_mail;
    private JLabel lbl_mail;
    private JLabel lbl_pass;
    private JPasswordField pfld_pass;
    private JButton btn_login;

    public LoginUI() {
        this.add(container);
        this.setTitle("Login");
        this.setSize(400, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // login bottom function
        this.btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JTextField [] checlist ={fld_mail,pfld_pass};

                // Helper class isEmailValid check
                if(!Helper.isEmailValid(fld_mail.getText())){
                  Helper.showMsg("Enter a valid email");

                    //Helper class empty check
                }else if(Helper.isFieldListEmpty(checlist)){
                   Helper.showMsg("fill");
                }else{

                }

            }
        });
    }
}
