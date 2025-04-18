use recruitment_management;

-- phân trang danh sách
delimiter //
create procedure get_technology_per_page (
    in in_numberOfElement int,
    in in_currentPage int
)
begin
    declare page int  default (in_currentPage - 1) * in_numberOfElement;
    select id, name, status, createdAt, updatedAt from technology limit in_numberOfElement offset page;
end //
delimiter //

delimiter //
create procedure get_total_row_technology()
begin
    select count(*) as totalRow from technology;
end //
delimiter ;

