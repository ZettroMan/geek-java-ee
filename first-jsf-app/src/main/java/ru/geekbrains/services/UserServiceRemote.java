package ru.geekbrains.services;

import ru.geekbrains.dto.UserDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserServiceRemote {

    List<UserDto> findAll();

    UserDto findById(Long id);

    Long countAll();

}
