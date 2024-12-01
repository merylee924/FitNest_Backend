package fitnest.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
   /* @Bean
    CommandLineRunner cmd(AccountRepo accountRepo, UserRepo userRepo) {
        return args -> {
            // Create sample Account
            Account account1 = Account.builder()
                    .username("john_doe")
                    .password("password123")
                    .build();
            accountRepo.save(account1);

            Account account2 = Account.builder()
                    .username("jane_smith")
                    .password("password456")
                    .build();
            accountRepo.save(account2);

            // Create sample User
            User user1 = User.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .idFace("faceID123")
                    .idBack("backID123")
                    .profilePicture("john_profile_pic.jpg")
                    .phoneNumber(123456789L)
                    .birthDate(new Date())
                    .gender("Male")
                    .description("A passionate athlete")
                    .interests(List.of(Interest.SWIM,Interest.RIDE)) // Example interests
                    .build();
            userRepo.save(user1);

            User user2 = User.builder()
                    .firstName("Jane")
                    .lastName("Smith")
                    .idFace("faceID456")
                    .idBack("backID456")
                    .profilePicture("jane_profile_pic.jpg")
                    .phoneNumber(987654321L)
                    .birthDate(new Date())
                    .gender("Female")
                    .description("A fitness enthusiast")
                    .interests(List.of(Interest.HIKE, Interest.BASKETBALL)) // Example interests
                    .build();
            userRepo.save(user2);


        };}*/
}
