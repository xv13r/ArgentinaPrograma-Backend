package ar.gob.inti.argentinaprograma.miportfolio;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.enums.RoleEnum;
import ar.gob.inti.argentinaprograma.miportfolio.model.Category;
import ar.gob.inti.argentinaprograma.miportfolio.model.Employment;
import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.model.Role;
import ar.gob.inti.argentinaprograma.miportfolio.model.User;
import ar.gob.inti.argentinaprograma.miportfolio.service.impl.CategoryService;
import ar.gob.inti.argentinaprograma.miportfolio.service.impl.EmploymentService;
import ar.gob.inti.argentinaprograma.miportfolio.service.impl.ProfileService;
import ar.gob.inti.argentinaprograma.miportfolio.service.impl.RoleService;
import ar.gob.inti.argentinaprograma.miportfolio.service.impl.UserService;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {
    boolean alreadySetup = false;

    @Autowired
    private CategoryService _categoryService;

    @Autowired
    private EmploymentService _employmentService;

    @Autowired
    private UserService _userService;

    @Autowired
    private RoleService _roleService;

    @Autowired
    private ProfileService _profileService;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        if (alreadySetup) {

            List<Category> newCategories = List.of(
                    new Category("Taller"),
                    new Category("Curso"),
                    new Category("Educación secundaria"),
                    new Category("Diplomatura"),
                    new Category("Universitario"));

            List<Employment> newEmployments = List.of(
                    new Employment("PartTime"),
                    new Employment("FullTime"),
                    new Employment("Freelance"),
                    new Employment("Monotributista"),
                    new Employment("Informal"));

            List<Role> newURoles = List.of(
                    new Role(RoleEnum.ROLE_USER),
                    new Role(RoleEnum.ROLE_ADMIN));

            List<User> newUsers = List.of(
                    new User("user", "user@email.io", "12345678+"),
                    new User("admin", "admin@email.io", "12345678+"));

            List<Profile> newProfiles= List.of(
                    new Profile("name", "lastname", LocalDate.now(), "about", "title"));
   
            User user1 = newUsers.get(0);
            User admin = newUsers.get(1);

            for (Category category : newCategories) {
                this._categoryService.create(category);
            }

            for (Employment employment : newEmployments) {
                this._employmentService.create(employment);
            }

            for (Role role : newURoles) {
                this._roleService.create(role);
            }

            user1.addRole(this._roleService.findByName(RoleEnum.ROLE_USER));
            admin.addRole(this._roleService.findByName(RoleEnum.ROLE_ADMIN));

            for (User user : newUsers) {
                this._userService.create(user);
            }

            for (Profile profile : newProfiles) {
                profile.setUser(user1);
                this._profileService.create(profile);
            }
        }
    }
}
