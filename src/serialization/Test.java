package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test{

	public static void main(String[] args) {
		Student student = new Student(202045038, "김민솔", "컴퓨터시스템과");
//		System.out.println(student.getId()+"\t"+student.getName()+"\t"+student.getMajor());

		// 직렬화
//		try {
//			FileOutputStream fsOut = new FileOutputStream("student.bin");
//			ObjectOutputStream osOut = new ObjectOutputStream(fsOut);
//			osOut.writeObject(student);  // 저장
//			osOut.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 역직렬화
		FileInputStream fsIn;
		try {
			fsIn = new FileInputStream("student.bin");
			ObjectInputStream osIn = new ObjectInputStream(fsIn);
			Student students = (Student) osIn.readObject();
			osIn.close();
			System.out.println(students.getId()+"\t"+students.getName()+"\t"+students.getMajor());
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
