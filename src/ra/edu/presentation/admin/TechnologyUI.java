package ra.edu.presentation.admin;

import ra.edu.business.model.technology.Technology;
import ra.edu.business.model.technology.TechnologyStatus;
import ra.edu.business.service.technology.TechnologyService;
import ra.edu.business.service.technology.TechnologyServiceImpl;
import ra.edu.utils.console.menu.PrintAdminMenu;
import ra.edu.validate.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TechnologyUI {
    private final TechnologyService technologyService = new TechnologyServiceImpl();
    public void handle (Scanner sc){
        boolean back = false;
        while (!back){
            PrintAdminMenu.showTechnologyManagementMenu();

            int choice = InputValidator.validateInt(sc);

            switch (choice){
                case 1:
                    int currentPage = 1;
                    int numOfElement = 5;

                    List<Technology> technologies = technologyService.getTechnologyPerPage(currentPage, numOfElement);
                    int totalRow = technologyService.getTotalRow();
                    int totalPage = (int) Math.ceil((double) totalRow / numOfElement);

                    if (technologies.isEmpty()) {
                        System.out.println("Danh sách công nghệ trống!");
                        break;
                    }

                    for (Technology tech : technologies) {
                        System.out.println("ID: " + tech.getId());
                        System.out.println("Tên: " + tech.getName());
                        System.out.println("Trạng thái: " + tech.getStatus());
                        System.out.println("Ngày tạo: " + tech.getCreatedAt());
                        System.out.println("Ngày cập nhật: " + tech.getUpdatedAt());
                        System.out.println("---------");
                    }


                    System.out.printf("Trang %d / %d%n", currentPage, totalPage);
                    break;
                case 2:
                    System.out.print("Nhập tên công nghệ mới: ");
                    String input = InputValidator.validateString(sc,"tên công nghệ",1,255);
                    Optional<Technology> found = technologyService.findByName(input);
                    if (found.isPresent()) {
                        if (technologyService.isNameDeleted(input)) {

                            Technology tech = found.get();
                            tech.setStatus(TechnologyStatus.ACTIVE);
                            if(technologyService.update(tech)){
                                System.out.println("Công nghệ này từng bị xoá. Đã kích hoạt lại!");
                            }else {
                                System.out.println("Kích hoạt thất bại.");
                            }

                        } else {
                            System.out.println("Tên công nghệ đã tồn tại.");
                        }
                    } else {
                        Technology tech = new Technology(0, input, TechnologyStatus.ACTIVE);
                        if (technologyService.add(tech)) {
                            System.out.println("Thêm công nghệ thành công!");
                        } else {
                            System.out.println("Thêm thất bại!");
                        }
                    }

                    break;
                case 3:
                    System.out.print("Nhập id công nghệ cần xóa: ");
                    int id = InputValidator.validateInt(sc);
                    Optional<Technology> foundDelete = technologyService.findById(id);
                    if(foundDelete.isPresent() && foundDelete.get().getStatus() != TechnologyStatus.DELETED){
                        Technology t = foundDelete.get();
                        t.setStatus(TechnologyStatus.DELETED);
                        if (technologyService.update(t)) {
                            System.out.println("Xóa thành công!");
                            break;
                        }
                    }

                    System.out.println("Không thấy đối tượng cần xóa!");
                    break;
                case 4:
                    System.out.print("Nhập id công nghệ cần sửa: ");
                    int idUpdate = InputValidator.validateInt(sc);
                    Optional<Technology> foundUpdate = technologyService.findById(idUpdate);
                    if(foundUpdate.isPresent() && foundUpdate.get().getStatus() != TechnologyStatus.DELETED){
                        System.out.println("Tên cũ: " + foundUpdate.get().getName());
                        System.out.println("Nhập tên mới cho công nghệ");
                        String newName = InputValidator.validateString(sc,"tên công nghệ", 1,255);
                        Optional<Technology> isExistName = technologyService.findByName(newName);
                        if (isExistName.isPresent()) {
                            System.out.println("Tên công nghệ đã tồn tại.");
                        }else {
                            Technology t = foundUpdate.get();
                            t.setName(newName);
                            if (technologyService.update(t)) {
                                System.out.println("Sửa thành công!");
                                break;
                            }
                        }
                    }
                    System.out.println("Không thấy đối tượng cần sửa!");
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                    break;
            }
        }
    }
}
