/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource.booking.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import static resource.booking.system.ResourceBookingSystem.folderDirectory;
import static resource.booking.system.ResourceBookingSystem.input;

/**
 *
 * @author ollie
 */
public class SavetoFile {

    public static ArrayList<bookingroom> listofpeople = new ArrayList();
public static String folderDirectory = System.getProperty("user.dir") + "\\bookList.txt";
    


    public static void writeFile(ArrayList<bookingroom> listofpeople) {

        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < listofpeople.size(); i++) {
                printToFile.println(listofpeople.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public static ArrayList<bookingroom> readFile() {
        ArrayList<bookingroom> listofpeople = new ArrayList<>();
        String lineFromFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] listof = lineFromFile.split(", ");
                bookingroom a = new bookingroom(listof[0], listof[1], Integer.parseInt(listof[2]), listof[3], listof[4], listof[5], listof[6]);
                listofpeople.add(a);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listofpeople;
    }

}
