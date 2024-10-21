package com.mycompany.filesplit;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Filesplit {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file yang ingin dibaca: ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan ukuran setiap bagian (jumlah baris per bagian): ");
        int partSize = scanner.nextInt();
        
        Queue<String> fileParts = new LinkedList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder part = new StringBuilder();
            int lineCount = 0;
            int partNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                part.append(line).append(System.lineSeparator());
                lineCount++;

                if (lineCount == partSize) {
                    fileParts.add(part.toString());
                    System.out.println("Bagian " + partNumber + " berhasil dibuat.");
                    partNumber++;
                    part.setLength(0);
                    lineCount = 0;
                }
            }
 
            if (lineCount > 0) {
                fileParts.add(part.toString());
                System.out.println("Bagian " + partNumber + " berhasil dibuat.");
            }
            
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
   
        System.out.println("\nIsi Queue (potongan-potongan file):");
        int partIndex = 1;
        while (!fileParts.isEmpty()) {
            System.out.println("Bagian " + partIndex + ":");
            System.out.println(fileParts.poll());
            partIndex++;
        }
        
        scanner.close();
    }
}
    
