package com.mycompany.virtualword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VirtualWord {
    
    static class Palavra {
        String texto;
        int silabas;
        
        Palavra(String texto, int silabas) {
            this.texto = texto;
            this.silabas = silabas;
        }
        
        @Override
        public String toString() {
            return texto + "(" + silabas +")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Portugues");
        System.out.println("2. English");
        System.out.print("Digite sua escolha / Enter your choice: ");

        int languageChoice = scanner.nextInt();
        scanner.nextLine();  // Limpa a linha de entrada
        
        String 
            messageFilePath, 
            messageFileFound, 
            messageLineRead, 
            messageFileEmpty, 
            messageErrorReadingFile, 
            messageWordsRead
        ;
        
        // Configura as mensagens de acordo com o idioma escolhido
        if (languageChoice == 1) { // Português
            messageFilePath = "Digite o caminho do arquivo: ";
            messageFileFound = "Arquivo encontrado! Iniciando leitura...";
            messageLineRead = "Linha lida do arquivo: ";
            messageFileEmpty = "O arquivo está vazio.";
            messageErrorReadingFile = "Erro ao ler o arquivo: ";
            messageWordsRead = "Palavras Lidas: ";
        } else { // Inglês como padrão
            messageFilePath = "Enter the file path: ";
            messageFileFound = "File found! Starting reading...";
            messageLineRead = "Line read from file";
            messageFileEmpty = "The file is empty.";
            messageErrorReadingFile = "Error reading file: ";
            messageWordsRead = "Words Read: ";
        }

        System.out.print(messageFilePath);
        String filePath = scanner.nextLine();

        scanner.close();

        List<Palavra> palavras = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            System.out.println(messageFileFound);

            String line = reader.readLine();
            if (line != null) {
                System.out.println(messageLineRead + line);

                String[] words = line.split(",");
                for (String wordEntry : words) {
                    wordEntry = wordEntry.trim();
                    
                    if (wordEntry.matches(".+\\(\\d+\\)")) {
                        int openParentIndex = wordEntry.indexOf("(");
                        int closeParentIndex = wordEntry.indexOf(")");
                        
                        String wordText = wordEntry.substring(0, openParentIndex).trim();
                        int syllableCount = Integer.parseInt(wordEntry.substring(openParentIndex + 1, closeParentIndex));
                        
                        palavras.add(new Palavra(wordText, syllableCount));
                    }
                    else {
                        palavras.add(new Palavra(wordEntry, -1));
                    }
                }
            } else {
                System.out.println(messageFileEmpty);
            }

            System.out.println(messageWordsRead);
            for (Palavra palavra : palavras) {
                if (palavra.silabas == -1) {
                    System.out.println(palavra.texto + "(sílabas não fornecidas)");
                }
                else {
                    System.out.println(palavra);
                }
            }
        } 
        catch (IOException e) {
            System.out.println(messageErrorReadingFile + e.getMessage());
        }

    }
}
