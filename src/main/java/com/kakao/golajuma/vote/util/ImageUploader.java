package com.kakao.golajuma.vote.util;

import com.kakao.golajuma.vote.domain.exception.ImageException;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ImageUploader {

	private static final String SYSTEM_PATH = System.getProperty("user.dir");
	private static final String UPLOAD_PATH = "/src/main/java/com/kakao/golajuma/vote/infra/images/";
	private static final String PNG = ".png";
	private static final String JPEG = ".jpeg";

	public String uploadImageByBase64(CreateVoteRequest.OptionDto request) {
		String base64 = request.getImage();
		String fileName = request.getName();

		// 파일이 업로드되지 않았거나 사이즈가 큰 경우를 체크합니다.
		// 사이즈는 일반 바이트에서 1.33을 곱하면 BASE64 사이즈가 대략 나옵니다.
		if (base64 == null || base64.equals("")) {
			throw new ImageException("이미지가 null 입니다");
		}
		if (base64.length() > 400000) {
			throw new ImageException("이미지가 너무 큽니다");
		}

		try {
			// 저장할 파일 경로를 지정합니다.
			String changedName = UUID.randomUUID().toString() + "_" + fileName;
			String uploadPath = SYSTEM_PATH + UPLOAD_PATH;

			if (base64.startsWith("data:image/png;base64,")) {
				base64 = base64.substring("data:image/png;base64,".length());
				changedName += PNG;
			}
			if (base64.startsWith("data:image/jpeg;base64,")) {
				base64 = base64.substring("data:image/jpeg;base64,".length());
				changedName += JPEG;
			}
			if (base64.startsWith("data:image/gif;base64,")) {
				base64 = base64.substring("data:image/gif;base64,".length());
				changedName += JPEG;
			}

			String storedPath = uploadPath + changedName;
			File file = new File(storedPath);

			// BASE64를 일반 파일로 변환하고 저장합니다.
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decodedBytes = decoder.decode(base64.getBytes());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(decodedBytes);
			fileOutputStream.close();
			return storedPath;
		} catch (IOException e) {
			throw new ImageException("이미지 저장 실패");
		}
	}

	public static String getImage(String imagePath) {
		if (imagePath == null || imagePath.equals("")) {
			return null;
		}
		return encodingImage(imagePath);
	}

	private static String encodingImage(String imagePath) {
		File imageFile = new File(imagePath);
		try {
			BufferedImage image = ImageIO.read(imageFile);
			// 이미지 파일의 확장자를 추출
			String extension = imagePath.substring(imagePath.lastIndexOf('.') + 1);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, getExtension(extension), baos);
			String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

			base64Image = ImageType(extension) + base64Image;
			return base64Image;
		} catch (Exception e) {
			throw new ImageException("이미지를 찾을 수 없습니다.");
		}
	}

	private static String ImageType(String extension) {
		if (extension.equals("png")) {
			return "data:image/png;base64,";
		}
		if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
			return "data:image/jpeg;base64,";
		}
		if ("gif".equalsIgnoreCase(extension)) {
			return "data:image/gif;base64,";
		}
		throw new ImageException("지원되지 않는 이미지 형식입니다.");
	}

	private static String getExtension(String extension) {
		if (extension.equals("png")) {
			return "png";
		}
		if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
			return "jpg";
		}
		if ("gif".equalsIgnoreCase(extension)) {
			return "gif";
		}
		throw new ImageException("지원되지 않는 이미지 형식입니다.");
	}
}
