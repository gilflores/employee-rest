package org.directory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private long id;

    @Column(name="ssn")
    private String ssn;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="second_last_name")
    private String secondLastName;

    @Column(name="birthdate")
    @JsonFormat(pattern="MM/dd/yyyy")
    private LocalDate birthdate;

    public Employee() {}

    public Employee(final long id, final String ssn, final String name,
                    final String lastName, final String secondLast,
                    final LocalDate birthdate) {
        this.id = id;
        this.ssn = ssn;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLast;
        this.birthdate = birthdate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", ssn=" + ssn + ", name=" + name + ", lastName=" + lastName
                + ", lastName=" + secondLastName + ", birthdate=" + birthdate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (ssn == null) {
            if (other.ssn != null)
                return false;
        } else if (!ssn.equals(other.ssn))
            return false;
        return true;
    }
}
