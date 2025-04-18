package ra.edu.utils.user;

// lưu người dùng hiện tại trong quá trình chạy úng dụng.
public class CurrentLoginUtils {
    public static int id;
    public static Role role;

    public static void setLogin (int newId, Role newRole){
        id = newId;
        role = newRole;
    }

    public static void clear() {
        id = 0;
        role = Role.NONE;
    }
}
