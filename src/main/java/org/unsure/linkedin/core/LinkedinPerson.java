package org.unsure.linkedin.core;

import com.google.code.linkedinapi.schema.Person;

public class LinkedinPerson {
    private final String firstName;
    private final String lastName;
    private final String headline;


    public LinkedinPerson(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.headline = person.getHeadline();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHeadline() {
        return headline;
    }
}
