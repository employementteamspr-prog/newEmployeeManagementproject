package com.examly.springapp.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
        @Id
        private Long id;
        private String name;
        private String mail;
        private String phone;
        private String address;
        private LocalDate dateOfJoining;
        private Long jobRoleId;

        @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
        private List<Document> documents;

        @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
        private List<MonthlyPayroll> monthlyPayrolls;
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "departmentId")
        private Department department;  

        @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
        private List<Review> reviews;

        @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY)
        private List<Review> reviewsGiven;

        @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
        private User user;

        
        public Employee() {
        }


        public Employee(Long id, String name, String mail, String phone, String address, LocalDate dateOfJoining,
                Long jobRoleId, User user, Department department) {
            this.id = id;
            this.name = name;
            this.mail = mail;
            this.phone = phone;
            this.address = address;
            this.dateOfJoining = dateOfJoining;
            this.jobRoleId = jobRoleId;
            this.user = user;
            this.department = department;

    }       

        


        public Long getId() {
            return id;
        }


        public void setId(Long id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getMail() {
            return mail;
        }


        public void setMail(String mail) {
            this.mail = mail;
        }


        public String getPhone() {
            return phone;
        }


        public void setPhone(String phone) {
            this.phone = phone;
        }


        public String getAddress() {
            return address;
        }


        public void setAddress(String address) {
            this.address = address;
        }


        public LocalDate getDateOfJoining() {
            return dateOfJoining;
        }


        public void setDateOfJoining(LocalDate dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }


        public Long getJobRoleId() {
            return jobRoleId;
        }


        public void setJobRoleId(Long jobRoleId) {
            this.jobRoleId = jobRoleId;
        }

        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
        public Department getDepartment() {
            return department;
        }
        public void setDepartment(Department department) {
            this.department = department;
        }

        public List<Document> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Document> documents) {
            this.documents = documents;
        }
    
        public List<MonthlyPayroll> getMonthlyPayrolls() {
            return monthlyPayrolls;
        }
        public void setMonthlyPayrolls(List<MonthlyPayroll> monthlyPayrolls) {
            this.monthlyPayrolls = monthlyPayrolls;
        }
        public List<Review> getReviews() {
            return reviews;
        }
        public void setReviews(List<Review> reviews) {
            this.reviews = reviews;
        }
        public List<Review> getReviewsGiven() {
            return reviewsGiven;
        }
        public void setReviewsGiven(List<Review> reviewsGiven) {
            this.reviewsGiven = reviewsGiven;
        }
        
  
}