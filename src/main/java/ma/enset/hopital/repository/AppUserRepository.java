package ma.enset.hopital.repository;

import ma.enset.hopital.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {
    AppUser findByUsername(String username);
}
