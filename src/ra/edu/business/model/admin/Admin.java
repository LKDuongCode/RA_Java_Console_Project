package ra.edu.business.model.admin;

import ra.edu.business.model.person.Person;
import ra.edu.business.model.person.PersonStatus;

public class Admin extends Person {
    public Admin() {}

    public Admin(int id, String name, String email, String password, PersonStatus status) {
        super(id, name, email, password, status);
    }

}
