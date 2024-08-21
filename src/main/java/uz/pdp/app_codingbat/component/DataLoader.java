package uz.pdp.app_codingbat.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.app_codingbat.entity.Role;
import uz.pdp.app_codingbat.entity.enums.Permission;
import uz.pdp.app_codingbat.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {

        if (Objects.equals(ddl, "create")) {
            initRoles();
        }

        System.out.println("Application started.............");
    }

    private void initRoles() {
        Role admin = Role.builder()
                .name("ADMIN")
                .permissions(Set.of(Permission.values()))
                .build();

        Role user = Role.builder()
                .name("USER")
                .permissions(Set.of(Permission.SOLVE_PROBLEM))
                .build();

        roleRepository.saveAll(List.of(admin, user));
    }
}
