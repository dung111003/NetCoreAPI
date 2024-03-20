package demo_1;

import java.io.File;
import java.io.IOException;

public class FileOperationExample {
    public static void main(String[] args) {
        // Tạo một tệp mới
        File newFile = new File("myFile.txt");
        try {
            if (newFile.createNewFile()) {
                System.out.println("Tệp đã được tạo: " + newFile.getName());
            } else {
                System.out.println("Tệp đã tồn tại.");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi tạo tệp: " + e.getMessage());
        }

        // Hiển thị cây thư mục
        System.out.println("Cây thư mục:");
        displayDirectoryTree(new File("."));

        // Xóa tệp
        if (newFile.exists()) {
            if (newFile.delete()) {
                System.out.println("Tệp đã được xóa: " + newFile.getName());
            } else {
                System.out.println("Không thể xóa tệp.");
            }
        }

        // Xóa thư mục (chú ý: hãy thay đổi đường dẫn thư mục thành thư mục mà bạn muốn xóa)
        File directoryToDelete = new File("myDirectory");
        if (directoryToDelete.exists() && directoryToDelete.isDirectory()) {
            if (deleteDirectory(directoryToDelete)) {
                System.out.println("Thư mục đã được xóa: " + directoryToDelete.getName());
            } else {
                System.out.println("Không thể xóa thư mục.");
            }
        }
    }

    // Hiển thị cây thư mục
    public static void displayDirectoryTree(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        displayDirectoryTree(file);
                    }
                }
            }
        }
    }

    // Xóa thư mục và nội dung bên trong
    public static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (!deleteDirectory(file)) {
                            return false;
                        }
                    } else {
                        if (!file.delete()) {
                            return false;
                        }
                    }
                }
            }
            return directory.delete();
        }
        return false;
    }
}
