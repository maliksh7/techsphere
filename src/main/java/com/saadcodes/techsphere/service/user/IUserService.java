package com.saadcodes.techsphere.service.user;

import com.saadcodes.techsphere.dtos.UserDto;
import com.saadcodes.techsphere.model.User;
import com.saadcodes.techsphere.request.CreateUserRequest;
import com.saadcodes.techsphere.request.UpdateUserRequest;

public interface IUserService {

    User getUserByEmail(String email);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    User getUserById(Long userId);
    void deleteUser(Long userId);

    UserDto UserDto(User user);
}
