package com.mycompany.virtualword;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VirtualWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o caminho do arquivo: ");
        String filePath = scanner.nextLine();

        scanner.close();

        List<String> keywords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            System.out.println("Arquivo encontrado! Iniciando leitura...");

            String line = reader.readLine();
            if (line != null) {
                System.out.println("Linha lida do arquivo: " + line);

                String[] words = line.split(",");
                for (String word : words) {
                    keywords.add(word.trim());
                }
            } else {
                System.out.println("O arquivo está vazio.");
            }

            System.out.println("Palavras Lidas: ");
            for (String keyword : keywords) {
                System.out.println(keyword);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
