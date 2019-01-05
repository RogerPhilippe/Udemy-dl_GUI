package br.com.philippesis.udemydlgui.utils;

import br.com.philippesis.udemydlgui.model.UdemydlGUIModel;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.Properties;

public class OpenSavedScheme {

    public UdemydlGUIModel lerProp(String locationFile) {
        Properties prop = getProp(locationFile);
        //String version = String.valueOf(prop.getProperty("Version"));
        String url = String.valueOf(prop.getProperty("url"));
        String user = String.valueOf(prop.getProperty("user"));
        //String password = String.valueOf(prop.getProperty(""));
        String downloadsPath = String.valueOf(prop.getProperty("downloadsPath"));
        return new UdemydlGUIModel.Builder()
                .setUrl(url)
                .setUser(user)
                .setDownloadsPath(downloadsPath)
                .build();
    }
    private static Properties getProp(String locationFile) {
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream(locationFile);
            props.load(file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "E04. " + e);
        }
        return props;
    }

}
