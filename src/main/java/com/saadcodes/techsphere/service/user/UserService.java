package com.saadcodes.techsphere.service.user;

import com.saadcodes.techsphere.dtos.ImageDto;
import com.saadcodes.techsphere.dtos.ProductDto;
import com.saadcodes.techsphere.dtos.UserDto;
import com.saadcodes.techsphere.model.Image;
import com.saadcodes.techsphere.model.Product;
import com.saadcodes.techsphere.model.User;
import com.saadcodes.techsphere.repository.UserRepository;
import com.saadcodes.techsphere.request.CreateUserRequest;
import com.saadcodes.techsphere.request.UpdateUserRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserByEmail(String email) {
        return (User) userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User newUser = new User();
                    newUser.setFirstName(request.getFirstName());
                    newUser.setLastName(request.getLastName());
                    newUser.setEmail(request.getEmail());
                    newUser.setPassword(request.getPassword());
                    return userRepository.save(newUser);
                }).orElseThrow(() -> new EntityExistsException("Oops! "+ request.getEmail()+ " already exists"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(
                userRepository :: delete, () -> {
                    throw new EntityNotFoundException("User not found");
                });
    }

    @Override
    public UserDto UserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
