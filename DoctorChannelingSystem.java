import java.util.Scanner;

class DoctorChannelingSystem { //main class
    // Static Arrays to store data in memory
    private static Patient[] patients = new Patient[100]; // STATIC - Fixed 100 slots
    private static Doctor[] doctors = new Doctor[50];  // STATIC - Fixed 50 slots
    private static Appointment[] appointments = new Appointment[200];    //STATIC - Fixed 200 slots
    
    // Counters
    //integer variables to keep track of the number of patients, doctors, and appointments registered in the system.
    //These counters help manage the arrays and ensure new entries are added correctly without exceeding array bounds
    private static int patientCount = 0;  
    private static int doctorCount = 0;
    private static int appointmentCount = 0;
    
    // Auto-increment IDs
    // These variables are used to generate unique IDs for new patients, doctors, and appointments.
    // Each time a new entity is created, the corresponding ID is assigned and then incremented for the next use.
    private static int nextPatientId = 1;
    private static int nextDoctorId = 1;
    private static int nextAppointmentId = 1;
    
    // Queue class object for waiting patients (for rescheduling)
    private static Queue waitingQueue = new Queue(50); //queue object for holding patients IDs who are waiting for appointment slot, max 50
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  //the scanner name input is used to read user input from the console.
        
        System.out.println("=== DOCTOR CHANNELING SYSTEM ===");
        System.out.println("Welcome to XYZ Pvt Ltd Healthcare System");
        
        while (true) {         //Starts an infinite loop to repeatedly show the menu and handle user choices.
            displayMenu();     //call displayMenu() method to show the main menu options to the user.
            System.out.print("Enter your choice: ");
            
            int choice = input.nextInt();  //Reads an integer choice from the user.
            input.nextLine(); // consume newline
            
            switch (choice) {  //A switch handles each menu option by calling the appropriate method.
                case 1:
                    registerPatient(input);
                    break;
                case 2:
                    registerDoctor(input);
                    break;
                case 3:
                    searchDoctors(input);
                    break;
                case 4:
                    bookAppointment(input);
                    break;
                case 5:
                    cancelAppointment(input);
                    break;
                case 6:
                    requestReschedule(input);
                    break;
                case 7:
                    displayAllAppointments();
                    break;
                case 8:
                    displayWaitingQueue();
                    break;
                case 9:
                    displayAllDoctors();
                    break;
                case 10:
                    displayAllPatients();
                    break;
                case 11:    //prints a goodbye message, closes the scanner, and returns from main to exit the program.
                    System.out.println("Thank you for using Doctor Channeling System!");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again."); //handles invalid menu choices by prompting the user to try again.
            }
        }
    }
    
    private static void displayMenu() {      //Prints the textual main menu. No logic — only formatting and option labels.
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Register Patient");
        System.out.println("2. Register Doctor");
        System.out.println("3. Search Doctors");
        System.out.println("4. Book Appointment");
        System.out.println("5. Cancel Appointment");
        System.out.println("6. Request Reschedule");
        System.out.println("7. View All Appointments");
        System.out.println("8. View Waiting Queue");
        System.out.println("9. View All Doctors");
        System.out.println("10. View All Patients");
        System.out.println("11. Exit");
    }
    
    private static void registerPatient(Scanner input) {  
        if (patientCount >= patients.length) {   //Checks if the patients array is full. If so, prints a message and returns.
            System.out.println("Patient registration limit reached!"); //error message
            return;
        }
        
        System.out.println("\n=== PATIENT REGISTRATION ==="); //get patient details from user
        System.out.print("Enter patient name: ");
        String name = input.nextLine();
        
        System.out.print("Enter mobile number: ");
        String mobile = input.nextLine();
        
        System.out.print("Enter email ID: ");
        String email = input.nextLine();
        
        System.out.print("Enter city: ");
        String city = input.nextLine();
        
        System.out.print("Enter age: ");
        int age = input.nextInt();
        input.nextLine(); // consume newline
        
        System.out.print("Enter medical history: ");
        String medicalHistory = input.nextLine();
        
        //Creates a new Patient object using nextPatientId as the patient ID.
        Patient newPatient = new Patient(nextPatientId, name, mobile, email, city, age, medicalHistory);
        patients[patientCount] = newPatient; //Stores it in the patients array at index patientCount.
        patientCount++;  //increments patientCount to reflect the new total number of patients.
        nextPatientId++;  //increments nextPatientId to ensure the next patient gets a unique ID.
        
        System.out.println("Patient registered successfully! Patient ID: " + newPatient.getPatientId()); //Prints confirmation with the newly assigned patient ID.
        notifyPatient(newPatient, "Registration successful! Welcome to XYZ Healthcare System."); //calls notifyPatient() to simulate sending a notification to the patient.
    }
    
    private static void registerDoctor(Scanner input) {
        if (doctorCount >= doctors.length) {  //check whether the doctors array is full. If it is, print an error message and return.
            System.out.println("Doctor registration limit reached!");
            return;
        }
        
        System.out.println("\n=== DOCTOR REGISTRATION ==="); //get doctor details from user
        System.out.print("Enter doctor name: ");
        String name = input.nextLine();
        
        System.out.print("Enter specialization: ");
        String specialization = input.nextLine();
        
        System.out.print("Enter available time slots (e.g., 9:00AM-12:00PM): ");
        String timeSlots = input.nextLine();
        
        System.out.print("Enter consultation fee: ");
        double fee = input.nextDouble();
        //creates a new Doctor object using nextDoctorId as the doctor ID.
        Doctor newDoctor = new Doctor(nextDoctorId, name, specialization, timeSlots, fee);
        doctors[doctorCount] = newDoctor;//stores it in the doctors array at index doctorCount.
        doctorCount++;//increments doctorCount to reflect the new total number of doctors.
        nextDoctorId++;//increments nextDoctorId to ensure the next doctor gets a unique ID.
        
        System.out.println("Doctor registered successfully! Doctor ID: " + newDoctor.getDoctorId());//Prints confirmation with the newly assigned doctor ID.
    }
    
    private static void searchDoctors(Scanner input) {  //search doctors by specialization
        System.out.println("\n=== SEARCH DOCTORS ===");
        System.out.print("Enter specialization to search: "); //Prompts user to enter a specialization
        String specialization = input.nextLine().toLowerCase(); //reads it, and converts to lowercase for case-insensitive matching.
        
        boolean found = false; //flag to track if any matching doctors are found.
        System.out.println("\nAvailable Doctors:");
        System.out.println("==================");
        
        for (int i = 0; i < doctorCount; i++) { //Iterates through the doctors array up to doctorCount.
            //Checks if the doctor's specialization (converted to lowercase) contains the search term.
            if (doctors[i].getSpecialization().toLowerCase().contains(specialization)) {
                System.out.println(doctors[i]);
                found = true;
            }
        }
        
        if (!found) {  //if no matching doctors were found, prints a message indicating that.
            System.out.println("No doctors found with specialization: " + specialization);
        }
    }
    
    private static void bookAppointment(Scanner input) {
        System.out.println("\n=== BOOK APPOINTMENT ===");
        
        // Display all patients
        displayAllPatients();//shows all registered patients to help user select a patient ID.
        System.out.print("Enter patient ID: "); //prompts user to enter the patient ID for whom the appointment is being booked.
        int patientId = input.nextInt(); //reads the patient ID.
        
        // Display all doctors
        displayAllDoctors(); //shows all registered doctors to help user select a doctor ID.
        System.out.print("Enter doctor ID: "); //prompts user to enter the doctor ID with whom the appointment is being booked.
        int doctorId = input.nextInt();  //reads the doctor ID.
        input.nextLine(); // consume newline
        
        System.out.print("Enter appointment date (DD/MM/YYYY): ");//prompts user to enter the desired appointment date.
        String date = input.nextLine(); //reads the date.
        
        System.out.print("Enter appointment time (HH:MM AM/PM): "); //prompts user to enter the desired appointment time.
        String time = input.nextLine(); //reads the time.
        
        // Validate patient and doctor exist
        Patient patient = findPatientById(patientId); //looks up the patient and doctor objects based on the entered IDs.
        Doctor doctor = findDoctorById(doctorId);
        
        if (patient == null) { //if either is not found, prints an error message and returns.
            System.out.println("Patient not found!");
            return;
        }
        
        if (doctor == null) { //if either is not found, prints an error message and returns.
            System.out.println("Doctor not found!");
            return;
        }
        
        // Check if appointment slot is available
        if (isSlotAvailable(doctorId, date, time)) {  //calls isSlotAvailable() to check if the requested slot is available.
            Appointment newAppointment = new Appointment(nextAppointmentId, patientId, doctorId, date, time); //If available, creates a new 'Appointment' object with nextAppointmentId.
            appointments[appointmentCount] = newAppointment; //stores it in the appointments array at index appointmentCount.
            appointmentCount++; //increments appointmentCount to reflect the new total number of appointments.
            nextAppointmentId++;    //increments nextAppointmentId to ensure the next appointment gets a unique ID.
            
            System.out.println("Appointment booked successfully!"); //Prints confirmation and appointment ID.
            System.out.println("Appointment ID: " + newAppointment.getAppointmentId());
            
            // Notify patient
            notifyPatient(patient, "Appointment confirmed with Dr. " + doctor.getName() +  //calls notifyPatient() to simulate sending a notification to the patient about the booked appointment.
                         " on " + date + " at " + time + ". Fee: Rs." + doctor.getConsultationFee());
        } else { //if not available, offers to add the patient to the waiting queue.
            System.out.println("Sorry, this slot is not available. Would you like to join the waiting queue? (y/n)"); //prompt
            String choice = input.nextLine(); //read user input
            if (choice.equalsIgnoreCase("y")) { //if user agrees (y/Y), attempts to enqueue the patient ID into waitingQueue.
                if (waitingQueue.enqueue(patientId)) {  //if successful, confirms addition to the queue and notifies the patient.
                    System.out.println("You have been added to the waiting queue.");  //confirmation message
                    notifyPatient(patient, "You have been added to the waiting queue for Dr. " + doctor.getName()); //calls notifyPatient() to inform the patient.
                } else {
                    System.out.println("Waiting queue is full!"); //if the queue is full, informs the user that they cannot be added.
                }
            }
        }
    }
    
    private static void cancelAppointment(Scanner input) { //cancel an existing appointment
        System.out.println("\n=== CANCEL APPOINTMENT ===");
        displayScheduledAppointments();  //calls displayScheduledAppointments() to show all appointments that can be cancelled.
        
        System.out.print("Enter appointment ID to cancel: "); //gets the appointment ID to cancel from the user.
        int appointmentId = input.nextInt(); //reads the appointment ID.
        
        Appointment appointment = findAppointmentById(appointmentId); //checks if the appointment exists by calling findAppointmentById().
        if (appointment == null) {  //if not found, prints an error message and returns.
            System.out.println("Appointment not found!");
            return;
        }
        //if appointment found in the system, checks if the appointment status is 'SCHEDULED'.
        if (!appointment.getStatus().equals("SCHEDULED")) { //if the appointment is not in 'SCHEDULED' status (i.e., already cancelled or completed), it cannot be cancelled again.
            System.out.println("ERROR: Cannot cancel this appointment!"); //error message
            System.out.println("Reason: This appointment is already " + appointment.getStatus());
            System.out.println("TIP: You can only cancel appointments with 'SCHEDULED' status.");
            return;
        }
        
        appointment.setStatus("CANCELLED"); //if valid, sets the appointment status to 'CANCELLED'.
        
        Patient patient = findPatientById(appointment.getPatientId()); //retrieves the patient and doctor objects associated with the appointment.
        Doctor doctor = findDoctorById(appointment.getDoctorId());
        
        System.out.println("Appointment cancelled successfully!"); //confirms the cancellation to the user.
        
        // Notify patient
        if (patient != null) { //calls notifyPatient() to inform the patient about the cancellation.
            notifyPatient(patient, "Your appointment with Dr. " + 
                         (doctor != null ? doctor.getName() : "Unknown") + 
                         " on " + appointment.getAppointmentDate() + " has been cancelled.");
        }
        
        // Check waiting queue and assign slot to next patient
        if (!waitingQueue.isEmpty()) {  //if there are patients in the waiting queue, dequeues the next patient ID.
            int nextPatientId = waitingQueue.dequeue(); //removes and retrieves the next patient ID from the waiting queue.
            Patient nextPatient = findPatientById(nextPatientId); //looks up the patient object for that ID.
            
            if (nextPatient != null) { //if found, creates a new appointment for that patient in the cancelled slot.
                // Create new appointment for waiting patient
                Appointment newAppointment = new Appointment(nextAppointmentId, nextPatientId, //uses nextAppointmentId for the new appointment ID.
                                                           appointment.getDoctorId(), //reuses the same doctor ID, date, and time from the cancelled appointment.
                                                           appointment.getAppointmentDate(), 
                                                           appointment.getAppointmentTime());
                appointments[appointmentCount] = newAppointment; //stores it in the appointments array.
                appointmentCount++; //increments appointmentCount.
                nextAppointmentId++; //increments nextAppointmentId.
                
                notifyPatient(nextPatient, "Good news! You have been assigned the appointment slot with Dr. " + //calls notifyPatient() to inform the patient about their new appointment.
                             (doctor != null ? doctor.getName() : "Unknown") + 
                             " on " + appointment.getAppointmentDate() + " at " + appointment.getAppointmentTime());
                
                System.out.println("Next patient from waiting queue has been assigned this slot.");
            }
        }
    }
    
    private static void requestReschedule(Scanner input) { //request to reschedule an existing appointment
        System.out.println("\n=== REQUEST RESCHEDULE ===");
        displayAllAppointments(); //calls displayAllAppointments() to show all appointments.
        
        System.out.print("Enter appointment ID to reschedule: "); //gets the appointment ID to reschedule from the user.
        int appointmentId = input.nextInt(); //reads the appointment ID.
        
        Appointment appointment = findAppointmentById(appointmentId); //checks if the appointment exists by calling findAppointmentById().
        if (appointment == null) {  //if not found, prints an error message and returns.
            System.out.println("Appointment not found!");
            return;
        }
        //when an appointment is found, checks if its status is 'SCHEDULED'.
        if (!appointment.getStatus().equals("SCHEDULED")) { //if not, it cannot be rescheduled.
            System.out.println("This appointment cannot be rescheduled (Status: " + appointment.getStatus() + ")");//error message
            return;
        }
        //if that status is 'SCHEDULED', proceeds to cancel the appointment by setting its status to 'CANCELLED'.
        // Cancel current appointment
        appointment.setStatus("CANCELLED");
        
        // Add patient to waiting queue
        if (waitingQueue.enqueue(appointment.getPatientId())) { //attempts to add the patient ID associated with the cancelled appointment to the waiting queue.
            Patient patient = findPatientById(appointment.getPatientId()); //looks up the patient object.
            System.out.println("Reschedule request processed. You have been added to the waiting queue."); //confirms addition to the queue.
            
            if (patient != null) { //if the patient is found, calls notifyPatient() to inform them about the reschedule request and their addition to the waiting queue.
                notifyPatient(patient, "Your reschedule request has been processed. You are now in the waiting queue.");
            }
        } else { //if the waiting queue is full, informs the user that they cannot be added.
            System.out.println("Waiting queue is full! Please try again later."); //error message
        }
    }
    
    private static void displayScheduledAppointments() { //displays all appointments that are currently in 'SCHEDULED' status.
        System.out.println("\n=== SCHEDULED APPOINTMENTS (Available for Cancellation) ===");
        boolean hasScheduled = false; //flag to track if any scheduled appointments are found.
        
        for (int i = 0; i < appointmentCount; i++) {  //Iterates through the appointments array up to appointmentCount.
            Appointment apt = appointments[i]; //For each appointment, checks if its status is 'SCHEDULED'.
            if (apt.getStatus().equals("SCHEDULED")) {
                Patient patient = findPatientById(apt.getPatientId()); //If so, retrieves the associated patient and doctor objects.
                Doctor doctor = findDoctorById(apt.getDoctorId()); 
                
                System.out.println("Appointment ID: " + apt.getAppointmentId()); //Prints the appointment details.
                System.out.println("Patient: " + (patient != null ? patient.getName() : "Unknown"));
                System.out.println("Doctor: " + (doctor != null ? doctor.getName() : "Unknown"));
                System.out.println("Date: " + apt.getAppointmentDate());
                System.out.println("Time: " + apt.getAppointmentTime());
                System.out.println("Status: " + apt.getStatus());
                System.out.println("-------------------------");
                hasScheduled = true;
            }
        }
        //if no scheduled appointments were found, prints a message indicating that.
        if (!hasScheduled) {
            System.out.println("No scheduled appointments available");
        }
    }
    
    private static void displayAllAppointments() { //displays all appointments in the system, regardless of their status.
        System.out.println("\n=== ALL APPOINTMENTS ===");
        if (appointmentCount == 0) {  //if there are no appointments, prints a message indicating that and returns.
            System.out.println("No appointments found.");
            return;
        }
        //if there are appointments, iterates through the appointments array up to appointmentCount.
        for (int i = 0; i < appointmentCount; i++) { //for each appointment, retrieves the associated patient and doctor objects.
            Appointment apt = appointments[i]; 
            Patient patient = findPatientById(apt.getPatientId());
            Doctor doctor = findDoctorById(apt.getDoctorId());
            
            System.out.println("Appointment ID: " + apt.getAppointmentId()); //Prints the appointment details.
            System.out.println("Patient: " + (patient != null ? patient.getName() : "Unknown"));
            System.out.println("Doctor: " + (doctor != null ? doctor.getName() : "Unknown"));
            System.out.println("Date: " + apt.getAppointmentDate());
            System.out.println("Time: " + apt.getAppointmentTime());
            System.out.println("Status: " + apt.getStatus());
            System.out.println("-------------------------");
        }
    }
    
    private static void displayWaitingQueue() {   //displays the current state of the waiting queue.
        System.out.println("\n=== WAITING QUEUE ===");
        if (waitingQueue.isEmpty()) {   //if the queue is empty, prints a message indicating that.
            System.out.println("Waiting queue is empty.");
        } else {  //if not empty, calls the display() method of the Queue class to show all patient IDs currently in the queue.
            System.out.println("Patients in waiting queue:");
            waitingQueue.display();
        }
    }
    
    private static void displayAllDoctors() {  //displays all registered doctors in the system.
        System.out.println("\n=== ALL DOCTORS ===");
        if (doctorCount == 0) {  //if there are no doctors, prints a message indicating that and returns.
            System.out.println("No doctors registered.");
            return;
        }
        //if there are doctors, iterates through the doctors array up to doctorCount and prints each doctor's details.
        for (int i = 0; i < doctorCount; i++) { //for each doctor, prints their details.
            System.out.println(doctors[i]);
        }
    }
    
    private static void displayAllPatients() { //displays all registered patients in the system.
        System.out.println("\n=== ALL PATIENTS ===");
        if (patientCount == 0) { //if there are no patients, prints a message indicating that and returns.
            System.out.println("No patients registered.");
            return;
        }
        //if there are patients, iterates through the patients array up to patientCount and prints each patient's details.
        for (int i = 0; i < patientCount; i++) {
            System.out.println(patients[i]);
        }
    }
    
    // Helper methods
    private static Patient findPatientById(int patientId) {  //looks up a patient by their ID.
        for (int i = 0; i < patientCount; i++) {  //Iterates through the patients array up to patientCount.
            if (patients[i].getPatientId() == patientId) { //If a match is found, returns the corresponding Patient object.
                return patients[i]; 
            }
        }
        return null;//if no match is found, returns null.
    }
    
    private static Doctor findDoctorById(int doctorId) {  //looks up a doctor by their ID.
        for (int i = 0; i < doctorCount; i++) { //Iterates through the doctors array up to doctorCount.
            if (doctors[i].getDoctorId() == doctorId) { //If a match is found, returns the corresponding Doctor object. 
                return doctors[i];
            }
        }
        return null; //if no match is found, returns null.
    }
    
    private static Appointment findAppointmentById(int appointmentId) {   //looks up an appointment by its ID.
        //Iterates through the appointments array up to appointmentCount.
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId() == appointmentId) {
                return appointments[i]; //If a match is found, returns the corresponding Appointment object.
            }
        }
        return null; //if no match is found, returns null.
    }
    //checks if a specific appointment slot is available for a given doctor on a given date and time.
    private static boolean isSlotAvailable(int doctorId, String date, String time) { 
        //Iterates through the appointments array up to appointmentCount.
        for (int i = 0; i < appointmentCount; i++) {
            Appointment apt = appointments[i]; 
            if (apt.getDoctorId() == doctorId &&  //If an appointment exists with the same doctor ID, date, and time, and its status is 'SCHEDULED', the slot is not available.
                apt.getAppointmentDate().equals(date) && 
                apt.getAppointmentTime().equals(time) && 
                apt.getStatus().equals("SCHEDULED")) {
                return false;
            }
        }
        return true; //if no conflicting appointment is found, returns true indicating the slot is available.
    }
    
    private static void notifyPatient(Patient patient, String message) { //simulates sending a notification to a patient by printing a message to the console.
        //In a real system, this could be an email or SMS notification.
        System.out.println("\n*** NOTIFICATION ***");
        System.out.println("To: " + patient.getName() + " (" + patient.getMobile() + ")"); //prints the patient's name and mobile number.
        System.out.println("Message: " + message); //prints the notification message.
        System.out.println("********************");
    }
}
