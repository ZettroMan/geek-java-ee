package ru.geekbrains.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String login;
    private String password;

    private Set<RoleDto> roles;

    public void addRole(RoleDto roleDto){
        roles.add(roleDto);
    }

}
