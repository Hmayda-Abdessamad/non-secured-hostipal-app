package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.awt.geom.PathIterator;
import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {

    @Autowired(required = true)
    private PatientRepository patientRepository;


    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
       //patientRepository.save(new Patient(null,"Mohamed",new Date(),false,4000));
       //patientRepository.save(new Patient(null,"Hanane",new Date(),false,432));
       //patientRepository.save(new Patient(null,"Imane",new Date(),true,340));

    }

         @Bean
        PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }

        //@Bean
        CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){

        return args -> {
            UserDetails user1=jdbcUserDetailsManager.loadUserByUsername("user1");
            if(user1==null){
                jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build());
            }
            UserDetails user2=jdbcUserDetailsManager.loadUserByUsername("user2");
            if(user2==null){
                jdbcUserDetailsManager.createUser(User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build());
            }
            UserDetails user3=jdbcUserDetailsManager.loadUserByUsername("user3");
            if(user3==null){
                jdbcUserDetailsManager.createUser(User.withUsername("user3").password(passwordEncoder().encode("1234")).roles("ADMIN","USER").build());
            }


        };
    }
}

