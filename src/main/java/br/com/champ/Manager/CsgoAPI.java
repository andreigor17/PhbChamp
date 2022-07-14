/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.champ.Manager;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import uk.oczadly.karl.csgsi.GSIListener;
import uk.oczadly.karl.csgsi.GSIServer;
import uk.oczadly.karl.csgsi.config.GSIConfig;
import uk.oczadly.karl.csgsi.config.GameNotFoundException;

/**
 *
 * @author andre
 */
@ViewScoped
@ManagedBean
public class CsgoAPI implements Serializable {

    public void init() {

    }

    public void startAPI() {
        GSIConfig config = new GSIConfig()
                .setLocalURI(1337) // Server on localhost:1337
                .setTimeoutPeriod(1.0)
                .setBufferPeriod(0.5)
                .withAuthToken("password", "Q79v5tcxVQ8u")
                .withAllDataComponents();  // Or specify which using withDataComponents(...)

        try {
            // Automatically locates the game directory and creates the configuration file
            Path output = config.writeFile("test_service");
            System.out.println("Written config file: " + output);
        } catch (GameNotFoundException e) {
            // Either CSGO or Steam installation directory could not be located
            System.err.println("Couldn't locate CSGO installation: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Couldn't write to configuration file.");
        }
    }

    public void listenAPI() {
        // Create a new listener (using a lambda for this example)
        GSIListener listener = (state, context) -> {
            // Access state information with the 'state' object...
            System.out.println("New state from game client address " + context.getAddress().getHostAddress());

            state.getProvider().ifPresent(provider -> {
                System.out.println("Client SteamID: " + provider.getClientSteamId());
            });
            state.getMap().ifPresent(map -> {
                System.out.println("Current map: " + map.getName());
            });
        };

// Configure server
        GSIServer server = new GSIServer.Builder(1337) // Port 1337, on all network interfaces
                .requireAuthToken("password", "Q79v5tcxVQ8u") // Require the specified password
                .registerListener(listener) // Alternatively, you can call this dynamically on the GSIServer
                .build();

// Start server
        try {
            server.start(); // Start the server (runs in a separate thread)
            System.out.println("Server started. Listening for state data...");
        } catch (IOException e) {
            System.err.println("Couldn't start server.");
        }
    }

}
