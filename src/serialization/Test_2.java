package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test_2 {

	public static void main(String[] args) {
		int data = 0;
		//직렬화
        try {
            // File 객체 생성
            File file = new File("test.txt");
            
            // 이미 있는 경우 덮어씀
            FileOutputStream fos = new FileOutputStream(file);
            while((data = System.in.read()) != -1) {
                // 입력받은 내용을 파일 내용으로 기록
                fos.write(data);
            }
            fos.close(); // 파일을 닫기
        } catch (FileNotFoundException e) {  //FileOutputStream 객체 생성시 파일 경로가 유효하지 않으면 발생           
            e.printStackTrace();
        } catch (IOException e) { //read, write
			e.printStackTrace();
		} 
	

		// 역직렬화
//        try {
//        	File file = new File("test.txt");
//            FileInputStream fis = new FileInputStream(file);
//            while((data = fis.read()) != -1) {
//                // 입력받은 내용을 파일 내용으로 기록
//                System.out.write(data);
//            }
//            fis.close(); // 파일을 닫기
//        } catch (FileNotFoundException e) {  //FileOutputStream 객체 생성시 파일 경로가 유효하지 않으면 발생           
//            e.printStackTrace();
//        } catch (IOException e) { //read, write
//			e.printStackTrace();
//		} 
    }
}