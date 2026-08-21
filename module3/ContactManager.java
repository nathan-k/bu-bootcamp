import java.util.*; 
 
public class ContactManager { 
 
    public static void main(String[] args) { 
        HashMap<String, Contact> contacts = new HashMap<>(); 
 
        // Step 4: add contacts here
        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 555 0101")); 
        contacts.put("Nathan King", new Contact("Nathan King", "+1 309 321 1000")); 
        contacts.put("John Smith", new Contact("John Smith", "+1 312 552 0128")); 
        contacts.put("Jane Smith", new Contact("Jane Smith", "+1 312 748 0483")); 
        contacts.put("Robert Jones", new Contact("Robert Jones", "+1 575 224 8938")); 
 
        // Step 5: look up a contact 
        Contact ada = contacts.get("Ada Lovelace");
        Contact jason = contacts.get("Jason Jones");  // Non-existant contact

        if (ada != null) {
            System.out.println("Found: " + ada);
        } else {
            System.out.println("Could not find contact: Ada Lovelace");
        }

        if (jason != null) {
            System.out.println("Found: " + jason);
        } else {
            System.out.println("Could not find contact: Jason Jones");
        }
 
        // Step 6: print sorted list 
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());  
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));  

        System.out.println("\n=== All Contacts ==="); 
        for (Contact contact : sorted) { 
            System.out.println(contact); 
        } 
    } 
}