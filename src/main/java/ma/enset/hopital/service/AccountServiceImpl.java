package ma.enset.hopital.service;


import lombok.AllArgsConstructor;
import ma.enset.hopital.entities.AppRole;
import ma.enset.hopital.entities.AppUser;
import ma.enset.hopital.repository.AppRoleRepository;
import ma.enset.hopital.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private AppRoleRepository appRoleRepository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if( appUser != null ){
            throw new RuntimeException("This user already exist");
        } else if (!password.equals(confirmPassword)) {
            throw  new RuntimeException("Passowrd not match");
        }else {
             appUser=AppUser.builder().username(username).password(passwordEncoder.encode(password))
                    .email(email)
                    .build();

           AppUser savedAppuser= appUserRepository.save(appUser);
           return savedAppuser;
        }

    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if( appRole != null ){
            throw new RuntimeException("This role already exist");
        }else {
            appRole=AppRole.builder().role(role)
                    .build();

            AppRole savedRole= appRoleRepository.save(appRole);
            return savedRole;
        }
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        AppUser appUser=appUserRepository.findByUsername(username);
         if (appRole!=null && appUser!=null){
             appUser.getRoles().add(appRole);
         }
        appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appRole!=null && appUser!=null){
            appUser.getRoles().remove(appRole);
        }
        appUserRepository.save(appUser);

    }

    @Override
    public AppUser LoadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
