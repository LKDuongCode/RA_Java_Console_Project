package ra.edu.business.model.candidate;

import ra.edu.business.model.person.Person;
import ra.edu.business.model.person.PersonStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Candidate extends Person {
    private String phone;
    private Gender gender;
    private int experience;
    private LocalDate dob;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Candidate() {}

    public Candidate(int id, String name, String email, String password, PersonStatus status, String phone, Gender gender, int experience, LocalDate dob, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, name, email, password, status);
        this.phone = phone;
        this.gender = gender;
        this.experience = experience;
        this.dob = dob;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
