import java.util.*;
import java.io.*;

public class ICSCalendar {   
   private static String classification;
   private static String location;
   private static int priority;
   private static String summary;
   private static String tzid;
   private static String dtstart;
   private static String dtend;
   
   private final static String FILENAME = "ICS314Calendar.ics";

   public static void main(String args[]) {
      Scanner scan = new Scanner(System.in);
      
      int num = 0;
      buildIcsFile();
      while (num == 0) {
         try {
            System.out.print(printMenu());
            // get menu option
            num = scan.nextInt();
            if (num == 3) {
               System.out.print("Entered 3\n");
               finalizeIcsFile();
            }
            else if (num == 2) {
               System.out.print("Entered 2\n");
               insertEvent();
               num = 0;
            }
            else if (num == 1) {
               System.out.print("Entered 1\n");
               getUserInput();
               num = 0;
            } 
            else {
               System.out.println("Invalid Number.  Please enter a valid number.\n");
               System.out.print(printMenu());
               num = scan.nextInt();
            }
         }
         catch(InputMismatchException e) {
            System.out.print("Invalid Input.  Please enter a valid number (Ex: 1)\n");
            scan.nextLine();
            num = 0;
         }
      }
      
   }
   
   public static String printMenu() {
      String menu;
      menu = "Please enter the number of the option you would like to take: \n1. Create new calendar event\n2. Save last created event to .ics file\n3. Exit\n\n";
      return menu;
   }
   
   
 /** 
   * Gets user input and stores it in the appropriate static variable
   */
   public static void getUserInput() {
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter(System.getProperty("line.separator")); // get the appropriate newline character for the OS
      boolean loopControl = true;
      boolean loopControlTwo = true;
      String date;
      String time;
      
      while (loopControl) {
         System.out.println("Please enter a classification for the event: (public, private or confidential)");
         classification = scan.next();
         if (classification.equals("public") || classification.equals("private") || classification.equals("confidential"))
            loopControl = false;
      }
      System.out.println("Please enter a location for the event: ");
      location = scan.next();
      while (loopControlTwo) {
         try {
            System.out.println("Please enter a priority number from 0-9: ");
            priority = Integer.parseInt(scan.next());
            loopControlTwo = false;
         }
         catch(NumberFormatException e) {
            System.out.println("Invalid input; Please enter a number.");
         }
      }
      System.out.println("Please enter a short summary for the event: ");
      summary = scan.next();
      System.out.println("Please enter a timezone ID (E.G. 'America/New_York'): ");
      tzid = scan.next();
      
      System.out.println("Please enter the start date for the event (yyyy/mm/dd): ");
      date = scan.next();
      Scanner split = new Scanner(date).useDelimiter("/");
      dtstart = split.next() + split.next() + split.next();
      System.out.println("Please enter the start time in 24-hour format: (00:00-24:00) ");
      time = scan.next();
      split = new Scanner(time).useDelimiter(":");
      dtstart += "T" + split.next() + split.next() + "00";
      
      System.out.println("Please enter the end date for the event (yyyy/mm/dd): ");
      date = scan.next();
      split = new Scanner(date).useDelimiter("/");
      dtend = split.next() + split.next() + split.next();
      System.out.println("Please enter the end time in 24-hour format: (00:00-24:00) ");
      time = scan.next();
      split = new Scanner(time).useDelimiter(":");
      dtend += "T" + split.next() + split.next() + "00";
   }
   
   
 /** 
   * Creates the initial .ics file
   */
   public static void buildIcsFile() {
      try {
         FileWriter ics = new FileWriter(FILENAME);
         ics.write("BEGIN:VCALENDAR\n");
         ics.append("VERSION:2.0\n");
         ics.close();
      }
      catch(IOException e) {
         e.printStackTrace();
      }
   }
 /** 
   * appends the last created event with the state currently in the static variables
   */  
   public static void insertEvent() {
      try {
         FileWriter ics = new FileWriter(FILENAME, true);
         ics.append("BEGIN:VEVENT\n");
         ics.append("CLASS:"+classification+"\n");
         ics.append("LOCATION:"+location+"\n");
         ics.append("PRIORITY:"+priority+"\n");
         ics.append("SUMMARY:"+summary+"\n");
         ics.append("TZID:"+tzid+"\n");
         ics.append("DTSTART:"+dtstart+"\n");
         ics.append("DTEND:"+dtend+"\n");
         ics.append("END:VEVENT\n");
         ics.close();
      }
      catch(IOException e) {
         e.printStackTrace();
      }
   }
 /** 
   * Finalizes the .ics file with the END calendar field.
   */
   public static void finalizeIcsFile() {
      try {
         FileWriter ics = new FileWriter(FILENAME, true);
         ics.write("END:VCALENDAR\n");
         ics.close();
      }
      catch(IOException e) {
         e.printStackTrace();
      }
   }
}
