package com.ts.tsadmin.controller.api;

import com.ts.tsadmin.domain.User;
import com.ts.tsadmin.dto.DataTableResponseDTO;
import com.ts.tsadmin.dto.UserDTO;
import com.ts.tsadmin.repository.UserRepository;
import com.ts.tsadmin.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/list")
    public DataTableResponseDTO<UserDTO> listUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> userEntityService.convertToDto(user))
                .collect(Collectors.toList());
        return new DataTableResponseDTO<>(userList.size(), userList.size(), 1, userDTOList);
    }

}
