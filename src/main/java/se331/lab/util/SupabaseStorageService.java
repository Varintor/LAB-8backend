package se331.lab.util;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SupabaseStorageService {

    @Value("${supabase.storage.bucket}")
    String bucketName;

    @Value("${supabase.storage.endpoint_output}")
    String outputUrl;

    private final S3Client s3Client;
//    private final S3Presigner s3Presigner;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddmmssSSS");

    public String uploadFile(MultipartFile file) throws IOException {
        // สร้าง temp file
        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        // ตั้งชื่อไฟล์กันชนกัน
        String saltFileName = LocalDateTime.now().format(formatter) + "-" + file.getOriginalFilename();

        // สร้าง PutObjectRequest
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(saltFileName)
                .build();

        // อัปโหลดไฟล์ขึ้น Supabase S3
        s3Client.putObject(putObjectRequest, RequestBody.fromFile(tempFile));

        String url = String.format("%s/%s/%s", outputUrl, bucketName, saltFileName);

        // ลบ temp file
        Files.delete(tempFile);

        // คืน URL สำหรับเข้าถึงไฟล์
        return String.format("%s/%s/%s", outputUrl, bucketName, saltFileName);
    }

    public StorageFileDto uploadImage(MultipartFile file) throws ServletException, IOException {
        String fileName = file.getOriginalFilename();

        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            final String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

            String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
            for (String s : allowedExt) {
                if (extension.equals(s)) {
                    // เรียก method อัปโหลดปกติที่คุณมีอยู่แล้ว
                    String urlName = this.uploadFile(file);

                    return StorageFileDto.builder()
                            .name(urlName)
                            .build();
                }
            }
            throw new ServletException("file must be an image");
        }
        throw new ServletException("invalid file");
    }
}
