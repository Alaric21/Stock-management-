package fr.eitelalaric.gestiondestock.service;

import fr.eitelalaric.gestiondestock.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save (UserDto userDto);

    UserDto findById(Integer id);

    List<UserDto> findAll();

    void delete(Integer id);
}
