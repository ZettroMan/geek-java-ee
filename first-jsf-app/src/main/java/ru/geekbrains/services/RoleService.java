package ru.geekbrains.services;

import ru.geekbrains.dto.RoleDto;
import ru.geekbrains.persist.Role;
import ru.geekbrains.persist.RoleRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {

    @Inject
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleDto> getAllRoles() {
        return roleRepository.getAllRoles().stream()
                .map(this::buildRoleDto)
                .collect(Collectors.toList());
    }

    public RoleDto buildRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
