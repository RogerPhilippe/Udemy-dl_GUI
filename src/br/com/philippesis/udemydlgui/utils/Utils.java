package br.com.philippesis.udemydlgui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.awt.Image.SCALE_SMOOTH;

public class Utils {

    private String path;

    public Utils() {

    }

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
                new ImageIcon(path+icon), options, options[0]);
        return valueJOptionPane == 0;
    }

    public Image getImage(String path, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (Exception er) { er.printStackTrace(); }
        return Objects.requireNonNull(img).getScaledInstance(width, height, SCALE_SMOOTH);
    }

}
