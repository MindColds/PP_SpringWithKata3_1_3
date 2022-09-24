package web.init;

import web.model.Role;
import web.service.UserService;
import web.model.User;
import web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DefaultUser {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DefaultUser(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void initailizeDefaultUser() {
        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Set<Role> adminSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);

        User admin = new User("Admin", "Admin", 42, "admin@mail.ru",
                "admin", adminSet);

        userService.saveUser(admin);
    }
}
