package ra.edu;


import ra.edu.presentation.admin.AdminUI;
import ra.edu.presentation.candidate.CandidateUI;
import ra.edu.presentation.AuthenticationGateway;
import ra.edu.utils.console.PrintStartupAnimation;
import ra.edu.utils.user.CurrentLoginUtils;
import ra.edu.utils.user.Role;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthenticationGateway gateway = new AuthenticationGateway();
        AdminUI adminUI = new AdminUI();
        CandidateUI candidateUI = new CandidateUI();


        PrintStartupAnimation.showIntroAnimation();
        while (true) {
            boolean isLoggedIn = gateway.startAuthenticationFlow(sc);

            if (isLoggedIn && CurrentLoginUtils.role == Role.ADMIN) {
                boolean isLogout = adminUI.handle(sc);
                if (isLogout) {
                    CurrentLoginUtils.clear();
                    continue;
                }
            }

            if (isLoggedIn && CurrentLoginUtils.role == Role.CANDIDATE) {
                    boolean isLogout = candidateUI.handle(sc);
                    if (isLogout) {
                        CurrentLoginUtils.clear();
                        continue;
                    }
            }
        }
    }
}
