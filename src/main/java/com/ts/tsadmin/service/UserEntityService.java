package com.ts.tsadmin.service;

import com.ts.tsadmin.domain.User;
import com.ts.tsadmin.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
