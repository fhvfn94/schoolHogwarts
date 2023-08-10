package com.book.Book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Reader {
    @Id
    @GeneratedValue
    private Long id;
    private int personalNumber;
    private String firstName;
    private String secondName;
    private String middleName;

    @OneToMany(mappedBy = "reader")
    private Collection<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return personalNumber == reader.personalNumber && Objects.equals(id, reader.id) && Objects.equals(firstName, reader.firstName) && Objects.equals(secondName, reader.secondName) && Objects.equals(middleName, reader.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalNumber, firstName, secondName, middleName);
    }

    public Reader(Long id, int personalNumber, String firstName, String secondName, String middleName) {
        this.id = id;
        this.personalNumber = personalNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public Reader() {
    }
}
