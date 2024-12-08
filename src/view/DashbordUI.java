package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DashbordUI  extends JFrame {
    private JPanel container;
    private JLabel lbl_welcome;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JPanel pnl_customer_filter;
    private JTextField fld_f_customer_name;
    private JComboBox cmb_customer_type;
    private JButton btn_customer_filter;
    private JButton btn_customer_reset;
    private JButton btn_customer_new;
    private JLabel lbl_f_customer_name;
    private JLabel lbl_f_customer_type;
    private User user;
    private CustomerController customerController;
    private DefaultTableModel tmdl_customer = new DefaultTableModel();
    private JPopupMenu popup_customer = new JPopupMenu();
    public DashbordUI(User user ) {
        this.user = user;
        this.customerController =new CustomerController();
        if (user == null) {
            Helper.showMsg("error");
            dispose();
        }

        this.add(container);
        this.setTitle("Müşteri yönetim sitemi");
        this.setSize(1000,500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       this.lbl_welcome.setText("Welcome: " + this.user.getName());
        this.btn_logout.addActionListener(e -> {
          dispose();
            new LoginUI();
        });


         loadCustomerTable(null);
         loadCustomerPopupMenu();
         loadCustomerButtonEvent();


        btn_customer_filter.addActionListener(actionEvent -> loadCustomerTable(null));



    }
    private void loadCustomerButtonEvent(){
        this.btn_customer_new.addActionListener(actionEvent -> {
            CustomerUI customerUI = new CustomerUI(new Customer());
        });
    }

    private void loadCustomerPopupMenu() {
        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            // satır tıklanma seçilimi
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
                tbl_customer.setRowSelectionInterval(selectedRow, selectedRow);
            }
        }

        );
        this.popup_customer.add("güncele").addActionListener(e ->{
            int selectId = Integer.parseInt(tbl_customer.getValueAt(tbl_customer.getSelectedRow(), 0).toString());
        });
        this.popup_customer.add("Sil").addActionListener(e->{
            System.out.println("Sil tıklandı");
        });

        this.tbl_customer.setComponentPopupMenu(this.popup_customer);

    }
    private  void loadCustomerTable(ArrayList<Customer> customers) {
        Object [] columCustomer = {"ID","Müşteri Adı","Tipi","Telefon","E-posta","Adres"};

        if(customers ==null){
            customers = this.customerController.findAll();
        }
        // table sıfırlama
        DefaultTableModel clearmodel= (DefaultTableModel) this.tbl_customer.getModel();
        clearmodel.setRowCount(0);

// başlıkları hazrılama
        this.tmdl_customer.setColumnIdentifiers(columCustomer);

        for(Customer customer :customers){
            Object[] rowObject={
                    customer.getId(),
                    customer.getName(),
                    customer.getType(),
                    customer.getPhone(),
                    customer.getAddress()
            };
            this.tmdl_customer.addRow(rowObject);
        }
        // table düzenleleri
        this.tbl_customer.setModel(this.tmdl_customer);
        this.tbl_customer.getTableHeader().setReorderingAllowed(false);
        this.tbl_customer.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.tbl_customer.setEnabled(false);
    }
}
