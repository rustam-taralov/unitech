package az.unitech.authuserms.service;

import az.unitech.authuserms.dtos.dto.UserDto;
import az.unitech.authuserms.dtos.request.UserRegistrationRequest;
import az.unitech.authuserms.enums.UserRole;
import az.unitech.authuserms.exception.BusinessStatus;
import az.unitech.authuserms.exception.ExceptionHelper;
import az.unitech.authuserms.mapper.UserMapper;
import az.unitech.authuserms.model.UserEntity;
import az.unitech.authuserms.repository.UserRepository;
import az.unitech.authuserms.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ExceptionHelper exceptionHelper;

    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUser(){
        return userRepository.findAll(PageUtils.getPage())
                .map(UserMapper.INSTANCE::toDto).toList();
    }

    public UserDto getUserByPin(String pin){
        if(StringUtils.isBlank(pin)) exceptionHelper.throwException(BusinessStatus.PIN_NULL);

        var userByPin=userRepository.findByPin(pin)
                .orElseThrow(()->exceptionHelper.getException(BusinessStatus.NO_SUCH_USER));
        return UserMapper.INSTANCE.toDto(userByPin);
    }

    public UserDto getUserById(Long id){
        if(id==null) exceptionHelper.throwException(BusinessStatus.ID_CAN_NOT_BE_NULL);
        var userById=userRepository.findById(id)
                .orElseThrow(()->exceptionHelper.getException(BusinessStatus.NO_SUCH_USER));
        return UserMapper.INSTANCE.toDto(userById);
    }

    public void saveUser(UserDto userDto){
        var userEntity= UserMapper.INSTANCE.toEntity(userDto);
        userRepository.save(userEntity);
    }

    public void deleteUser(Long id){
        if(id==null) exceptionHelper.throwException(BusinessStatus.ID_CAN_NOT_BE_NULL);
        userRepository.deleteById(id);
    }

    public void registerUser(UserRegistrationRequest userRegistrationRequest){
        if(userRepository.existsAllByPin(userRegistrationRequest.getPin()))
            exceptionHelper.throwException(BusinessStatus.USER_ALREADY_EXIST);

        var user= UserEntity.builder()
                        .pin(userRegistrationRequest.getPin())
                        .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                        .userRole(UserRole.USER)
                        .build();

        userRepository.save(user);
    }
}
