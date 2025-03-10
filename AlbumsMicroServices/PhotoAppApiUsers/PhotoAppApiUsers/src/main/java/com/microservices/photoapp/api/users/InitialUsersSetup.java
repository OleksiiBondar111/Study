package com.microservices.photoapp.api.users;

import com.microservices.photoapp.api.users.data.*;
import com.microservices.photoapp.api.users.shared.Roles;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitialUsersSetup {

    private final AuthorityRepository authorityRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UsersRepository usersRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("From Application Ready Event");

        AuthorityEntity readAuthority = createAuthorityEntity("READ");
        AuthorityEntity writeAuthority = createAuthorityEntity("WRITE");
        AuthorityEntity deleteAuthority = createAuthorityEntity("DELETE");

        createRoleEntity(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRoleEntity(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        if (roleAdmin == null) {
            return;
        }

        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("Oleksii");
        adminUser.setLastName("Bondar");
        adminUser.setEmail("oleksii.bondar@test.com");
        adminUser.setUserId(UUID.randomUUID().toString());
        adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("12345678"));
        adminUser.setRoles(Arrays.asList(roleAdmin));

        UserEntity storeAdminUser = usersRepository.findByEmail(adminUser.getEmail());
        if (storeAdminUser == null) {
            usersRepository.save(adminUser);
        }


    }

    @Transactional
    AuthorityEntity createAuthorityEntity(String name) {

        AuthorityEntity authorityEntity = authorityRepository.findByName(name);

        if (authorityEntity == null) {
            authorityEntity = new AuthorityEntity(name);
            authorityRepository.save(authorityEntity);
        }
        return authorityEntity;
    }

    @Transactional
    RoleEntity createRoleEntity(String name, Collection<AuthorityEntity> authorityEntities) {

        RoleEntity role = roleRepository.findByName(name);

        if (role == null) {
            role = new RoleEntity(name, authorityEntities);
            roleRepository.save(role);
        }

        return role;
    }

}
