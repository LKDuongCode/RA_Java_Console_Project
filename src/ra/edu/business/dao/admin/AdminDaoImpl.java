package ra.edu.business.dao.admin;

import ra.edu.business.config.DatabaseConnection;
import ra.edu.business.model.admin.Admin;
import ra.edu.business.model.person.PersonStatus;
import ra.edu.utils.PasswordUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDaoImpl implements AdminDAO {
    @Override
    public Optional<Admin> findByEmail(String email) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_get_admin_by_email(?)}")
        ) {
            call.setString(1, email);

            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setName(rs.getString("name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPassword(rs.getString("password"));
                    admin.setStatus(PersonStatus.valueOf(rs.getString("status")));

                    return Optional.of(admin);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi tìm admin theo email: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }

        return Optional.empty();
    }
    @Override
    public void logout() {
        String sql = "{CALL sp_reset_remembered_admin()}";
        try (
                Connection conn = DatabaseConnection.connectToDatabase();
                CallableStatement callSt = conn.prepareCall(sql);
        ) {
            if (conn == null || callSt == null) {
                System.err.println("Không thể kết nối CSDL để logout.");
                return;
            }

            callSt.execute();
        } catch (SQLException e) {
            System.err.println("Lỗi khi logout: " + e.getMessage());
        }
    }


    @Override
    public boolean saveRememberedUser(String email) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_remember_admin(?)}")
        ) {
            call.setString(1, email);
            call.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi ghi nhớ phiên đăng nhập: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }

        return false;
    }


    @Override
    public Optional<Admin> getRememberedUser() {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_check_remembered_admin()}")
        ) {
            boolean hasResult = call.execute();

            if (hasResult) {
                try (ResultSet rs = call.getResultSet()) {
                    if (rs.next()) {
                        // Kiểm tra có lỗi không
                        String errorCode = null;
                        try {
                            errorCode = rs.getString("error_code");
                        } catch (SQLException ignored) {}

                        if (errorCode != null) {
                            // Trường hợp không có admin được lưu
                            System.out.println(rs.getString("message"));
                            return Optional.empty();
                        }

                        int rememberedAdminId = rs.getInt("remembered_admin_id");

                        return findById(rememberedAdminId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }

        return Optional.empty();
    }


    @Override
    public List<Admin> getAll() {
        return List.of();
    }

    @Override
    public boolean insert(Admin admin) {
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        return false;
    }


    @Override
    public Optional<Admin> findById(int id) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_find_admin_by_id(?)}")
        ) {
            call.setInt(1, id);

            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setName(rs.getString("name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPassword(rs.getString("password"));
                    admin.setStatus(PersonStatus.valueOf(rs.getString("status")));

                    return Optional.of(admin);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm Admin theo ID: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }

        return Optional.empty();
    }

}
