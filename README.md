<<<<<<< HEAD
# Doctor Channeling System

## Overview
This is a complete Doctor Channeling System developed for XYZ Pvt Ltd using only custom Queue data structure and arrays (no built-in data structures like ArrayList, LinkedList, etc.).

## Features Implemented

### 1. Patient Registration
- Patient name, Mobile number, Email ID, City, Age, Medical History
- Auto-generated Patient ID
- Notification system for successful registration

### 2. Doctor Registration
- Doctor ID, Name, Specialization, Available Time Slots, Consultation Fee
- Auto-generated Doctor ID
- Complete doctor profile management

### 3. Search Functionality
- Patients can search for available doctors by specialization
- Case-insensitive search
- Displays all matching doctors with their details

### 4. Appointment Booking
- Patients can book appointments with available doctors
- Automatic slot availability checking
- Immediate notification to patient upon booking
- All appointments saved for future reference

### 5. Appointment Cancellation
- Patients can cancel appointments at any time
- Automatic notification to patient upon cancellation
- Next patient in waiting queue automatically assigned the slot
- Automatic notification to next patient

### 6. Reschedule Requests
- Patients can request appointment rescheduling
- Patient automatically added to waiting queue
- Notification system for reschedule requests

### 7. Complete Appointment Display
- View all scheduled appointments
- Detailed appointment information
- Patient and doctor details for each appointment

## Technical Implementation

### Custom Queue Data Structure
- Circular array implementation
- Operations: enqueue, dequeue, peek, isEmpty, isFull
- Special remove operation for specific elements
- Display functionality for queue contents

### Array-based Storage
- **Patient Array**: Stores up to 100 patients
- **Doctor Array**: Stores up to 50 doctors  
- **Appointment Array**: Stores up to 200 appointments
- **Waiting Queue**: Handles up to 50 waiting patients

### Classes Structure
1. **Queue.java**: Custom queue implementation
2. **Patient.java**: Patient entity with all required fields
3. **Doctor.java**: Doctor entity with all required fields
4. **Appointment.java**: Appointment entity with status tracking
5. **DoctorChannelingSystem.java**: Main system with all functionality

## How to Run

1. Compile all Java files:
   ```bash
   javac *.java
   ```

2. Run the main system:
   ```bash
   java DoctorChannelingSystem
   ```

## System Menu Options

1. **Register Patient**: Add new patient to the system
2. **Register Doctor**: Add new doctor to the system
3. **Search Doctors**: Find doctors by specialization
4. **Book Appointment**: Schedule appointment with available doctor
5. **Cancel Appointment**: Cancel existing appointment
6. **Request Reschedule**: Request appointment rescheduling
7. **View All Appointments**: Display all appointments
8. **View Waiting Queue**: Show patients waiting for rescheduling
9. **View All Doctors**: List all registered doctors
10. **View All Patients**: List all registered patients
11. **Exit**: Close the application

## Key Features

### Notification System
- Automatic SMS-style notifications for all patient interactions
- Notifications for: registration, appointment booking, cancellation, rescheduling, slot assignment

### Queue Management
- Waiting queue for patients requesting reschedules
- Automatic assignment of cancelled slots to waiting patients
- FIFO (First In, First Out) processing

### Data Validation
- Patient and doctor existence validation
- Appointment slot availability checking
- Status-based operation restrictions

### Status Tracking
- Appointment status: SCHEDULED, CANCELLED, COMPLETED
- Real-time status updates
- Comprehensive appointment history

## System Limitations
- Maximum 100 patients
- Maximum 50 doctors
- Maximum 200 appointments
- Maximum 50 patients in waiting queue

This system fulfills all the requirements specified in the scenario using only custom Queue implementation and arrays, without any built-in data structures.
=======
# Doctor-Channeling-System
A Java-based Doctor Channelling System developed using Static Arrays and Queue data structures. The system allows patient and doctor registration, doctor searching, appointment booking, cancellation, rescheduling, waiting-list management, and appointment tracking through a console-based interface.
>>>>>>> 1a133c7d9ef1ad281e76d1878425c22c27f47b89
