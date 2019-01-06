package br.com.philippesis.udemydlgui.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LinuxShell {

    // Metodo comando shell (Linux/Unix)
    public boolean commandoShell(String command) {

        boolean statusComando = false;

        System.out.println("Comando: " + command);
        if (command != null) {
            ArrayList<String> commands = new ArrayList<>();
            commands.add("/bin/bash");
            commands.add("-c");
            commands.add(command);

            BufferedReader br;
            try {
                ProcessBuilder p = new ProcessBuilder(commands);
                Process process = p.start();
                InputStream is = process.getInputStream();
                InputStreamReader isR = new InputStreamReader(is);
                br = new BufferedReader(isR);
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Retorno do comando = [" + line + "]");
                    //comTerminal(line);
                }
                //comTerminal("Finalizado.");
                System.out.println("Finalizado.");
                statusComando = true;
            } catch (Exception e) {
                System.out.println("Erro ao tentar executar comando shell!");
            }
        }

        return statusComando;
    }

}
