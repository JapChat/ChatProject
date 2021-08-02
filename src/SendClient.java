import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
 
public class SendClient {
    public static void main(String[] args) {
    	
    	String serverIp = "127.0.0.1";
        Socket socket = null; //연결되지 않는 소캣 생성
         
        try {
            // 서버 연결 요청
            socket = new Socket(serverIp, 7777); //127.0.0.1서버에 접속
            System.out.println("서버에 연결되었습니다.");
 
            //파일전송        	
            String filePath = "C:\\Users\\0206c\\OneDrive\\바탕 화면";
            // 수신받을 파일의 경로 설정
//        	String fileNm = "mylove.png";
//        	String fileNm = "메모.txt";
//        	String fileNm = "인쇄.hwp";
        	String fileNm = "사진.zip";
            FileSender fs = new FileSender(socket, filePath, fileNm);
            fs.start();
                        
            //메세지 전송
            /*
            String msg = "미안해 화내서! 사랑해";
            MsgSender ms = new MsgSender(socket, msg);
            ms.start();
            */
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//파일 전송용 클래스
class FileSender extends Thread {
	
	String filePath;
	String fileNm;
    Socket socket;
    DataOutputStream dos;
    FileInputStream fis;
    BufferedInputStream bis;
 
    public FileSender(Socket socket, String filePath, String fileNm) {
    	
        this.socket = socket;
        this.fileNm = fileNm;
        this.filePath = filePath;
        
        try {
            // 데이터 전송용 스트림 생성
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // @Override
    public void run() {
    	
    	try {
    		
    		//파일전송을 서버에 알린다.('file' 구분자 전송)
			dos.writeUTF("file");
			dos.flush();
			
			//전송할 파일을 읽어서 Socket Server에 전송
			String result = fileRead(dos);
	    	System.out.println("result : " + result);
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
            try { dos.close(); } catch (IOException e) { e.printStackTrace(); }
            try { bis.close(); } catch (IOException e) { e.printStackTrace(); }
        }

    }
    
    
    private String fileRead(DataOutputStream dos){
    	
        String result;
    	
    	try {
            System.out.println("파일 전송 작업을 시작합니다.");
 
            dos.writeUTF(fileNm);
            System.out.println("파일 이름(" + fileNm + ")을 전송하였습니다.");
 
            // 파일을 읽어서 서버에 전송
            File file = new File(filePath + "/" + fileNm);
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            
            int len;
            int size = 4096;
            byte[] data = new byte[size];
            while ((len = bis.read(data)) != -1) {
                dos.write(data, 0, len);
            }
            
            //서버에 전송
            dos.flush();
            
            /* -- 먹통된다.
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            result = dis.readUTF();
            if( result.equals("SUCCESS") ){
            	System.out.println("파일 전송 작업을 완료하였습니다.");
            	System.out.println("보낸 파일의 사이즈 : " + file.length());
            }else{
            	System.out.println("파일 전송 실패!.");
            }
            */
            
            result = "SUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
            result = "ERROR";
        }finally{
            try { fis.close(); } catch (IOException e) { e.printStackTrace(); }
        }
        
        return result;
    }
}

//메세지 전송용 클래스
class MsgSender extends Thread {
	
    Socket socket;
    String msg;
    DataOutputStream dos;
    FileInputStream fis;
    BufferedInputStream bis;
 
    public MsgSender(Socket socket, String msg) {
    	
        this.socket = socket;
        this.msg = msg;
        
        try {
            // 데이터 전송용 스트림 생성
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 
    //@Override
    public void run() {
    	
    	try {
    		//파일전송 구분자 전송('msg' 전송)
			dos.writeUTF("msg");
			dos.flush();
    		
			dos.writeUTF( msg );
			dos.flush();
			
	    	System.out.println("[" + msg + "] 전송");
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
            try { dos.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
}