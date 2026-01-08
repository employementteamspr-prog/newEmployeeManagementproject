package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Employee {
        @Id
        private int id;
        private String name;
        private String mail;
        private String phone;
        private String address;
        private String dateOfJoining;
        private int jobRoleId;

        
        public Employee() {
        }


        public Employee(int id, String name, String mail, String phone, String address, String dateOfJoining,
                int jobRoleId) {
            this.id = id;
            this.name = name;
            this.mail = mail;
            this.phone = phone;
            this.address = address;
            this.dateOfJoining = dateOfJoining;
            this.jobRoleId = jobRoleId;
        }


        public int getId() {
            return id;
        }


        public void setId(int id) {
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


        public String getDateOfJoining() {
            return dateOfJoining;
        }


        public void setDateOfJoining(String dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }


        public int getJobRoleId() {
            return jobRoleId;
        }


        public void setJobRoleId(int jobRoleId) {
            this.jobRoleId = jobRoleId;
        }
  
}
