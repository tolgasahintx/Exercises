package com.crisp.Models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails{

    @SerializedName("Id")
    private String id;

    @SerializedName("Name")
    private String name;

    @SerializedName("DateOfBirth")
    private String dateOfBirth;

    @SerializedName("Address")
    private String address;
}
