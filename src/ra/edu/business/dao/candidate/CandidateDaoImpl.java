package ra.edu.business.dao.candidate;

import ra.edu.business.config.DatabaseConnection;
import ra.edu.business.model.admin.Admin;
import ra.edu.business.model.candidate.Candidate;
import ra.edu.business.model.person.PersonStatus;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CandidateDaoImpl implements CandidateDAO{
    @Override
    public Optional<Candidate> findByEmail(String email) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_get_candidate_by_email(?)}")
        ) {
            call.setString(1, email);
            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) {
                    Candidate candidate = mapCandidate(rs);
                    return Optional.of(candidate);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi tìm candidate theo email: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Candidate> getRememberedUser() {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_check_remembered_candidate()}")
        ) {
            boolean hasResult = call.execute();
            if (hasResult) {
                try (ResultSet rs = call.getResultSet()) {
                    if (rs.next()) {
                        try {
                            String errorCode = rs.getString("error_code");
                            if (errorCode != null) {
                                System.out.println(rs.getString("message"));
                                return Optional.empty();
                            }
                        } catch (SQLException ignored) {}

                        int rememberedId = rs.getInt("remembered_candidate_id");
                        return findById(rememberedId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi kiểm tra remembered candidate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Candidate> findById(int id) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_find_candidate_by_id(?)}")
        ) {
            call.setInt(1, id);
            try (ResultSet rs = call.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapCandidate(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi tìm candidate theo ID: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean saveRememberedUser(String email) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_remember_candidate(?)}")
        ) {
            call.setString(1, email);
            call.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi lưu remembered candidate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void logout() {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_reset_remembered_candidate()}")
        ) {
            call.execute();
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi logout candidate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định: " + e.getMessage());
        }
    }

    private Candidate mapCandidate(ResultSet rs) throws SQLException {
        Candidate c = new Candidate();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        c.setPassword(rs.getString("password"));
        c.setStatus(PersonStatus.valueOf(rs.getString("status")));
        return c;
    }

    @Override
    public List<Candidate> getAll() {
        return List.of();
    }

    @Override
    public boolean insert(Candidate candidate) {
        try (
                Connection c = DatabaseConnection.connectToDatabase();
                CallableStatement call = c.prepareCall("{call sp_register_candidate(?, ?, ?, ?, ?, ?, ?, ?)}")
        ) {
            call.setString(1, candidate.getName());
            call.setString(2, candidate.getEmail());
            call.setString(3, candidate.getPassword());
            call.setString(4, candidate.getPhone());
            call.setInt(5, candidate.getExperience());
            call.setString(6, candidate.getGender().name());
            call.setString(7, candidate.getDescription());
            call.setDate(8, Date.valueOf(candidate.getDob()));

            call.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi insert Candidate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi bất định khi insert Candidate: " + e.getMessage());
        }

        return false;
    }


    @Override
    public boolean update(Candidate candidate) {
        return false;
    }


}
