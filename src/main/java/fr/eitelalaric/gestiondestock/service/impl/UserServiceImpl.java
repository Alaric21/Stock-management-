package fr.eitelalaric.gestiondestock.service.impl;

import fr.eitelalaric.gestiondestock.dto.UserDto;
import fr.eitelalaric.gestiondestock.exception.EntityNotFoundException;
import fr.eitelalaric.gestiondestock.exception.ErrorCodes;
import fr.eitelalaric.gestiondestock.exception.InvalidEntityException;
import fr.eitelalaric.gestiondestock.repository.UserRepository;
import fr.eitelalaric.gestiondestock.service.UserService;
import fr.eitelalaric.gestiondestock.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if (!errors.isEmpty()) {
            log.error("User not valid: {}", userDto);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID,errors);
        }
        return UserDto.fromEntity(userRepository.save(UserDto.toEntity(userDto)));
    }

    @Override
    public UserDto findById(Integer id) {
        if (id==null){
            log.error("User ID is null");
            return null;
        }
        return userRepository.findById(id).map(UserDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("User with ID "+id+"doesnt exist", ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("User ID is null");
            return;
        }
        userRepository.deleteById(id);
    }
}
