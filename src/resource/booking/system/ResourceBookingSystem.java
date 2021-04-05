package resource.booking.system;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceBookingSystem {

    public static String folderDirectory = System.getProperty("user.dir") + "\\listofpeople.txt";

    public static ArrayList<bookingroom> listofpeople = new ArrayList();
    public static ArrayList<String> room1time = new ArrayList();
    public static ArrayList<String> room2time = new ArrayList();
    public static ArrayList<String> room3time = new ArrayList();
    public static ArrayList<String> room4time = new ArrayList();
    public static ArrayList<String> room5time = new ArrayList();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean x = true;
        try {
            while (x = true) {
                System.out.println("Hello, what would you like to do");
                System.out.println("create a booking(1), view a booking(2), catering staff bookings (3), delete a booking(4), edit a booking(5 exit the software (0)");
                int choosesoftware = input.nextInt();
                while (choosesoftware < 0 || choosesoftware > 4) {
                    System.out.println("An error was detected please input another software choice please ");
                    choosesoftware = input.nextInt();
                }
                switch (choosesoftware) {
                    case 1:
                        createabooking();
                        break;
                    case 2:
                        if(listofpeople.isEmpty()){
                            System.out.println("No bookings have been made");
                        }
                        for (int i = 0; i < listofpeople.size(); i++) {
                                System.out.println(i + ") First Name: " + listofpeople.get(i).getFname() + ", "
                                        + "Surname: " + listofpeople.get(i).getLname() + ", "
                                        + "Refreshments: " + listofpeople.get(i).getRefreshments() + ", "
                                        + "Equipment: " + listofpeople.get(i).getResources() + ", "
                                        + "Date of booking: " + listofpeople.get(i).getCurrentBooking());
                                // Didn't think catering staff should have access to email
                            }
                        break;
                    case 3:
                        System.out.println("Catering Staff to See when you are needed please enter (Password)");
                        String PasswordGuess = input.next();
                        String Password = "Password";
                        if (Password.equals(PasswordGuess)) {
                            for (int i = 0; i < listofpeople.size(); i++) {
                                System.out.println(i + ") First Name: " + listofpeople.get(i).getFname() + ", "
                                        + "Surname: " + listofpeople.get(i).getLname() + ", "
                                        + "Refreshments: " + listofpeople.get(i).getRefreshments() + ", "
                                        + "Equipment: " + listofpeople.get(i).getResources() + ", "
                                        + "Date of booking: " + listofpeople.get(i).getCurrentBooking());
                                // Didn't think catering staff should have access to email
                            }
                        }
                        if (listofpeople.isEmpty()) {
                            System.out.println("No current bookings");
                        }

                        break;
                    case 4:
                        deleteBooking();
                        break;
                    case 5:
                        editBook();
                    case 0:
                        exit();
                        break;

                }

            }
        } catch (Exception e) {
            System.out.println("An error was found");
        }
    }

    public static void exit() {
        System.out.println("You have chosen to exit the booking software"
                + " Bye");
        System.exit(0);

    }

    public static void createabooking() {
        ArrayList<String> choiceofRefreshments = new ArrayList<String>();
        ArrayList<String> choiceofequipment = new ArrayList<String>();
        try {
            boolean help = true;
            System.out.println("How many people are you planning on participating in your event?");
            int Numofpeople = input.nextInt();
            while (Numofpeople <= 0 || Numofpeople > 50) {
                System.out.println("The number you have inputed does not work please try again");
                Numofpeople = input.nextInt();
            }
            System.out.println("Does anyone in your group require specialist assistance, Y or N");
            String YorN = input.next();
            while (!YorN.contains("Y") & !YorN.contains("N")) {
                System.out.println("An error has occured, do you require specialist assistance, Y or N");
                YorN = input.next();
            }
            if (YorN.equals("Y")) {
                help = true;
            }
            if (YorN.equals("N")) {
                help = false;
            }

            if (Numofpeople <= 2 & help == false) {
                System.out.println("You can have room 1");
                System.out.println("What is your first name");
                String Fname = input.next();

                System.out.println("What is your Last name");
                String Lname = input.next();
                System.out.println("What is your Email?");
                System.out.println("Please note this must be a gmail email");
                String UserEmailS = input.next();
                Pattern pattern = Pattern.compile("@gmail.com", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(UserEmailS);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    System.out.println("the email you have entered it correct");
                } else {
                    while (matchFound == false) {
                        System.out.println("Please enter in another email, you need to have an gmail account");
                        UserEmailS = input.next();
                        matcher = pattern.matcher(UserEmailS);
                        matchFound = matcher.find();
                    }
                }
//            bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmail);
//            listofpeople.add(room);
                System.out.println("What year would you like to book the room");
                int Year = input.nextInt();
                int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                while (Year < CurrentYear) {
                    System.out.println("Please enter in another Year an Error was found");
                    Year = input.nextInt();
                }
                System.out.println("what Month would you like to book the room");
                int Month = input.nextInt();
                int CurrentMonth = 1 + Calendar.getInstance().get(Calendar.MONTH);
                while (Month == 0 || Month > 12) {
                    System.out.println("Please enter another Month an Error was found");
                    Month = input.nextInt();
                }
                if (CurrentYear == Year) {
                    while (Month < CurrentMonth) {
                        System.out.println("Please enter another Month as a Error was found");
                        Month = input.nextInt();
                    }
                }
                System.out.println("What day would you like to book the room");
                int day = input.nextInt();
                int Currentday = Calendar.getInstance().get(Calendar.DATE);
                if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
                    while (day > 31 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                    while (day > 30 || day < 0) {
                        System.out.println("Please enter in another Day an error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 2) {
                    while (day > 28 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (CurrentYear == Year) {
                    while (Month == CurrentMonth) {
                        if (day < Currentday || day == Currentday) {
                            System.out.println("The day entered was incorrect, please enter in another date");
                            day = input.nextInt();
                        }
                    }
                }
                String YtoS = String.valueOf(Year);
                String MtoS = String.valueOf(Month);
                String DtoS = String.valueOf(day);

                System.out.println("Our Company offer premium services, therefore we may offer you 3 dates on the dates you have suggested, meaning we are open all 365 days of the year");

                System.out.println("10:30 , 14:30 , 17:00, you may have access to this room for a maximum 2 hours");
                System.out.println("Which date would you like?");
                String time = input.next();
                while (!time.equals("10:30") & !time.equals("14:30") & !time.equals("17:00")) {
                    System.out.println("An Error was found please enter in another Time. 10:30 , 14:30 , 17:00");
                    time = input.next();
                }
                String CurrentBooking = DtoS + "/" + MtoS + "/" + YtoS + ":" + time;
                String booking1 = YtoS + MtoS + DtoS + "10:30";
                String booking2 = YtoS + MtoS + DtoS + "14:30";
                String booking3 = YtoS + MtoS + DtoS + "17:00";
                if (room1time.contains(booking1) || room1time.contains(booking2) || room1time.contains(booking3)) {
                    System.out.println("unfortunately the date and time has already been booked please enter in a new time and date");
                    System.out.println("The booking software will reset, and you will need to enter your details again, Sorry, Thank you");
                    createabooking();

                } else {
                    room1time.add(CurrentBooking);
                }

                System.out.println("Would you like to book any resources Y or N");
                String YorN2 = input.next();
                while (YorN2.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Projector(1),  Pens(2), Paper(3), Pencil(4), Move on(0)");
                        int choice = input.nextInt();
                        if (choice == 0) {
                            YorN2 = "N";
                        }

                        if (choice == 1) {
                            choiceofequipment.add("Projector");
                        }
                        if (choice == 2) {

                            System.out.println("How many Pens? we can offe up to 20");
                            int Pens = input.nextInt();
                            while (Pens < 0 & Pens > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pens = input.nextInt();
                            }
                            choiceofequipment.add(Pens + " Pen");
                        }
                        if (choice == 3) {

                            System.out.println("How many pieces of Paper ? we can offer up to 50");
                            int pieces = input.nextInt();
                            while (pieces < 0 & pieces > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                pieces = input.nextInt();
                            }
                            choiceofequipment.add(pieces + " piece(s) of Paper");

                        }
                        if (choice == 4) {
                            System.out.println("How many Pencils? we can offer up to 20");
                            int Pencils = input.nextInt();
                            while (Pencils < 0 & Pencils > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pencils = input.nextInt();
                            }
                            choiceofequipment.add(Pencils + " Pencils");
                        }

                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }

                    }
                }

                System.out.println("Would you like to book any Refreshments? Y or N");
                String Refresh = input.next();
                while (Refresh.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                        int choice = input.nextInt();
                        String choiceS = String.valueOf(choice);
                        while (!choiceS.equals("0") & !choiceS.equals("1") & !choiceS.equals("2") & !choiceS.equals("3") & !choiceS.equals("4") & !choiceS.equals("5")) {
                            System.out.println("Sorry that input did not work, Please try again");
                            System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                            choice = input.nextInt();
                            choiceS = String.valueOf(choice);
                        }
                        if (choice == 0) {
                            Refresh = "N";
                        }

                        if (choice == 1) {
                            System.out.println("How many Cokes? we can offer up to 15");
                            int Coke = input.nextInt();
                            while (Coke < 0 & Coke > 15) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coke = input.nextInt();
                            }
                            choiceofRefreshments.add(Coke + "Coke");
                        }
                        if (choice == 2) {

                            System.out.println("How many Coffees do you want? we can offer up to 20");
                            int Coffee = input.nextInt();
                            while (Coffee < 0 & Coffee > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coffee = input.nextInt();
                            }
                            String CCoffee = String.valueOf(Coffee);
                            choiceofRefreshments.add(CCoffee + " Coffees");
                        }
                        if (choice == 3) {

                            System.out.println("How many cups of water? we can offer up to 50");
                            int Water = input.nextInt();
                            while (Water < 0 & Water > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Water = input.nextInt();
                            }
                            choiceofRefreshments.add(Water + " Cups of water");

                        }
                        if (choice == 4) {
                            System.out.println("How many Teas would you like? we can offer 20 maximum");
                            int Teas = input.nextInt();
                            while (Teas < 0 & Teas > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Teas = input.nextInt();
                            }
                            choiceofRefreshments.add(Teas + " cups of teas");
                        }
                        if (choice == 5) {
                            System.out.println("How many Ham and Cheese Sandwiches? We can only offer 10 in maximum");
                            int HandC = input.nextInt();
                            while (HandC < 0 & HandC > 10) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                HandC = input.nextInt();
                            }
                            choiceofRefreshments.add(HandC + " Ham and Cheese Sandwiches");
                        }
                        String Refreshments = choiceofRefreshments.toString();
                        choiceofRefreshments.clear();
                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }
                    }
                }
                System.out.println("You have finished your booking process");
                System.out.println("See you on" + CurrentBooking);
                String Refreshments = choiceofRefreshments.toString();
                choiceofRefreshments.clear();
                String Equipment = choiceofequipment.toString();
                choiceofequipment.clear();

                bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmailS, CurrentBooking, Refreshments, Equipment);
                listofpeople.add(room);

            }

            if (Numofpeople > 2 & Numofpeople <= 4 & help == false) {
                System.out.println("You can have room 2");
                System.out.println("You can have room 1");
                System.out.println("What is your first name");
                String Fname = input.next();

                System.out.println("What is your Last name");
                String Lname = input.next();
                System.out.println("What is your Email?");
                System.out.println("Please note this must be a gmail email");
                String UserEmailS = input.next();
                Pattern pattern = Pattern.compile("@gmail.com", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(UserEmailS);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    System.out.println("the email you have entered it correct");
                } else {
                    while (matchFound == false) {
                        System.out.println("Please enter in another email, you need to have an gmail account");
                        UserEmailS = input.next();
                        matcher = pattern.matcher(UserEmailS);
                        matchFound = matcher.find();
                    }
                }
//            bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmail);
//            listofpeople.add(room);
                System.out.println("What year would you like to book the room");
                int Year = input.nextInt();
                int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                while (Year < CurrentYear) {
                    System.out.println("Please enter in another Year an Error was found");
                    Year = input.nextInt();
                }
                System.out.println("what Month would you like to book the room");
                int Month = input.nextInt();
                int CurrentMonth = 1 + Calendar.getInstance().get(Calendar.MONTH);
                while (Month == 0 || Month > 12) {
                    System.out.println("Please enter another Month an Error was found");
                    Month = input.nextInt();
                }
                if (CurrentYear == Year) {
                    while (Month < CurrentMonth) {
                        System.out.println("Please enter another Month as a Error was found");
                        Month = input.nextInt();
                    }
                }
                System.out.println("What day would you like to book the room");
                int day = input.nextInt();
                int Currentday = Calendar.getInstance().get(Calendar.DATE);
                if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
                    while (day > 31 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                    while (day > 30 || day < 0) {
                        System.out.println("Please enter in another Day an error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 2) {
                    while (day > 28 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (CurrentYear == Year) {
                    while (Month == CurrentMonth) {
                        if (day < Currentday || day == Currentday) {
                            System.out.println("The day entered was incorrect, please enter in another date");
                            day = input.nextInt();
                        }
                    }
                }
                String YtoS = String.valueOf(Year);
                String MtoS = String.valueOf(Month);
                String DtoS = String.valueOf(day);

                System.out.println("Our Company offer premium services, therefore we may offer you 3 dates on the dates you have suggested, meaning we are open all 365 days of the year");

                System.out.println("10:30 , 14:30 , 17:00, you may have access to this room for a maximum 2 hours");
                System.out.println("Which date would you like?");
                String time = input.next();
                while (!time.equals("10:30") & !time.equals("14:30") & !time.equals("17:00")) {
                    System.out.println("An Error was found please enter in another Time. 10:30 , 14:30 , 17:00");
                    time = input.next();
                }
                String CurrentBooking = DtoS + "/" + MtoS + "/" + YtoS + ":" + time;
                String booking1 = YtoS + MtoS + DtoS + "10:30";
                String booking2 = YtoS + MtoS + DtoS + "14:30";
                String booking3 = YtoS + MtoS + DtoS + "17:00";
                if (room2time.contains(booking1) || room1time.contains(booking2) || room1time.contains(booking3)) {
                    System.out.println("unfortunately the date and time has already been booked please enter in a new time and date");
                    System.out.println("The booking software will reset, and you will need to enter your details again, Sorry, Thank you");
                    createabooking();

                } else {
                    room2time.add(CurrentBooking);
                }

                System.out.println("Would you like to book any resources Y or N");
                String YorN2 = input.next();
                while (YorN2.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Projector(1),  Pens(2), Paper(3), Pencil(4), Move on(0)");
                        int choice = input.nextInt();
                        if (choice == 0) {
                            YorN2 = "N";
                        }

                        if (choice == 1) {
                            choiceofequipment.add("Projector");
                        }
                        if (choice == 2) {

                            System.out.println("How many Pens? we can offe up to 20");
                            int Pens = input.nextInt();
                            while (Pens < 0 & Pens > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pens = input.nextInt();
                            }
                            choiceofequipment.add(Pens + " Pen");
                        }
                        if (choice == 3) {

                            System.out.println("How many pieces of Paper ? we can offer up to 50");
                            int pieces = input.nextInt();
                            while (pieces < 0 & pieces > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                pieces = input.nextInt();
                            }
                            choiceofequipment.add(pieces + " piece(s) of Paper");

                        }
                        if (choice == 4) {
                            System.out.println("How many Pencils? we can offer up to 20");
                            int Pencils = input.nextInt();
                            while (Pencils < 0 & Pencils > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pencils = input.nextInt();
                            }
                            choiceofequipment.add(Pencils + " Pencils");
                        }

                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }

                    }
                }

                System.out.println("Would you like to book any Refreshments? Y or N");
                String Refresh = input.next();
                while (Refresh.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                        int choice = input.nextInt();
                        String choiceS = String.valueOf(choice);
                        while (!choiceS.equals("0") & !choiceS.equals("1") & !choiceS.equals("2") & !choiceS.equals("3") & !choiceS.equals("4") & !choiceS.equals("5")) {
                            System.out.println("Sorry that input did not work, Please try again");
                            System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                            choice = input.nextInt();
                            choiceS = String.valueOf(choice);
                        }
                        if (choice == 0) {
                            Refresh = "N";
                        }

                        if (choice == 1) {
                            System.out.println("How many Cokes? we can offer up to 15");
                            int Coke = input.nextInt();
                            while (Coke < 0 & Coke > 15) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coke = input.nextInt();
                            }
                            choiceofRefreshments.add(Coke + "Coke");
                        }
                        if (choice == 2) {

                            System.out.println("How many Coffees do you want? we can offer up to 20");
                            int Coffee = input.nextInt();
                            while (Coffee < 0 & Coffee > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coffee = input.nextInt();
                            }
                            String CCoffee = String.valueOf(Coffee);
                            choiceofRefreshments.add(CCoffee + " Coffees");
                        }
                        if (choice == 3) {

                            System.out.println("How many cups of water? we can offer up to 50");
                            int Water = input.nextInt();
                            while (Water < 0 & Water > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Water = input.nextInt();
                            }
                            choiceofRefreshments.add(Water + " Cups of water");

                        }
                        if (choice == 4) {
                            System.out.println("How many Teas would you like? we can offer 20 maximum");
                            int Teas = input.nextInt();
                            while (Teas < 0 & Teas > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Teas = input.nextInt();
                            }
                            choiceofRefreshments.add(Teas + " cups of teas");
                        }
                        if (choice == 5) {
                            System.out.println("How many Ham and Cheese Sandwiches? We can only offer 10 in maximum");
                            int HandC = input.nextInt();
                            while (HandC < 0 & HandC > 10) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                HandC = input.nextInt();
                            }
                            choiceofRefreshments.add(HandC + " Ham and Cheese Sandwiches");
                        }
                        String Refreshments = choiceofRefreshments.toString();
                        choiceofRefreshments.clear();
                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }
                    }
                }
                System.out.println("You have finished your booking process");
                System.out.println("See you on" + CurrentBooking);
                String Refreshments = choiceofRefreshments.toString();
                choiceofRefreshments.clear();
                String Equipment = choiceofequipment.toString();
                choiceofequipment.clear();

                bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmailS, CurrentBooking, Refreshments, Equipment);
                listofpeople.add(room);

            }

            if (Numofpeople > 4 & Numofpeople <= 8 & help == false) {
                System.out.println("You can have room 3");
                System.out.println("You can have room 2");
                System.out.println("You can have room 1");
                System.out.println("What is your first name");
                String Fname = input.next();

                System.out.println("What is your Last name");
                String Lname = input.next();
                System.out.println("What is your Email?");
                System.out.println("Please note this must be a gmail email");
                String UserEmailS = input.next();
                Pattern pattern = Pattern.compile("@gmail.com", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(UserEmailS);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    System.out.println("the email you have entered it correct");
                } else {
                    while (matchFound == false) {
                        System.out.println("Please enter in another email, you need to have an gmail account");
                        UserEmailS = input.next();
                        matcher = pattern.matcher(UserEmailS);
                        matchFound = matcher.find();
                    }
                }
//            bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmail);
//            listofpeople.add(room);
                System.out.println("What year would you like to book the room");
                int Year = input.nextInt();
                int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                while (Year < CurrentYear) {
                    System.out.println("Please enter in another Year an Error was found");
                    Year = input.nextInt();
                }
                System.out.println("what Month would you like to book the room");
                int Month = input.nextInt();
                int CurrentMonth = 1 + Calendar.getInstance().get(Calendar.MONTH);
                while (Month == 0 || Month > 12) {
                    System.out.println("Please enter another Month an Error was found");
                    Month = input.nextInt();
                }
                if (CurrentYear == Year) {
                    while (Month < CurrentMonth) {
                        System.out.println("Please enter another Month as a Error was found");
                        Month = input.nextInt();
                    }
                }
                System.out.println("What day would you like to book the room");
                int day = input.nextInt();
                int Currentday = Calendar.getInstance().get(Calendar.DATE);
                if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
                    while (day > 31 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                    while (day > 30 || day < 0) {
                        System.out.println("Please enter in another Day an error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 2) {
                    while (day > 28 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (CurrentYear == Year) {
                    while (Month == CurrentMonth) {
                        if (day < Currentday || day == Currentday) {
                            System.out.println("The day entered was incorrect, please enter in another date");
                            day = input.nextInt();
                        }
                    }
                }
                String YtoS = String.valueOf(Year);
                String MtoS = String.valueOf(Month);
                String DtoS = String.valueOf(day);

                System.out.println("Our Company offer premium services, therefore we may offer you 3 dates on the dates you have suggested, meaning we are open all 365 days of the year");

                System.out.println("10:30 , 14:30 , 17:00, you may have access to this room for a maximum 2 hours");
                System.out.println("Which date would you like?");
                String time = input.next();
                while (!time.equals("10:30") & !time.equals("14:30") & !time.equals("17:00")) {
                    System.out.println("An Error was found please enter in another Time. 10:30 , 14:30 , 17:00");
                    time = input.next();
                }
                String CurrentBooking = DtoS + "/" + MtoS + "/" + YtoS + ":" + time;
                String booking1 = YtoS + MtoS + DtoS + "10:30";
                String booking2 = YtoS + MtoS + DtoS + "14:30";
                String booking3 = YtoS + MtoS + DtoS + "17:00";
                if (room3time.contains(booking1) || room3time.contains(booking2) || room3time.contains(booking3)) {
                    System.out.println("unfortunately the date and time has already been booked please enter in a new time and date");
                    System.out.println("The booking software will reset, and you will need to enter your details again, Sorry, Thank you");
                    createabooking();

                } else {
                    room3time.add(CurrentBooking);
                }

                System.out.println("Would you like to book any resources Y or N");
                String YorN2 = input.next();
                while (YorN2.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Projector(1),  Pens(2), Paper(3), Pencil(4), Move on(0)");
                        int choice = input.nextInt();
                        if (choice == 0) {
                            YorN2 = "N";
                        }

                        if (choice == 1) {
                            choiceofequipment.add("Projector");
                        }
                        if (choice == 2) {

                            System.out.println("How many Pens? we can offe up to 20");
                            int Pens = input.nextInt();
                            while (Pens < 0 & Pens > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pens = input.nextInt();
                            }
                            choiceofequipment.add(Pens + " Pen");
                        }
                        if (choice == 3) {

                            System.out.println("How many pieces of Paper ? we can offer up to 50");
                            int pieces = input.nextInt();
                            while (pieces < 0 & pieces > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                pieces = input.nextInt();
                            }
                            choiceofequipment.add(pieces + " piece(s) of Paper");

                        }
                        if (choice == 4) {
                            System.out.println("How many Pencils? we can offer up to 20");
                            int Pencils = input.nextInt();
                            while (Pencils < 0 & Pencils > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pencils = input.nextInt();
                            }
                            choiceofequipment.add(Pencils + " Pencils");
                        }

                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }

                    }
                }

                System.out.println("Would you like to book any Refreshments? Y or N");
                String Refresh = input.next();
                while (Refresh.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                        int choice = input.nextInt();
                        String choiceS = String.valueOf(choice);
                        while (!choiceS.equals("0") & !choiceS.equals("1") & !choiceS.equals("2") & !choiceS.equals("3") & !choiceS.equals("4") & !choiceS.equals("5")) {
                            System.out.println("Sorry that input did not work, Please try again");
                            System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                            choice = input.nextInt();
                            choiceS = String.valueOf(choice);
                        }
                        if (choice == 0) {
                            Refresh = "N";
                        }

                        if (choice == 1) {
                            System.out.println("How many Cokes? we can offer up to 15");
                            int Coke = input.nextInt();
                            while (Coke < 0 & Coke > 15) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coke = input.nextInt();
                            }
                            choiceofRefreshments.add(Coke + "Coke");
                        }
                        if (choice == 2) {

                            System.out.println("How many Coffees do you want? we can offer up to 20");
                            int Coffee = input.nextInt();
                            while (Coffee < 0 & Coffee > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coffee = input.nextInt();
                            }
                            String CCoffee = String.valueOf(Coffee);
                            choiceofRefreshments.add(CCoffee + " Coffees");
                        }
                        if (choice == 3) {

                            System.out.println("How many cups of water? we can offer up to 50");
                            int Water = input.nextInt();
                            while (Water < 0 & Water > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Water = input.nextInt();
                            }
                            choiceofRefreshments.add(Water + " Cups of water");

                        }
                        if (choice == 4) {
                            System.out.println("How many Teas would you like? we can offer 20 maximum");
                            int Teas = input.nextInt();
                            while (Teas < 0 & Teas > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Teas = input.nextInt();
                            }
                            choiceofRefreshments.add(Teas + " cups of teas");
                        }
                        if (choice == 5) {
                            System.out.println("How many Ham and Cheese Sandwiches? We can only offer 10 in maximum");
                            int HandC = input.nextInt();
                            while (HandC < 0 & HandC > 10) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                HandC = input.nextInt();
                            }
                            choiceofRefreshments.add(HandC + " Ham and Cheese Sandwiches");
                        }
                        String Refreshments = choiceofRefreshments.toString();
                        choiceofRefreshments.clear();
                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }
                    }
                }
                System.out.println("You have finished your booking process");
                System.out.println("See you on" + CurrentBooking);
                String Refreshments = choiceofRefreshments.toString();
                choiceofRefreshments.clear();
                String Equipment = choiceofequipment.toString();
                choiceofequipment.clear();

                bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmailS, CurrentBooking, Refreshments, Equipment);
                listofpeople.add(room);

            }
            if (Numofpeople > 0 & Numofpeople <= 15 & help == true) {
                System.out.println("You can have room 4");
                System.out.println("What is your first name");
                String Fname = input.next();

                System.out.println("What is your Last name");
                String Lname = input.next();
                System.out.println("What is your Email?");
                System.out.println("Please note this must be a gmail email");
                String UserEmailS = input.next();
                Pattern pattern = Pattern.compile("@gmail.com", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(UserEmailS);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    System.out.println("the email you have entered it correct");
                } else {
                    while (matchFound == false) {
                        System.out.println("Please enter in another email, you need to have an gmail account");
                        UserEmailS = input.next();
                        matcher = pattern.matcher(UserEmailS);
                        matchFound = matcher.find();
                    }
                }
//            bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmail);
//            listofpeople.add(room);
                System.out.println("What year would you like to book the room");
                int Year = input.nextInt();
                int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                while (Year < CurrentYear) {
                    System.out.println("Please enter in another Year an Error was found");
                    Year = input.nextInt();
                }
                System.out.println("what Month would you like to book the room");
                int Month = input.nextInt();
                int CurrentMonth = 1 + Calendar.getInstance().get(Calendar.MONTH);
                while (Month == 0 || Month > 12) {
                    System.out.println("Please enter another Month an Error was found");
                    Month = input.nextInt();
                }
                if (CurrentYear == Year) {
                    while (Month < CurrentMonth) {
                        System.out.println("Please enter another Month as a Error was found");
                        Month = input.nextInt();
                    }
                }
                System.out.println("What day would you like to book the room");
                int day = input.nextInt();
                int Currentday = Calendar.getInstance().get(Calendar.DATE);
                if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
                    while (day > 31 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                    while (day > 30 || day < 0) {
                        System.out.println("Please enter in another Day an error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 2) {
                    while (day > 28 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (CurrentYear == Year) {
                    while (Month == CurrentMonth) {
                        if (day < Currentday || day == Currentday) {
                            System.out.println("The day entered was incorrect, please enter in another date");
                            day = input.nextInt();
                        }
                    }
                }
                String YtoS = String.valueOf(Year);
                String MtoS = String.valueOf(Month);
                String DtoS = String.valueOf(day);

                System.out.println("Our Company offer premium services, therefore we may offer you 3 dates on the dates you have suggested, meaning we are open all 365 days of the year");

                System.out.println("10:30 , 14:30 , 17:00, you may have access to this room for a maximum 2 hours");
                System.out.println("Which date would you like?");
                String time = input.next();
                while (!time.equals("10:30") & !time.equals("14:30") & !time.equals("17:00")) {
                    System.out.println("An Error was found please enter in another Time. 10:30 , 14:30 , 17:00");
                    time = input.next();
                }
                String CurrentBooking = DtoS + "/" + MtoS + "/" + YtoS + ":" + time;
                String booking1 = YtoS + MtoS + DtoS + "10:30";
                String booking2 = YtoS + MtoS + DtoS + "14:30";
                String booking3 = YtoS + MtoS + DtoS + "17:00";
                if (room4time.contains(booking1) || room4time.contains(booking2) || room4time.contains(booking3)) {
                    System.out.println("unfortunately the date and time has already been booked please enter in a new time and date");
                    System.out.println("The booking software will reset, and you will need to enter your details again, Sorry, Thank you");
                    createabooking();

                } else {
                    room4time.add(CurrentBooking);
                }

                System.out.println("Would you like to book any resources Y or N");
                String YorN2 = input.next();
                while (YorN2.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Projector(1),  Pens(2), Paper(3), Pencil(4), Move on(0)");
                        int choice = input.nextInt();
                        if (choice == 0) {
                            YorN2 = "N";
                        }

                        if (choice == 1) {
                            choiceofequipment.add("Projector");
                        }
                        if (choice == 2) {

                            System.out.println("How many Pens? we can offe up to 20");
                            int Pens = input.nextInt();
                            while (Pens < 0 & Pens > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pens = input.nextInt();
                            }
                            choiceofequipment.add(Pens + " Pen");
                        }
                        if (choice == 3) {

                            System.out.println("How many pieces of Paper ? we can offer up to 50");
                            int pieces = input.nextInt();
                            while (pieces < 0 & pieces > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                pieces = input.nextInt();
                            }
                            choiceofequipment.add(pieces + " piece(s) of Paper");

                        }
                        if (choice == 4) {
                            System.out.println("How many Pencils? we can offer up to 20");
                            int Pencils = input.nextInt();
                            while (Pencils < 0 & Pencils > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pencils = input.nextInt();
                            }
                            choiceofequipment.add(Pencils + " Pencils");
                        }

                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }

                    }
                }

                System.out.println("Would you like to book any Refreshments? Y or N");
                String Refresh = input.next();
                while (Refresh.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                        int choice = input.nextInt();
                        String choiceS = String.valueOf(choice);
                        while (!choiceS.equals("0") & !choiceS.equals("1") & !choiceS.equals("2") & !choiceS.equals("3") & !choiceS.equals("4") & !choiceS.equals("5")) {
                            System.out.println("Sorry that input did not work, Please try again");
                            System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                            choice = input.nextInt();
                            choiceS = String.valueOf(choice);
                        }
                        if (choice == 0) {
                            Refresh = "N";
                        }

                        if (choice == 1) {
                            System.out.println("How many Cokes? we can offer up to 15");
                            int Coke = input.nextInt();
                            while (Coke < 0 & Coke > 15) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coke = input.nextInt();
                            }
                            choiceofRefreshments.add(Coke + "Coke");
                        }
                        if (choice == 2) {

                            System.out.println("How many Coffees do you want? we can offer up to 20");
                            int Coffee = input.nextInt();
                            while (Coffee < 0 & Coffee > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coffee = input.nextInt();
                            }
                            String CCoffee = String.valueOf(Coffee);
                            choiceofRefreshments.add(CCoffee + " Coffees");
                        }
                        if (choice == 3) {

                            System.out.println("How many cups of water? we can offer up to 50");
                            int Water = input.nextInt();
                            while (Water < 0 & Water > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Water = input.nextInt();
                            }
                            choiceofRefreshments.add(Water + " Cups of water");

                        }
                        if (choice == 4) {
                            System.out.println("How many Teas would you like? we can offer 20 maximum");
                            int Teas = input.nextInt();
                            while (Teas < 0 & Teas > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Teas = input.nextInt();
                            }
                            choiceofRefreshments.add(Teas + " cups of teas");
                        }
                        if (choice == 5) {
                            System.out.println("How many Ham and Cheese Sandwiches? We can only offer 10 in maximum");
                            int HandC = input.nextInt();
                            while (HandC < 0 & HandC > 10) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                HandC = input.nextInt();
                            }
                            choiceofRefreshments.add(HandC + " Ham and Cheese Sandwiches");
                        }
                        String Refreshments = choiceofRefreshments.toString();
                        choiceofRefreshments.clear();
                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }
                    }
                }
                System.out.println("You have finished your booking process");
                System.out.println("See you on" + CurrentBooking);
                String Refreshments = choiceofRefreshments.toString();
                choiceofRefreshments.clear();
                String Equipment = choiceofequipment.toString();
                choiceofequipment.clear();

                bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmailS, CurrentBooking, Refreshments, Equipment);
                listofpeople.add(room);

            }

            if (Numofpeople > 15 & Numofpeople <= 50 & help == false) {
                System.out.println("You can have room 5");
                System.out.println("You can have room 2");
                System.out.println("You can have room 1");
                System.out.println("What is your first name");
                String Fname = input.next();

                System.out.println("What is your Last name");
                String Lname = input.next();
                System.out.println("What is your Email?");
                System.out.println("Please note this must be a gmail email");
                String UserEmailS = input.next();
                Pattern pattern = Pattern.compile("@gmail.com", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(UserEmailS);
                boolean matchFound = matcher.find();
                if (matchFound) {
                    System.out.println("the email you have entered it correct");
                } else {
                    while (matchFound == false) {
                        System.out.println("Please enter in another email, you need to have an gmail account");
                        UserEmailS = input.next();
                        matcher = pattern.matcher(UserEmailS);
                        matchFound = matcher.find();
                    }
                }
//            bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmail);
//            listofpeople.add(room);
                System.out.println("What year would you like to book the room");
                int Year = input.nextInt();
                int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                while (Year < CurrentYear) {
                    System.out.println("Please enter in another Year an Error was found");
                    Year = input.nextInt();
                }
                System.out.println("what Month would you like to book the room");
                int Month = input.nextInt();
                int CurrentMonth = 1 + Calendar.getInstance().get(Calendar.MONTH);
                while (Month == 0 || Month > 12) {
                    System.out.println("Please enter another Month an Error was found");
                    Month = input.nextInt();
                }
                if (CurrentYear == Year) {
                    while (Month < CurrentMonth) {
                        System.out.println("Please enter another Month as a Error was found");
                        Month = input.nextInt();
                    }
                }
                System.out.println("What day would you like to book the room");
                int day = input.nextInt();
                int Currentday = Calendar.getInstance().get(Calendar.DATE);
                if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
                    while (day > 31 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                    while (day > 30 || day < 0) {
                        System.out.println("Please enter in another Day an error was found");
                        day = input.nextInt();
                    }
                }
                if (Month == 2) {
                    while (day > 28 || day < 0) {
                        System.out.println("Please enter in another Day an Error was found");
                        day = input.nextInt();
                    }
                }
                if (CurrentYear == Year) {
                    while (Month == CurrentMonth) {
                        if (day < Currentday || day == Currentday) {
                            System.out.println("The day entered was incorrect, please enter in another date");
                            day = input.nextInt();
                        }
                    }
                }
                String YtoS = String.valueOf(Year);
                String MtoS = String.valueOf(Month);
                String DtoS = String.valueOf(day);

                System.out.println("Our Company offer premium services, therefore we may offer you 3 dates on the dates you have suggested, meaning we are open all 365 days of the year");

                System.out.println("10:30 , 14:30 , 17:00, you may have access to this room for a maximum 2 hours");
                System.out.println("Which date would you like?");
                String time = input.next();
                while (!time.equals("10:30") & !time.equals("14:30") & !time.equals("17:00")) {
                    System.out.println("An Error was found please enter in another Time. 10:30 , 14:30 , 17:00");
                    time = input.next();
                }
                String CurrentBooking = DtoS + "/" + MtoS + "/" + YtoS + ":" + time;
                String booking1 = YtoS + MtoS + DtoS + "10:30";
                String booking2 = YtoS + MtoS + DtoS + "14:30";
                String booking3 = YtoS + MtoS + DtoS + "17:00";
                if (room5time.contains(booking1) || room5time.contains(booking2) || room5time.contains(booking3)) {
                    System.out.println("unfortunately the date and time has already been booked please enter in a new time and date");
                    System.out.println("The booking software will reset, and you will need to enter your details again, Sorry, Thank you");
                    createabooking();

                } else {
                    room5time.add(CurrentBooking);
                }

                System.out.println("Would you like to book any resources Y or N");
                String YorN2 = input.next();
                while (YorN2.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Projector(1),  Pens(2), Paper(3), Pencil(4), Move on(0)");
                        int choice = input.nextInt();
                        if (choice == 0) {
                            YorN2 = "N";
                        }

                        if (choice == 1) {
                            choiceofequipment.add("Projector");
                        }
                        if (choice == 2) {

                            System.out.println("How many Pens? we can offe up to 20");
                            int Pens = input.nextInt();
                            while (Pens < 0 & Pens > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pens = input.nextInt();
                            }
                            choiceofequipment.add(Pens + " Pen");
                        }
                        if (choice == 3) {

                            System.out.println("How many pieces of Paper ? we can offer up to 50");
                            int pieces = input.nextInt();
                            while (pieces < 0 & pieces > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                pieces = input.nextInt();
                            }
                            choiceofequipment.add(pieces + " piece(s) of Paper");

                        }
                        if (choice == 4) {
                            System.out.println("How many Pencils? we can offer up to 20");
                            int Pencils = input.nextInt();
                            while (Pencils < 0 & Pencils > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Pencils = input.nextInt();
                            }
                            choiceofequipment.add(Pencils + " Pencils");
                        }

                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }

                    }
                }

                System.out.println("Would you like to book any Refreshments? Y or N");
                String Refresh = input.next();
                while (Refresh.equals("Y")) {
                    try {

                        System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                        int choice = input.nextInt();
                        String choiceS = String.valueOf(choice);
                        while (!choiceS.equals("0") & !choiceS.equals("1") & !choiceS.equals("2") & !choiceS.equals("3") & !choiceS.equals("4") & !choiceS.equals("5")) {
                            System.out.println("Sorry that input did not work, Please try again");
                            System.out.println("You have a choice of: Coke(1), Coffee(2), Water(3), Tea(4), Ham and Cheese Sandwich(5), Move on(0)");
                            choice = input.nextInt();
                            choiceS = String.valueOf(choice);
                        }
                        if (choice == 0) {
                            Refresh = "N";
                        }

                        if (choice == 1) {
                            System.out.println("How many Cokes? we can offer up to 15");
                            int Coke = input.nextInt();
                            while (Coke < 0 & Coke > 15) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coke = input.nextInt();
                            }
                            choiceofRefreshments.add(Coke + "Coke");
                        }
                        if (choice == 2) {

                            System.out.println("How many Coffees do you want? we can offer up to 20");
                            int Coffee = input.nextInt();
                            while (Coffee < 0 & Coffee > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Coffee = input.nextInt();
                            }
                            String CCoffee = String.valueOf(Coffee);
                            choiceofRefreshments.add(CCoffee + " Coffees");
                        }
                        if (choice == 3) {

                            System.out.println("How many cups of water? we can offer up to 50");
                            int Water = input.nextInt();
                            while (Water < 0 & Water > 50) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Water = input.nextInt();
                            }
                            choiceofRefreshments.add(Water + " Cups of water");

                        }
                        if (choice == 4) {
                            System.out.println("How many Teas would you like? we can offer 20 maximum");
                            int Teas = input.nextInt();
                            while (Teas < 0 & Teas > 20) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                Teas = input.nextInt();
                            }
                            choiceofRefreshments.add(Teas + " cups of teas");
                        }
                        if (choice == 5) {
                            System.out.println("How many Ham and Cheese Sandwiches? We can only offer 10 in maximum");
                            int HandC = input.nextInt();
                            while (HandC < 0 & HandC > 10) {
                                System.out.println("Sorry that input was not correct, please input another number");
                                HandC = input.nextInt();
                            }
                            choiceofRefreshments.add(HandC + " Ham and Cheese Sandwiches");
                        }
                        String Refreshments = choiceofRefreshments.toString();
                        choiceofRefreshments.clear();
                    } catch (Exception e) {
                        System.out.println("An error was found, would you like to restart the booking Y or N");
                        String YorN3 = input.next();
                        if (YorN3.equals("Y")) {
                            createabooking();
                        } else {
                            System.out.println("Sorry to see you go, bye");
                            System.exit(1);
                        }
                    }
                }
                System.out.println("You have finished your booking process");
                System.out.println("See you on" + CurrentBooking);
                String Refreshments = choiceofRefreshments.toString();
                choiceofRefreshments.clear();
                String Equipment = choiceofequipment.toString();
                choiceofequipment.clear();

                bookingroom room = new bookingroom(Fname, Lname, Numofpeople, UserEmailS, CurrentBooking, Refreshments, Equipment);
                listofpeople.add(room);

            }
            if (Numofpeople > 15 & help == true) {
                System.out.println("Unfortuneatly room 4, the only room with built assisstance can only accept a maximum of 15 people");
                System.out.println("Knowing this would you like to restart the booking process? Y or N");
                String YorN4 = input.next();
                if (YorN4.equals("Y")) {
                    System.out.println("The booking system will now restart");
                    createabooking();
                }
            }
        } catch (Exception e) {
            System.out.println("An error was found");
            System.out.println("Restarting the system");
        }
    }

    public static void deleteBooking() {
        //if booklist.is empty
        if (listofpeople.isEmpty()) {
            System.out.println("Sorry, there are no bookings to find");
        } else {
            System.out.println("Please type in your Email");
            String Email = input.next();

            int index = searchForEmail();
            if (index == -1) {
                System.out.println("Sorry that is not a valid Email");

            } else {
                listofpeople.remove(index);
            }
        }

    }

    public static int searchForEmail() {
        System.out.println("Please type in a Email");
        String Email = input.next();

        int index = -1;
        for (int i = 0; i < listofpeople.size(); i++) {
            if (Email.contains(listofpeople.get(i).getCurrentBooking())) {
                index = i;
                return i;
            }
        }
        return -1;
    }
//

    public static void editBook() {
        if (listofpeople.isEmpty()) {
            System.out.println("Sorry, there are no bookings right now");
        } else {
            String email = input.next();

            int index = searchForEmail();

            if (index == -1) {
                System.out.println("Sorry that is not a valid email you have entered");

            } else {
                System.out.println("");

                System.out.println("which bit do you want to edit");
                System.out.println("1 - First and Last name ");
                System.out.println("2 - Email");
                System.out.println("3 - time");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Please type a different First name");
                        String newFirstname = input.next();
                        System.out.println("Type in a Last name");
                        String newSurname = input.next();
                        listofpeople.get(index).setFname(newFirstname);
                        listofpeople.get(index).setLname(newSurname);
                        break;
                    case 2:
                        System.out.println("Please type in a new Email");
                        input.next();
                        String newEmail = input.nextLine();
                        listofpeople.get(index).setUserEmail(newEmail);
                        break;
                    case 3:
                        System.out.println("What year would you like to book the room");
                        int Year = input.nextInt();
                        int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                        while (Year < CurrentYear) {
                            System.out.println("Please enter in another Year an Error was found");
                            Year = input.nextInt();
                        }
                        System.out.println("what Month would you like to book the room");
                        int Month = input.nextInt();
                        int CurrentMonth = 1 + Calendar.getInstance().get(Calendar.MONTH);
                        while (Month == 0 || Month > 12) {
                            System.out.println("Please enter another Month an Error was found");
                            Month = input.nextInt();
                        }
                        if (CurrentYear == Year) {
                            while (Month < CurrentMonth) {
                                System.out.println("Please enter another Month as a Error was found");
                                Month = input.nextInt();
                            }
                        }
                        System.out.println("What day would you like to book the room");
                        int day = input.nextInt();
                        int Currentday = Calendar.getInstance().get(Calendar.DATE);
                        if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
                            while (day > 31 || day < 0) {
                                System.out.println("Please enter in another Day an Error was found");
                                day = input.nextInt();
                            }
                        }
                        if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
                            while (day > 30 || day < 0) {
                                System.out.println("Please enter in another Day an error was found");
                                day = input.nextInt();
                            }
                        }
                        if (Month == 2) {
                            while (day > 28 || day < 0) {
                                System.out.println("Please enter in another Day an Error was found");
                                day = input.nextInt();
                            }
                        }
                        if (CurrentYear == Year) {
                            while (Month == CurrentMonth) {
                                if (day < Currentday || day == Currentday) {
                                    System.out.println("The day entered was incorrect, please enter in another date");
                                    day = input.nextInt();
                                }
                            }
                        }
                        String YtoS = String.valueOf(Year);
                        String MtoS = String.valueOf(Month);
                        String DtoS = String.valueOf(day);

                        System.out.println("Our Company offer premium services, therefore we may offer you 3 dates on the dates you have suggested, meaning we are open all 365 days of the year");

                        System.out.println("10:30 , 14:30 , 17:00, you may have access to this room for a maximum 2 hours");
                        System.out.println("Which date would you like?");
                        String time = input.next();
                        while (!time.equals("10:30") & !time.equals("14:30") & !time.equals("17:00")) {
                            System.out.println("An Error was found please enter in another Time. 10:30 , 14:30 , 17:00");
                            time = input.next();
                        }
                        String CurrentBooking = DtoS + "/" + MtoS + "/" + YtoS + ":" + time;

                        listofpeople.get(index).setCurrentBooking(CurrentBooking);
                        break;

                }
            }
        }
    }
}
