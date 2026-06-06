class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private String status; // SCHEDULED, CANCELLED, COMPLETED
    
    // Constructor
    public Appointment(int appointmentId, int patientId, int doctorId, String appointmentDate, String appointmentTime) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "SCHEDULED";
    }
    
    // Getters
    public int getAppointmentId() { return appointmentId; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    
    // Setters
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + 
               ", Patient ID: " + patientId + 
               ", Doctor ID: " + doctorId + 
               ", Date: " + appointmentDate + 
               ", Time: " + appointmentTime + 
               ", Status: " + status;
    }
}
