package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String stringaRicevuta = in.readLine();
            System.out.println("-----Stringa ricevuta!-----\n");

            ObjectMapper objectMapper = new ObjectMapper();
            Classe c2 = objectMapper.readValue(stringaRicevuta, Classe.class);

            System.out.println("Numero classe: " + c2.getNumero());
            System.out.println("Sezione classe: " + c2.getSezione());
            System.out.println("Aula classe: " + c2.getAula() + "\n");

            for (int i = 0; i < c2.getAlunni().size(); i++) {
                System.out.println( "Nome alunno " + (i + 1) + ": " + c2.getAlunni().get(i).getNome() + "\n" + 
                                    "Cognome alunno " + (i + 1) + ": " + c2.getAlunni().get(i).getCognome() + "\n" + 
                                    "Data nascita alunno " + (i + 1) + ": " + c2.getAlunni().get(i).getDataNascita() + "\n\n");
            }
            s.close();
            System.out.println("-----Chiusura Client-----");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Qualcosa e' andato storto");
            System.exit(1);
        }
    }
}