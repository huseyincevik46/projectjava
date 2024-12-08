package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUI extends JFrame {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_name;
    private JTextField fld_customer_name;
    private JLabel lbl_type;
    private JComboBox<Customer.TYPE> cmb_customer_type;
    private JLabel lbl_customer_phone;
    private JTextField fld_customer_phone;
    private JLabel lbl_customer_mail;
    private JTextField fld_customer_mail;
    private JLabel lbl_customer_address;
    private JTextArea tarea_customer_address;
    private JButton btn_customer_save;
    private Customer customer;
    private CustomerController customerController;


    public CustomerUI(Customer customer) {
        this.customer=customer;
        this.customerController=new CustomerController();
        this.add(container);
        this.setTitle("Müşteri Ekle/Düzenle");
        this.setSize(300,500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cmb_customer_type.setModel(new javax.swing.DefaultComboBoxModel<>(Customer.TYPE.values()));

        if (this.customer.getId()==0){
            this.lbl_title.setText("Müşteri ekle");

        }else{
            this.lbl_title.setText("Müşteri Düzenle");
            this.lbl_name.setText(customer.getName());
            this.lbl_customer_mail.setText(customer.getMail());
            this.lbl_customer_phone.setText(customer.getPhone());
            this.lbl_customer_address.setText(customer.getAddress());
            this.cmb_customer_type.getModel().setSelectedItem(customer.getType());

        }

        this.btn_customer_save.addActionListener(actionEvent -> {
            JTextField[] checList={this.fld_customer_name,this.fld_customer_phone};
            if(Helper.isFieldListEmpty(checList)){
                Helper.showMsg("fill");
            }else if(!Helper.isFieldEmpty(this.fld_customer_mail) && !Helper.isEmailValid(this.fld_customer_mail.getText())){
                Helper.showMsg("lütfen geçerli bir e posta ");

            }else{

            }

        });
    }
}
