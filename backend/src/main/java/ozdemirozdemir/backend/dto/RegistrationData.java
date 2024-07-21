package ozdemirozdemir.backend.dto;

import java.sql.Date;


public record RegistrationData(String firstName,
                               String lastName,
                               String email,
                               Date dateOfBirth) { }
