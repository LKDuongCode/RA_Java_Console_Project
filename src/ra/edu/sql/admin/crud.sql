use recruitment_management;

-- mặc đinh có 1 admin
INSERT INTO admin (name, email, password, status)
VALUES ('AdminDZ', 'duong@gmail.com', '$2a$10$p4alOEKMbam7m4vF1xsY5us5pi6I7Wf0Joe2.JR5eMzUDPL9bNPMG', 'ACTIVE');


DELIMITER $$

CREATE PROCEDURE sp_find_admin_by_id(IN p_id INT)
BEGIN
    SELECT id, name, email, password, status
    FROM admin
    WHERE id = p_id;
END$$

DELIMITER ;
