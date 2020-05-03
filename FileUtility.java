package tech.codingclub;

import sun.nio.ch.sctp.SctpNet;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class FileUtility {

    public static boolean createFile(String fileNameWithPath){

        File file = new File(fileNameWithPath);
        boolean fileCreated = false;

        try{
            fileCreated = file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        return fileCreated;
    }

    public static ArrayList<String> readAndPrintFile(String fileName){

        Scanner sc = null;

        ArrayList<String> fileStrings = new ArrayList<String>();
        try{

            File file = new File(fileName);
            sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
//                System.out.println(line);
                fileStrings.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sc!=null)
                sc.close();
        }
        return fileStrings;
    }

    public static boolean writeToFile(String fileNameWithPath, String content){
        BufferedWriter bw = null;
        try{
            File file = new File(fileNameWithPath);
            if(!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }finally {
            if(bw!=null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;

    }

    public static boolean appendToFile(String fileNAme, String content){

        try{
            FileWriter fw = new FileWriter(fileNAme,true);
            fw.append(content);
            fw.append("\n");
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public static void main(String[] args) {

        System.out.println("This is TANAY SRIVASTAVA");
        System.out.println("FileUtility running at " + new Date().toString() + " sharp.");
        String nameOfNewFile = "/home/tanaysri_/IdeaProjects/techcodingmafia/data/practice/file/"+"create-file.txt";
        boolean status = createFile(nameOfNewFile);
        System.out.println("File Created: "+status);

        ArrayList<String> fileStrings = new ArrayList<String>();
        fileStrings = readAndPrintFile(nameOfNewFile);

        for(String x: fileStrings){
            System.out.println(x);
        }

        System.out.println("Number of lines in file : "+fileStrings.size());
        String nameOfWriteFile = "/home/tanaysri_/IdeaProjects/techcodingmafia/data/practice/file/"+"write-file.txt";
        boolean wroteToFile = writeToFile(nameOfWriteFile, "This file was generated on Tanay's system atumatically!!");
        System.out.println(wroteToFile);

        for(int i=1; i<=100;i++) {
            String data = i + "";
            appendToFile(nameOfWriteFile, data);
        }
    }

    public static ArrayList<String> readFileAsList(String fileName) {
        Scanner scanner = null;

        ArrayList<String> strings = new ArrayList<String>();
        try{
            File file = new File(fileName);
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                strings.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return strings;
    }
}
