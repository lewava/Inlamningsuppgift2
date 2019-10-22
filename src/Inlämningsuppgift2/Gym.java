package Inlämningsuppgift2;

import javax.swing.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Gym extends Customer {

    public Gym() {}

    public Gym(String name, String personNr, LocalDate gymFee) {
        super(name, personNr, gymFee);
    }

    public static void main(String[] args) {
        List<Customer> gymCustomer = readFile();
        try {
            while (true) {
                String input = JOptionPane.showInputDialog("Skriv in namn eller personnummer.");
                if (input == null) {
                    System.exit(0);
                }
                input.trim();
                Customer c1 = searchCustomer(input, gymCustomer);
                if (c1 == null) {
                    JOptionPane.showMessageDialog(null, "Personen finns inte i systemet.");
                }
                if (activeMember(c1)) {
                    JOptionPane.showMessageDialog(null, c1.name + " har ett aktivt medlemskap.");
                    writeToFile(c1, "C:\\Users\\Leon_\\IdeaProjects\\Inlämningsuppgift 2 OOP\\src\\LastTrained.txt");

                } else {
                    JOptionPane.showMessageDialog(null, c1.name + " finns i systemet men har inget aktivt medlemskap");
                }
            }
        } catch (NullPointerException e) {
            System.exit(0);
        } catch (Exception e2) {
            System.out.println("Ett fel inträffade");
            e2.printStackTrace();
            System.exit(0);
        }
    }

    public static List<Customer> readFile() {
        String file = "C:\\Users\\Leon_\\IdeaProjects\\Inlämningsuppgift 2 OOP\\src\\Inlämningsuppgift2\\Customers.txt";
        String firstLine;
        String personNr;
        String name;
        String date;
        LocalDate gymFee;

        List<Customer> gymCustomer = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(file));) {
            while (sc.hasNextLine()) {

                firstLine = sc.nextLine();
                firstLine.trim();
                personNr = firstLine.substring(0, 10);
                name = firstLine.substring(12);
                date = sc.nextLine();
                date.trim();
                gymFee = LocalDate.parse(date);
                Customer customer = new Customer(name, personNr, gymFee);
                gymCustomer.add(customer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e2) {
            System.out.println("Ett fel inträffade");
            e2.printStackTrace();
            System.exit(0);
        }
        return gymCustomer;
    }

    public static Customer searchCustomer(String input, List<Customer> list) throws NullPointerException {
        Customer c = null;
        for (Customer customer : list) {
            if (customer.getName().equalsIgnoreCase(input) || customer.personNr.equals(input)) {
                c = customer;
            }
        }
        return c;
    }

    public static boolean activeMember(Customer customer) {
        return customer.getGymFee().plusYears(1).isAfter(LocalDate.now())
                || customer.getGymFee().plusYears(1).equals(LocalDate.now());
    }

    public static void writeToFile(Customer customer, String file) {
        try (FileWriter fw = new FileWriter(file,true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
            String info = String.format("Namn: %-10s\nPersonnummer: %-10s\nDatum: %-10s\n\n",
                    customer.name, customer.personNr, LocalDateTime.now().format(date));
            bw.write(info);
        } catch (Exception e) {
            System.out.println("Ett fel inträffade");
            e.printStackTrace();
            System.exit(0);
        }
    }
}