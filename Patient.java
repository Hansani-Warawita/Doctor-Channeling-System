class Patient {   //patient class or entity
    private int patientId;  // these are the variables or attributes of the patient class
    private String name;    //private means encapsulation – the variables can’t be accessed directly from outside the class.
    private String mobile; //to access these variables, we use getter and setter methods. controlled the internal data access.
    private String email;
    private String city;
    private int age;
    
    private String medicalHistory;
    
    // Constructor
    //this is parameterized constructor, it means this accepts one or more arguments.
    //a special method that initializes an object when it’s created.
    public Patient(int patientId, String name, String mobile, String email, String city, int age, String medicalHistory) {
        this.patientId = patientId;   // 'this' keyword refers to the current object's variable,field or attribute.
        this.name = name;             //When register a new Patient the constructor assigns values to fields.
        this.mobile = mobile;
        this.email = email;
        this.city = city;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }
    
    // Getters
    public int getPatientId() { return patientId; }  // Getter methods provide read-only access to private variables.
    public String getName() { return name; }         //patient.getName() returns the name of the patient without exposing the private variable directly.
    public String getMobile() { return mobile; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public int getAge() { return age; }
    public String getMedicalHistory() { return medicalHistory; }
    
    // Setters
    public void setName(String name) { this.name = name; }  //They allow updating values of private variables or fields while keeping control.
    public void setMobile(String mobile) { this.mobile = mobile; }  //as a Example: patient.setAge(35); updates the age of a patient.
    public void setEmail(String email) { this.email = email; } //This shows data hiding and controlled modification.
    public void setCity(String city) { this.city = city; }
    public void setAge(int age) { this.age = age; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
    
    @Override  //polymorphism - method overriding
    public String toString() {
        return "Patient ID: " + patientId + //toString() is a method inherited from the Object class (every Java class extends Object implicitly).
               ", Name: " + name + 
               ", Mobile: " + mobile +    //as a Example: System.out.println(patient); will automatically use this method to display the patient info.
               ", Email: " + email + 
               ", City: " + city +      
               ", Age: " + age + 
               ", Medical History: " + medicalHistory;
    }
}
