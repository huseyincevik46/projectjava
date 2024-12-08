package core;

import javax.swing.*;

public class Helper {
    // helper (email valuemin,email controller,field controller)

    public static void setTheme(){
        // install ready theme
        UIManager.getInstalledLookAndFeels();

        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if(info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    // Is the text field an empty controller?
    // tek bir area control eder.
    public static boolean isFieldEmpty(JTextField field){

        return field.getText().trim().isEmpty();
    }

    //formları kontrol etmek amacıyla(birden fazla alanı kontrol eder)
    public static boolean isFieldListEmpty(JTextField[] fields){
        for(JTextField field : fields){
            if(isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static boolean isEmailValid(String mail){
            //  @ olucak, @ bundan önce bir
        //  +
        //  değer alması lazım, .com vs uzantıları olması lazım
        if(mail == null || mail.trim().isEmpty()) return true;

             // @ kontrolü
        if(!mail.contains("@")) return false;

            // emaili ikiye ayırarak kontrol sağlama
        String[] parts=mail.split("@");

            // ikiye ayrılmadıysa false
        if(parts.length!=2) return false;

            // 1. parça boş ve 2.parça boş ise false
        if(parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) return false;

            // 2.parçada nokta yoksa false
        if(!parts[1].contains(".")) return false;

        return true;

//        final String EMAIL_REGEX="^[A-Za-z0-9+_.-]+@+.[A-Za-z0-9.-]+$";
//        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
//        Matcher matcher = emailPattern.matcher(email);
//        return matcher.matches();
//
//        for (String email : email) {
//            if (isValidEmail(email)) {
//                System.out.println(email + " geçerli bir e-posta adresidir.");
//            } else {
//                System.out.println(email + " geçersiz bir e-posta adresidir.");
//            }
//        }
    }

    public static void optionPaneDialogTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }

    public static void showMsg(String message){
        String msg;
        String title;
        optionPaneDialogTR();

         switch (message) {
            case "fill" -> {
                msg = "lütfen bir alan doldurunuz";
                title ="Hata";
            }
            case "done" -> {
                msg = "işlem başarılı! ";
                title ="Sonuç";
            }
            case "error" -> {
                msg = "bir hata oluştu !";
                title ="Hata";
            }
            default -> {
                msg = message;
                title ="Hata";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title , JOptionPane.INFORMATION_MESSAGE);


    }


}
