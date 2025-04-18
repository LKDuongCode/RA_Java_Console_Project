package ra.edu.business.dao.technology;

import ra.edu.business.config.DatabaseConnection;
import ra.edu.business.model.technology.Technology;
import ra.edu.business.model.technology.TechnologyStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TechnologyDaoImpl implements TechnologyDAO{
    @Override
    public Optional<Technology> findByName(String name) {
         name = name.trim().toLowerCase();
        try (
                Connection conn = DatabaseConnection.connectToDatabase();
                CallableStatement call = conn.prepareCall("{CALL find_technology_by_name(?)}")
        ) {
            call.setString(1, name);

            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) {
                    Technology tech = new Technology();
                    tech.setId(rs.getInt("id"));
                    tech.setName(rs.getString("name"));

                    String statusStr = rs.getString("status");

                    TechnologyStatus status = TechnologyStatus.valueOf(statusStr);
                    tech.setStatus(status);
                    tech.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    tech.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());

                    return Optional.of(tech);
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm công nghệ theo tên: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Giá trị status không hợp lệ: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Technology> getAll() {
        return List.of();
    }

    @Override
    public boolean insert(Technology technology) {
        try (
                Connection conn = DatabaseConnection.connectToDatabase();
                CallableStatement call = conn.prepareCall("{CALL insert_technology(?)}")
        ) {
            call.setString(1, technology.getName());
            int result = call.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm công nghệ: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Technology technology) {
        try (
                Connection conn = DatabaseConnection.connectToDatabase();
                CallableStatement call = conn.prepareCall("{CALL update_technology(?, ?, ?)}")
        ) {
            call.setInt(1, technology.getId());

            if (technology.getName() != null) {
                call.setString(2, technology.getName().trim().toLowerCase());
            } else {
                call.setNull(2, Types.VARCHAR);
            }

            if (technology.getStatus() != null) {
                call.setString(3, technology.getStatus().name());
            } else {
                call.setNull(3, Types.VARCHAR);
            }

            return call.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật công nghệ: " + e.getMessage());
        }

        return false;
    }



    @Override
    public Optional<Technology> findById(int id) {
        try (
                Connection conn = DatabaseConnection.connectToDatabase();
                CallableStatement stmt = conn.prepareCall("{CALL find_technology_by_id(?)}")
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Technology tech = new Technology();
                    tech.setId(rs.getInt("id"));
                    tech.setName(rs.getString("name"));

                    String statusStr = rs.getString("status");
                    TechnologyStatus status = TechnologyStatus.valueOf(statusStr);
                    tech.setStatus(status);
                    tech.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                    tech.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());

                    return Optional.of(tech);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm công nghệ theo ID: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Trạng thái không hợp lệ: " + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Technology> getTechnologyPerPage(int page, int numOfElement) {
        List<Technology> resultList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connectToDatabase();
             CallableStatement cs = conn.prepareCall("{call get_technology_per_page(?, ?)}")) {

            cs.setInt(1, numOfElement);
            cs.setInt(2, page);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Technology tech = new Technology();
                tech.setId(rs.getInt("id"));
                tech.setName(rs.getString("name"));
                tech.setStatus(TechnologyStatus.valueOf(rs.getString("status")));
                tech.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
                tech.setUpdatedAt(rs.getTimestamp("updatedAt").toLocalDateTime());

                resultList.add(tech);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi procedure get_technology_per_page: " + e.getMessage());
        }

        return resultList;
    }

    @Override
    public int getTotalRow() {
        int totalRow = 0;

        try (Connection conn = DatabaseConnection.connectToDatabase();
             CallableStatement cs = conn.prepareCall("{call get_total_row_technology()}")) {

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                totalRow = rs.getInt("totalRow");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi procedure get_total_row_technology: " + e.getMessage());
        }

        return totalRow;
    }

}
