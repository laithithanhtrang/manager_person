package manager_person.managerperson.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Person {
    private int id;
    @NotBlank(message = "Your name is required")
    @Size(min = 5, max = 30, message = "Name must between 5 and 30")
    private String name;
    @NotBlank(message = "Your job is required")
    private String job;
    private boolean gender;
    @NotBlank(message = "Your birthday is required")
    private String birthDay;
    private MultipartFile photo;
}
// Khi bạn đã đánh dấu 1 class là @Data, thì tại bất cứ đâu trong project. Khi
// sử dụng tới class này, nó sẽ tự có các function đã generate mà không cần code
// thêm bất kì dòng nào

// @NoArgsConstructor: Hàm khởi tạo rỗng, đã đề cập ở trên
// @AllArgsConstructor: Hàm khởi tạo chứa tất cả thuộc tính, ví dụ
// Champion(String name, String type)
// @RequiredArgsConstructor: Hàm khởi tạo theo yêu cầu. Bạn chỉ muốn hàm khởi
// tạo có vài thuộc tính do bạn chọn thôi, thì bạn thêm final trước thuộc tính
// trong class, nó sẽ tự sinh ra Constructor như thế.