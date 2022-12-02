package az.unitech.authuserms.service;

import az.unitech.authuserms.exception.BusinessStatus;
import az.unitech.authuserms.exception.ExceptionHelper;
import az.unitech.authuserms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ExceptionHelper exceptionHelper;
    @Override
    public UserDetails loadUserByUsername(String pin) throws UsernameNotFoundException {
        var userEntity = userRepository.findByPin(pin)
                .orElseThrow(()->exceptionHelper.getException(BusinessStatus.NO_SUCH_USER));

        return new User(userEntity.getPin(), userEntity.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+userEntity.getUserRole().name())));
    }
}
