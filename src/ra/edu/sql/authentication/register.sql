use recruitment_management;

DELIMITER $$

CREATE PROCEDURE sp_register_candidate (
    IN p_name VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_password VARCHAR(255),
    IN p_phone VARCHAR(12),
    IN p_experience INT,
    IN p_gender ENUM('MALE','FEMALE','OTHER'),
    IN p_description TEXT,
    IN p_dob DATE
)
BEGIN
    INSERT INTO candidate (
        name, email, password, phone,
        experience, gender, description, dob,
        status, createdAt, updatedAt
    ) VALUES (
                 p_name, p_email, p_password, p_phone,
                 p_experience, p_gender, p_description, p_dob,
                 'ACTIVE', NOW(), NOW()
             );
END$$

DELIMITER ;
