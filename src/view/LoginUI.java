package view;

import business.UserController;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final UserController userController;

    public LoginUI() {
        this.userController = new UserController();

        this.add(container);
        this.setTitle("Login");
        this.setSize(400, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // login bottom function
        this.btn_login.addActionListener(e -> {

                JTextField[] checklist ={this.fld_mail,this.pfld_pass};

                // Helper class isEmailValid check
                if(!Helper.isEmailValid(this.fld_mail.getText())){
                  Helper.showMsg("Enter a valid email");

                    //Helper class empty check
                }else if (Helper.isFieldListEmpty(checklist)){
                   Helper.showMsg("fill");
                }else{
                    User user = this.userController.findByLogin(this.fld_mail.getText(),this.pfld_pass.getText());
                    if(user == null){
                        Helper.showMsg("Kullanıcı bulunamadı!");

                    }else{
                       dispose();
                       DashbordUI dashbordUI = new DashbordUI(user);
                    }

                }


        });
    }
}
