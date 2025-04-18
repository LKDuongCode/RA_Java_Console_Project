package ra.edu.business.service.candidate;

import ra.edu.business.dao.candidate.CandidateDAO;
import ra.edu.business.dao.candidate.CandidateDaoImpl;
import ra.edu.business.model.candidate.Candidate;
import ra.edu.business.model.person.PersonStatus;
import ra.edu.utils.PasswordUtils;
import ra.edu.utils.SystemPrompt;
import ra.edu.utils.console.ColorTransfer;
import ra.edu.utils.user.CurrentLoginUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CandidateServiceImpl implements CandidateService{
    private final CandidateDAO candidateDAO = new CandidateDaoImpl();

    @Override
    public Optional<Candidate> login(String email, String password) {
        Optional<Candidate> candidateOpt = candidateDAO.findByEmail(email);

        if (candidateOpt.isEmpty()) {
            System.err.println("Tài khoản hoặc mật khẩu không đúng!");
            return Optional.empty();
        }

        Candidate candidate = candidateOpt.get();
        System.out.println(password);
        System.out.println(candidate.getPassword());

        if (!PasswordUtils.verifyPassword(password, candidate.getPassword())) {
            System.err.println("Tài khoản hoặc mật khẩu không đúng!");
            return Optional.empty();
        }

        if (candidate.getStatus() == PersonStatus.INACTIVE) {
            System.err.println("Tài khoản đã bị khóa");
            return Optional.empty();
        }

        return Optional.of(candidate);
    }

    @Override
    public boolean logout(Scanner sc) {
        boolean confirmed = SystemPrompt.confirmAction("Bạn có chắc muốn đăng xuất không?", sc);
        if (!confirmed) {
            System.out.println(ColorTransfer.toGreen("Hủy đăng xuất!"));
            return false;
        }

        candidateDAO.logout();
        return true;
    }

    @Override
    public void rememberMe(String email) {
        if (candidateDAO.saveRememberedUser(email)) {
            System.out.println(ColorTransfer.toYellow("Đã lưu phiên đăng nhập!"));
        } else {
            System.err.println("Lưu phiên đăng nhập thất bại.");
        }
    }

    @Override
    public boolean checkLoggedIn() {
        return candidateDAO.getRememberedUser().isPresent();
    }

    @Override
    public Candidate getRememberedCandidate() {
        return candidateDAO.getRememberedUser().get();
    }

    @Override
    public List<Candidate> getAll() {
        return List.of();
    }

    @Override
    public boolean add(Candidate candidate) {
        // Mã hoá mật khẩu trước khi lưu
        String hashedPassword = PasswordUtils.hashPassword(candidate.getPassword());
        candidate.setPassword(hashedPassword);

        return candidateDAO.insert(candidate);
    }


    @Override
    public boolean update(Candidate candidate) {
        return false;
    }

    @Override
    public boolean delete(Candidate candidate) {
        return false;
    }

}
