package br.com.philippesis.udemydlgui.main;

import br.com.philippesis.udemydlgui.utils.LinuxShell;
import br.com.philippesis.udemydlgui.utils.Loading;
import br.com.philippesis.udemydlgui.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class UdemydlGUI extends JFrame {

    private Point mouseDownCompCoords = null;

    //private JFileChooser fc;

    private Utils utils;

    private String path;

    private Loading loading;

    private LinuxShell linuxShell;

    // Paths images.
    private static final String PATH_ICONS_TITLE_BAR = "/app_icons/title-bar/";
    private static final String PATH_ICONS_MAIN_MENU = "/app_icons/main-menu/";
    private static final String PATH_ICONS_APP = "/app_icons/app/";
    //private static final String PATH_ICONS_CONTENT = "/app_icons/content/";
    //String PATH_ICONS_CONTEXT_MENU = "/app_icons/context-menu/";

    private UdemydlGUI() {
        utils = new Utils();
        path = utils.getAppAssetsPath();
        initComps();

    }

    private void initComps() {

        // Verifica se aplicação udemy-dl.py está presente, caso não, fecha aplicação.
        if(!utils.verifyUdemydlOnBoard()){
            utils.okConfirm("Erro fatal. Não foram encontrados arquivos importantes.\nErr-app01 - Udemy-dl",
                    "ERRO", JOptionPane.ERROR_MESSAGE, UdemydlGUI.this,
                    PATH_ICONS_APP + "icons8-siren-32.png");
            System.exit(1);
        }

        // Main frame
        setSize(430, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        // App Icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(path + PATH_ICONS_APP + "icons8-downloads-48.png"));
        // Title Bar
        JPanel pnlTitleBar = new JPanel();
        pnlTitleBar.setBounds(0, 0, 430, 44);
        pnlTitleBar.setLayout(null);
            // Icon APP title bar
        JLabel appIcon = new JLabel();
            // Icons path
        appIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-downloads-16.png"));
        appIcon.setText("  Udemy-dl GUI for Python. Beta!");
        appIcon.setVerticalTextPosition(SwingConstants.CENTER);
        appIcon.setBounds(10, 10, 260, 16);
            // Icon minimize title bar
        JLabel minimizeIcon = new JLabel();
        minimizeIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-minimize-window-24.png"));
        minimizeIcon.setBounds(367, 10, 24, 24);
            // Icon close title bar
        JLabel closeIcon = new JLabel();
        closeIcon.setIcon(new ImageIcon(path + PATH_ICONS_TITLE_BAR + "icons8-close-window-24.png"));
        closeIcon.setBounds(396, 10, 24, 24);
            // Add comps on title bar
        pnlTitleBar.add(appIcon);
        pnlTitleBar.add(minimizeIcon);
        pnlTitleBar.add(closeIcon);
        // Main Menu
        JPanel pnlMainMenu = new JPanel();
        pnlMainMenu.setBounds(0, 50, 430, 42);
        pnlMainMenu.setLayout(null);
            // Button New
        JLabel newIcon = new JLabel();
        newIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-plus-32.png"));
        newIcon.setBounds(10, 5, 32, 32);
            // Button Open
        JLabel openIcon = new JLabel();
        openIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-opened-folder-32.png"));
        openIcon.setBounds(52,5, 32, 32 );
            // Save Icon
        JLabel saveIcon = new JLabel();
        saveIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-save-32.png"));
        saveIcon.setBounds(94, 5, 32, 32);
            // Downloads Start icon
        JLabel downloadsIcon = new JLabel();
        downloadsIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-downloads-32.png"));
        downloadsIcon.setBounds(136, 5, 32, 32);
            // About icon
        JLabel aboutIcon = new JLabel();
        aboutIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-about-32.png"));
        aboutIcon.setBounds(178, 5, 32, 32);
            // Exit icon
        JLabel exitIcon = new JLabel();
        exitIcon.setIcon(new ImageIcon(path + PATH_ICONS_MAIN_MENU + "icons8-exit-sign-32.png"));
        exitIcon.setBounds(220, 5, 32, 32);
        // Add comps on main menu
        pnlMainMenu.add(newIcon);
        pnlMainMenu.add(openIcon);
        pnlMainMenu.add(saveIcon);
        pnlMainMenu.add(downloadsIcon);
        pnlMainMenu.add(aboutIcon);
        pnlMainMenu.add(exitIcon);
        // Add comps on application
        add(pnlTitleBar);
        add(pnlMainMenu);

        // Eventos
        // Mouse drag
        pnlTitleBar.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
                // System.out.println("Cords: " + e.getPoint());
                // System.out.println("Location: " + e.getLocationOnScreen());
            }
        });
        // Panel onde funcionará o mouseDrag
        pnlTitleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(!utils.verifyLoading(loading)) {
                    Point currCoords = e.getLocationOnScreen();
                    setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
                }
            }
        });
        //Fim mouse drag
        // Mouse click release event icon close
        closeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Verifica se loading está rodando
                if(!utils.verifyLoading(loading)) {
                    // Chama método para fechar aplicação
                    exitApp();
                }
            }
        });
        // Mouse entered event icon close
        closeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeIcon.setToolTipText("Fechar");
            }
        });
        // Mouse click release event icon minimize
        minimizeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Verifica se loading está rodando
                if(!utils.verifyLoading(loading)) {
                    // Minimizar aplicação
                    setState(Frame.ICONIFIED);
                }
            }
        });
        // Mouse entered event icon minimize
        minimizeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                minimizeIcon.setToolTipText("Minimizar");
            }
        });
        // Mouse click release event btn new
        newIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // Verifica se loading não está rodando
//                if(!utils.verifyLoading(loading)) {
//
//                }
            }
        });
        // Mouse entered event btn new
        newIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newIcon.setToolTipText("Novo Esquema de Downloads");
            }
        });
        // Mouse click release event btn open
        // Mouse entered event btn open
        openIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                openIcon.setToolTipText("Abrir Esquema de Downloads");
            }
        });
        // Mouse click release event btn save
        // Mouse entered event btn save
        saveIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                saveIcon.setToolTipText("Salvar Esquema de Downloads");
            }
        });
        // Mouse click release event btn downloads
        downloadsIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!utils.verifyLoading(loading)) {
                    loading = new Loading(UdemydlGUI.this);
                    loading.setVisible(true);
                    linuxShell = new LinuxShell();
                    if (linuxShell.commandoShell("ls")) {
                        loading.dispose();
                        loading = null;
                    }
                }
            }
        });
        // Mouse entered event btn downloads
        downloadsIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                downloadsIcon.setToolTipText("Iniciar Downloads");
            }
        });
        // Mouse click release event btn about
        // Mouse entered event btn about
        aboutIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                aboutIcon.setToolTipText("Sobre Aplicação");
            }
        });
        // Mouse click release event btn exit
        // Mouse entered event btn exit
        exitIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitIcon.setToolTipText("Fechar a Aplicação");
            }
        });
    }

    private void exitApp() {
        Object[] options = { "SIM", "NÃO" };
        if(utils.yesNoConfirm(options, "Deseja Realmente Sair?", "Sair da aplicação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.CANCEL_OPTION, UdemydlGUI.this,
                PATH_ICONS_APP + "icons8-siren-32.png")) {
            //loading.dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            UdemydlGUI udemydlGUI = new UdemydlGUI();
            udemydlGUI.setVisible(true);
        });
    }
}
