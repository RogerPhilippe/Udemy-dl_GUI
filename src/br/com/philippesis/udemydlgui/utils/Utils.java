package br.com.philippesis.udemydlgui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static java.awt.Image.SCALE_SMOOTH;

public class Utils {

    private String path;

    private static final String PATH_UDEMY_DL = "/udemy-dl/";

    public String getAppAssetsPath() {

        //Atualizar caminho aplicação
        try {
            path = new File("assets/").getCanonicalPath();
            //System.out.println("Caminho da aplicação: "+path);
            return path;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public boolean yesNoConfirm(Object[] options, String msg, String title, int optionType, int messageType,
                                Component parent, String icon) {
        path = getAppAssetsPath();
        int valueJOptionPane = JOptionPane.showOptionDialog(parent, msg, title, optionType, messageType,
                new ImageIcon(path + icon), options, options[0]);
        return valueJOptionPane == 0;
    }

    public void okConfirm(String msg, String title, int messageType, Component parent, String icon) {
        JOptionPane.showMessageDialog(parent, msg, title, messageType, new ImageIcon(path + icon));
    }

    public Image getImage(String path, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception er) { er.printStackTrace(); }
        return Objects.requireNonNull(img).getScaledInstance(width, height, SCALE_SMOOTH);
    }

    public boolean verifyUdemydlOnBoard(){
        File udemy_dl_py = new File(path + PATH_UDEMY_DL + "udemy-dl.py");
        return udemy_dl_py.exists();
    }

    public boolean verifyLoading(JFrame frame) {
        boolean statusFrame = false;
        // Verifique se um frame (no caso, foi criado para loading) esteja em execução.
        if(frame != null) {
            try {
                statusFrame = frame.getLocationOnScreen() != null;
            }
            // A exception IllegalCompo... só ocorrerá uma única vez, poís será gerada caso frame não seja null,
            //  mas não esteja mais executando. Nesse caso, itemos destruir o frame.
            catch (IllegalComponentStateException er) { frame.dispose(); }
            // Exception qualquer.
            catch (Exception er) { er.printStackTrace(); }
        }
        return statusFrame;
    }

    public String getDateHour() {
        return new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public String getDateHourForLog() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

}
