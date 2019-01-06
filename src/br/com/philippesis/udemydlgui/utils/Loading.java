package br.com.philippesis.udemydlgui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Loading extends JFrame {

    private JFrame frame;

    private Utils utils;

    private String path;

    private static final String PATH_ICONS_APP = "/app_icons/app/";

    private static final String PATH_ICONS_COXTENT = "/app_icons/context/";

    public Loading(JFrame frame) {
        utils = new Utils();
        path = utils.getAppAssetsPath();
        this.frame = frame;
        initComps();
    }
    private void initComps() {

        // Main Frame
        setSize(250, 70);
        setLocationRelativeTo(frame);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JDialog dialog = new JDialog ();
        dialog.setModal (true);
        dialog.setAlwaysOnTop (true);
        dialog.setModalityType (Dialog.ModalityType.APPLICATION_MODAL);
        setAlwaysOnTop (true);
        // Loading gif
        JLabel gifLoading = new JLabel();
        gifLoading.setText("  Aguarde...");
        gifLoading.setFont(new Font(gifLoading.getText(), Font.PLAIN, 18));
        gifLoading.setIcon(new ImageIcon(path + PATH_ICONS_COXTENT + "carregando-48.gif"));
        gifLoading.setBounds(20, 10, 160, 48);
        // Cancel button
        JLabel btnCancel = new JLabel();
        btnCancel.setIcon(new ImageIcon(path + PATH_ICONS_COXTENT + "icons8-private-48.png"));
        btnCancel.setBounds(190, 10, 48, 48);
        // ADD comps
        add(gifLoading);
        add(btnCancel);
        // Events
        // Mouse releases event btn cancel
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Object[] options = { "SIM", "NÃO" };
                if (utils.yesNoConfirm(options, "Deseja Realmente Cancelar o Downloads?", "CANCNELAR",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, Loading.this,PATH_ICONS_APP +
                                "icons8-siren-32.png")) {
                    cancel();
                }
            }
        });
        // Mouse entered evetn btn cancel
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCancel.setToolTipText("Cancelar Downloads");
            }
        });
    }

    public void cancel() {
        this.dispose();
    }

}
