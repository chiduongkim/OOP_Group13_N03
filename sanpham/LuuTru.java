package sanpham;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LuuTru {
    public static void luuThongTinSP(String maSP, String tenSP, String giaSP, String soLuong) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("thongtinSP.txt", true))) {
            writer.write("Mã SP: " + maSP + ", Tên SP: " + tenSP + ", Giá: " + giaSP + ", Số Lượng: " + soLuong);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu thông tin sản phẩm: " + e.getMessage());
        }
    }
}
