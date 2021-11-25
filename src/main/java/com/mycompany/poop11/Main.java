package com.mycompany.poop11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author brandon
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("--------------- [ File ] ---------------");
        File archivo = new File("archivo.txt");
        System.out.println(archivo.exists());
        if(!archivo.exists()){
            try {
                boolean seCreo = archivo.createNewFile();
                System.out.println(seCreo);
                System.out.println(archivo.exists());
            } catch (IOException ex) {
                ex.getMessage();
                ex.getStackTrace();
            }
        }
        
        System.out.println("--------------- [ FileOutputsStreams ] ---------------");
        FileOutputStream fos = null;
        byte[] buffer = new byte[81];
        int nBytes;
        
        try {
           System.out.println("Escribe el texto a guardar en el archivo: ");
            nBytes = System.in.read(buffer);
            fos = new FileOutputStream("archivoFOS.txt");
            fos.write(buffer, 0, nBytes);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }finally{ 
            try {
                if(fos != null)
                fos.close();
            } catch (IOException ex) {
            }
        
        }
        
        System.out.println("--------------- [ FileINputsStreams ] ---------------");
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream("archivoFOS.txt");
            nBytes = fis.read(buffer, 0, 81);
            String texto = new String(buffer,0,nBytes);
            System.out.println(texto);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                if(fis != null)
                fis.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println("--------------- [ FileWrite ] ---------------");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba texto para el archivo");
            String texto2 = br.readLine();
            
            FileWriter fw = new FileWriter("archivoFileWrite.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            
            salida.println(texto2);
            for(int i = 0; i < 10; i++){
                salida.println(i + " linea del for");
            }for(int i = 0; i < 5; i++){
                salida.println("Brandon,Andrade,Pinto,317087889,15,02,2001");
            }
            
            salida.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("--------------- [ FileReader ] ---------------");
        try {
            BufferedReader br;
            FileReader fr = new FileReader("archivoFileWrite.txt");
            br = new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea = br.readLine();
            while(linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("--------------- [ StringTokenizer ] ---------------");
        
        String linea = "Ramiro, Juarez, Perez, 219354263, 21, 22";
        StringTokenizer tokenizer = new StringTokenizer(linea,",");
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        
        System.out.println("--------------- [ Serializacion ] ---------------");
        
        Date date = new Date();
        System.out.println(date);
        try{
            FileOutputStream f = new FileOutputStream("fecha.ser");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(date);
            oos.close();
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        
        System.out.println("--------------- [ Deserializacion ] ---------------");
        
        try{
            TimeUnit.SECONDS.sleep(10);
            
            Date date2 = new Date();
            System.out.println("La fecha actulizada es: " + date2);
            
            FileInputStream f = new FileInputStream("fecha.ser");
            ObjectInputStream ois = new ObjectInputStream(f);
            Date date3 = (Date) ois.readObject();
            ois.close();
            System.out.println("Objeto deserializado: " + date3);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println("\"--------------- [ Actividad Extra ] ---------------\"");
        System.out.println("\"--------------- [ FileReader ] ---------------\"");//utilizar el tokenizer
        try {
            BufferedReader br;
            FileReader fr = new FileReader("archivoFileWrite.txt");
            br = new BufferedReader(fr);
            System.out.println("El texto de el archivo es: ");
            linea = br.readLine();
            
            while (linea != null) {
                StringTokenizer token = new StringTokenizer(linea, ",");
                while (token.hasMoreTokens()) {
                    System.out.println(token.nextToken());
                }
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
