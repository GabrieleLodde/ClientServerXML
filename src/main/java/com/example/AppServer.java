package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppServer {
    public static void main(String[] args) {
        try {
            System.out.println("Server avviato...");
            ServerSocket server = new ServerSocket(3000);

            Socket s = server.accept();
            System.out.println("Un client si è connesso");

            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Alunno a = new Alunno("Pippo", "Rossi", new Date(2006, 07, 12));
            Alunno b = new Alunno("Gioele", "Febbre", new Date(2005, 12, 03));
            Alunno c = new Alunno("DJ", "Yang", new Date(2003, 06, 12));
            Alunno d = new Alunno("Gabriele", "Lodde", new Date(2005, 06, 16));

            ArrayList<Alunno> arrayAlunni = new ArrayList<Alunno>();
            arrayAlunni.add(a);
            arrayAlunni.add(b);
            arrayAlunni.add(c);
            arrayAlunni.add(d);

            Classe c1 = new Classe(5, "BIA", "18-TW", arrayAlunni);

            ObjectMapper objectMapper = new ObjectMapper();
            String stringaInviata = objectMapper.writeValueAsString(c1);

            out.writeBytes(stringaInviata + "\n");
            System.out.println("---Serializzazione effettuata!----");

            s.close();
            server.close();
            System.out.println("-----Chiusura Server-----");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }
}