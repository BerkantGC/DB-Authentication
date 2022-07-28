package com.auth.auth.dto;

import com.auth.auth.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;

    private String username;
    private String password;
    private Integer blocked;
    private List<Role> roles;
}
