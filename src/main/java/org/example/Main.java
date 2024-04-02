package org.example;

import java.io.*;
import java.util.Arrays;

public class Main {
    static String url = "src/main/resources/A84F44A7-0D89-4E37-B686-5B4991422C05_1_105_c.jpeg";
    static File file;

    static {
        file = new File(url);
    }

    // 파일을 한번에 읽는 코드
    private static byte[] convertFile() {
        try (InputStream inputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

            byte[] buffer = new byte[(int) file.length()];
            bufferedInputStream.read(buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 라인 단위로 읽는 코드
    private static byte[] convertLine() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            content.append("\r\n");
            return content.toString().getBytes();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 바이트 단위로 읽는 코드
    private static byte[] convertByte() {
        try (InputStream inputStream = new FileInputStream(file)) {
            // 파일 내용을 바이트 배열로 읽어오기
            byte[] buffer = new byte[(int) file.length()];
            int bytesRead = inputStream.read(buffer);

            // bytesRead가 -1이 아니면 계속 읽기
            while (bytesRead != -1) {
                bytesRead = inputStream.read(buffer);
            }

            return buffer;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {

        System.out.println();

        System.out.println(Arrays.toString(convertFile()));
        System.out.println(Arrays.toString(convertLine()));
        System.out.println(Arrays.toString(convertByte()));
    }
}
