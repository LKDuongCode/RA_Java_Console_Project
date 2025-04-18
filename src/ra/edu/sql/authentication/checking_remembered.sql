use recruitment_management;
-- tạo 2 đối tượng để hứng dữ liệu
INSERT INTO remembered_admin (id, adminId) VALUES (1, NULL);
INSERT INTO remembered_candidate (id, candidateId) VALUES (1, NULL);
-- kiểm tra xem trước đó có nhớ đăng nhập
DELIMITER $$

CREATE PROCEDURE sp_check_remembered_candidate ()
BEGIN
    DECLARE v_candidateId INT;

    SELECT candidateId INTO v_candidateId
    FROM remembered_candidate
    WHERE id = 1;

    IF v_candidateId IS NULL THEN
        SELECT 'AUTH1' AS error_code,
               'Candidate chưa lưu phiên đăng nhập' AS message;
    ELSE
        SELECT v_candidateId AS remembered_candidate_id;
    END IF;
END$$

DELIMITER ;

CREATE PROCEDURE sp_check_remembered_admin ()
BEGIN
    DECLARE v_adminId INT;

    SELECT adminId INTO v_adminId
    FROM remembered_admin
    WHERE id = 1;

    IF v_adminId IS NULL THEN
        SELECT 'AUTH2' AS error_code,
               'Admin chưa lưu phiên đăng nhập' AS message;
    ELSE
        SELECT v_adminId AS remembered_admin_id;
    END IF;
END$$

DELIMITER ;

