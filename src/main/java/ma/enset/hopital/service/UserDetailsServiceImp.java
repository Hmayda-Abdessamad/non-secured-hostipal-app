package ma.enset.hopital.service;

import lombok.AllArgsConstructor;
import ma.enset.hopital.entities.AppUser;
import ma.enset.hopital.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       final AppUser appUser= appUserRepository.findByUsername(username);
       if(appUser==null) throw new UsernameNotFoundException(String.format("User %s not found ",username));

       //String[] roles=appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
       List<SimpleGrantedAuthority> authorities= appUser.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
       UserDetails user= User.withUsername(appUser.getUsername()).password(appUser.getPassword()).authorities(authorities).build();
                //.roles(roles).build();
        return user;
    }
}
