import java.io.*;
import java.util.*;

public class ContactManager {
    private static List<Contact> contacts = new ArrayList<>();
    private static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        loadContacts();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Contact Manager ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addContact(scanner);
                case 2 -> viewContacts();
                case 3 -> searchContact(scanner);
                case 4 -> updateContact(scanner);
                case 5 -> deleteContact(scanner);
                case 6 -> {
                    saveContacts();
                    System.out.println("Exiting... Contacts saved!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter Name to search: ");
        String name = scanner.nextLine();
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                System.out.println("Contact Found:\n" + c);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void updateContact(Scanner scanner) {
        System.out.print("Enter Name to update: ");
        String name = scanner.nextLine();
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new Phone (Leave blank to keep current): ");
                String newPhone = scanner.nextLine();
                if (!newPhone.isEmpty())
                    c.setPhone(newPhone);

                System.out.print("Enter new Email (Leave blank to keep current): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty())
                    c.setEmail(newEmail);

                System.out.println("Contact updated successfully!");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter Name to delete: ");
        String name = scanner.nextLine();
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Contact deleted.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private static void loadContacts() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            @SuppressWarnings("unchecked")
            List<Contact> loadedContacts = (List<Contact>) ois.readObject();
            contacts = (loadedContacts != null) ? loadedContacts : new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }
}
