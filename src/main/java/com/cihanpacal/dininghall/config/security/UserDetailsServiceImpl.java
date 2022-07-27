package com.cihanpacal.dininghall.config.security;

import com.cihanpacal.dininghall.model.entity.AuthenticationToken;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.repository.AuthenticationTokenRepository;
import com.cihanpacal.dininghall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser=userRepository.findByEmail(username);
        User user=optionalUser.orElseThrow(()->{
            throw new UsernameNotFoundException("user not found");
        });

        UserDetailsImpl userDetails=new UserDetailsImpl();
        userDetails.setUsername(user.getEmail());
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(user.getEnabled());
        userDetails.setNonLocked(user.getNonLocked());
        userDetails.setId(user.getId());

        Set<GrantedAuthority> authorities=new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        userDetails.setAuthorities(authorities);

        return userDetails;
    }

    public UserDetails getUserDetailsByAuthenticationToken(String token) {

        Optional<AuthenticationToken> authenticationTokenOptional=authenticationTokenRepository.findByToken(token);
        if(!authenticationTokenOptional.isPresent()){
            return null;
        }

        AuthenticationToken authenticationToken=authenticationTokenOptional.get();

        if(authenticationToken.getExpiryDate().isBefore(LocalDateTime.now())){
            return null;
        }

        User user=authenticationToken.getUser();

        UserDetailsImpl userDetails=new UserDetailsImpl();
        userDetails.setUsername(user.getEmail());
        userDetails.setEnabled(user.getEnabled());
        userDetails.setNonLocked(user.getNonLocked());
        userDetails.setId(user.getId());
        Set<GrantedAuthority> authorities=new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        userDetails.setAuthorities(authorities);



        return userDetails;
    }

}
