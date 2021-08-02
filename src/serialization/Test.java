package serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Test{

	public static void main(String[] args) {
		Student student = new Student(2020, "스누피", "컴퓨터과");
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
			ObjectInput osIn = new ObjectInputStream(fsIn);
			Student students = (Student) osIn.readObject();
			osIn.close();
			System.out.println(student.getId()+"\t"+student.getName()+"\t"+student.getMajor());
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
