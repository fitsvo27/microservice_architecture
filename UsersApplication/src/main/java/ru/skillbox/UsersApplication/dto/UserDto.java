package ru.skillbox.UsersApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("middlename")
    private String middlename;
    @JsonProperty("gender")
    private Character gender;
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    @JsonProperty("avatar_link")
    private String avatarLink;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("deleted")
    private Boolean deleted;
}
