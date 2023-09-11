package ma.enset.hopital.service;

import ma.enset.hopital.entities.AppRole;
import ma.enset.hopital.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String password,String email,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);

    AppUser LoadUserByUsername(String username);
}