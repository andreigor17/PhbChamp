/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.champ.Manager;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class ManagerServer implements Serializable {

    private static final Logger log = Logger.getLogger(ManagerServer.class.getName());
    private List<String> returns;

    @PostConstruct
    public void init() {
        instanciar();
        try {
            this.returns = executeShellHDSize();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void instanciar() {
        this.returns = new ArrayList<>();
    }

    public List<String> executeCommand(final String command) throws IOException {

        final ArrayList<String> commands = new ArrayList<String>();
        List<String> returns = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);

        BufferedReader br = null;

        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("disk1s1")) {
                    System.out.println("Retorno do comando = [" + line + "]");
                    returns.add(line);
                }
            }
        } catch (IOException ioe) {
            log.severe("Erro ao executar comando shell" + ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
        return returns;
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }

    public List<String> executeShellHDSize() throws IOException {
        List<String> returns = new ArrayList<>();
        returns = executeCommand("df -h");
        return returns;
    }

    public List<String> getReturns() {
        return returns;
    }

    public void setReturns(List<String> returns) {
        this.returns = returns;
    }

}
