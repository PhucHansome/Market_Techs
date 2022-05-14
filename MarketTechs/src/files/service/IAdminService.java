package files.service;

import files.object.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> getAdmin();

    Admin loginAdmin(String username, String password);

    void add(Admin newAdmin);

    void updateName(Admin newAdmin);
    void updatePhone(Admin newAdmin);
    void updateAdress(Admin newAdmin);
    void remove(Admin newAdmin);

    boolean existById(int id);

    boolean checkDuplicateEmail(String Email);

    boolean checkDuplicatePhone(String phone);

    boolean checkDuplicateUserName(String userName);

    Admin getUserById(int id);

}


