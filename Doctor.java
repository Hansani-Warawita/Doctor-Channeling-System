class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private String availableTimeSlots;
    private double consultationFee;
    
    // Constructor
    public Doctor(int doctorId, String name, String specialization, String availableTimeSlots, double consultationFee) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.availableTimeSlots = availableTimeSlots;
        this.consultationFee = consultationFee;
    }
    
    // Getters
    public int getDoctorId() { return doctorId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getAvailableTimeSlots() { return availableTimeSlots; }
    public double getConsultationFee() { return consultationFee; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setAvailableTimeSlots(String availableTimeSlots) { this.availableTimeSlots = availableTimeSlots; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
    
    @Override
    public String toString() {
        return "Doctor ID: " + doctorId + 
               ", Name: " + name + 
               ", Specialization: " + specialization + 
               ", Available Time: " + availableTimeSlots + 
               ", Consultation Fee: Rs." + consultationFee;
    }
}
