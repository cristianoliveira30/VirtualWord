package com.mycompany.virtualword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author crist
 */
public class VirtualWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o caminho do arquivo: ");
        String filePath = scanner.nextLine();

        scanner.close();

        List<String> keywords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line = reader.readLine();
            if (line != null) {
                String[] words = line.split(",");
                for (String word : words) {
                    keywords.add(word.trim());
                }
            }
            System.out.println("Palavras Lidas: ");
            for (String keyword : keywords) 
            {
                System.out.println(keyword);
            }
        }
        catch (IOException e)
        {
            System.out.println("Erro ao ler arquivo" + e.getMessage());
        }
    }
}
