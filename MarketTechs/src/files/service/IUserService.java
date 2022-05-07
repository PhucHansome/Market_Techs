package files.service;

import files.object.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();

    User loginAdmin(String username, String password);

    void add(User newUser);

    void updateName(User newUser);
    void updatePhone(User newUser);
    void updateAdress(User newUser);

    boolean existById(int id);

    boolean checkDuplicateEmail(String Email);

    boolean checkDuplicatePhone(String phone);

    boolean checkDuplicateUserName(String userName);

    User getUserById(int id);

}


