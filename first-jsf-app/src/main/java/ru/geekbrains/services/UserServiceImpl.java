package ru.geekbrains.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.dto.UserDto;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(UserServiceRemote.class)
public class UserServiceImpl implements UserService, UserServiceRemote {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @EJB
    private UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(this::buildUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        if(user == null) return null;
        return buildUserDto(user);
    }

    @Override
    public Long countAll() {
        return userRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(UserDto userDto) {
        userRepository.saveOrUpdate(new User(userDto));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto buildUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;
    }


}
