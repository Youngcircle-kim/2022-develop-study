import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
class Juso{
    private User address;
    public void print_menu(){
        //메뉴 소환
        System.out.println("1. 연락처 출력\n2. 연락처 등록\n3. 연락처 삭제\n4. 끝내기");
    }
    public void init(File file,ArrayList<User> list)throws IOException {
        //Reader 와 BufferedReader 선언 및 초기화
        Reader fr = null;
        BufferedReader br = null;
        //예외 발생을 위한 try-catch
        try{
            fr = new FileReader("C:\\Users\\삼성\\부엉이\\soju.txt");
            br = new BufferedReader(fr);
            String line = "";
            String[] words = new String[3];
            while((line = br.readLine()) != null){
                words = line.split("\t");
                list.add(new User(words[0], words[1], words[2]));
            }
            fr.close();
            br.close();
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void view_juso(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("C:\\Users\\삼성\\부엉이\\soju.txt"));         
        //읽을 라인이 없을 때까지 루프를 돌린다.
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();            
            System.out.println(str);        
        }  
    }
    public void add_juso(File file, String name, String age, String number,ArrayList<User> List) throws IOException{
        address = new User(name, age, number);
        List.add(address);
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < List.size(); i++) {
            fw.write(List.get(i).getName()+"\t"+List.get(i).getAge()+"\t"+List.get(i).getNumber()+"\r\n");
        }
        fw.close();
        }
    public void delete_juso(ArrayList<User> List,File file) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean run =true;
        System.out.println("1. 순번 삭제 \r\n2. 이름 삭제 \r\n3. 번호 삭제 \r\n4. 메인 메뉴로 이동 \r\n세부 메뉴를 선택하세요: ");
        String deleteNum = sc.next();
        switch (deleteNum) {
            //  연락처 순번으로 연락처를 삭제한다.
            case "1":
                while(run){
                    try {
                        System.out.println();
                        System.out.print("삭제할 연락처 순번은?: ");
                        int length = sc.nextInt()-1;
                        List.remove(length);
                        System.out.printf("%d번 연락처가 삭제되었습니다.\r\n", length+1);
                        System.out.println();
                        update(List, file);
                        break;
                    } catch(IndexOutOfBoundsException ex) {
                        System.out.println("존재하지 않는 연락처입니다.");
                        run = false;
                        break;
                    } finally {
                        System.out.println("숫자를 입력해주세요.");
                    }
                }
                break;
             // 입력한 이름으로 연락처를 삭제한다.
            case "2":
                    System.out.println();
                    System.out.print("삭제할 연락처 이름은?: ");
                    String name = sc.next();
                    for (int i = 0; i < List.size(); i++) {
                        System.out.println(List.get(i).getName());
                        if(name.equals(List.get(i).getName())) {
                            List.remove(i);
                            System.out.println(name + " 연락처가 삭제되었습니다.\n");
                            break;
                        } else {
                            System.out.println("존재하지 않는 연락처입니다.\n");
                            break;
                        }
                        
                    }
                    update(List, file);
                    break;
                
            // 입력한 전화번호로 연락처를 삭제한다.
            case "3":
                System.out.print("\r\n삭제할 연락처 번호는?: ");
                String number = sc.next();
                for (int j = 0; j < List.size(); j++) {
                    if(number.equals(List.get(j).getNumber())) {
                        List.remove(j);
                        System.out.println(number + " 연락처가 삭제되었습니다.");
                    } else {
                        System.out.println("존재하지 않는 연락처입니다.");
                    }
                }
                update(List, file);
                break;
            case "4":
                print_menu();
                break;
            default:
                System.out.println("잘못된 세부 메뉴입니다.");
                delete_juso(List, file);
            sc.close();

        }
    }
    public void update(ArrayList<User> List, File file) throws IOException{
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < List.size(); i++) {
            fw.write(List.get(i).getName()+"\t"+List.get(i).getAge()+"\t"+List.get(i).getNumber()+"\r\n");
        }
        fw.close();
    }
    public void createFile(File file) throws IOException{
        try {
            if (file.createNewFile()) {
            }
            else {
                System.out.println("파일 생성 완료.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class User {
    public String name;
    public String age;
    public String number;

    public User(String name, String age, String number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}

public class mainJuso{
    public static void main(String[] args) throws IOException {
        //String 이름 나이 전화번호 선언
        String name, age, number;
        //Scanner on
        Scanner sc = new Scanner(System.in);
        //Juso 클래스 객체 선언및 생성
        Juso js = new Juso();
        //ArrayList User 타입으로 선언 및 생성
        ArrayList<User> list = new ArrayList<>();
        //While 멈추기 위한 boolean 타입 run 선언및 true 값으로 생성.
        boolean run= true;
        //File 선언 및 pathName에 파일 생성
        File file = new File("C:\\Users\\삼성\\부엉이\\soju.txt");
        System.out.println("[기말 프로젝트] 60221304 김영환\r\n[연락처 관리 프로그램]");
        //file 중복 확인
        js.createFile(file);
        //파일에 있는 값 어레이에 집어 넣기
        js.init(file, list);
        //4.끝내기 선택할 때까지 무한 루프
        while(run){
            // 메뉴 소환
            js.print_menu();
            String i = sc.next();
            //예외나 오류 방지를 위한 String으로 입력받기
            switch(i){
                case "1":
                    //연락처 출력
                    js.view_juso(file);
                    break;
                case "2":
                    //연락처 등록
                    System.out.print("이    름 : ");
                    name = sc.next();
                    System.out.print("나    이 : ");
                    age = sc.next();
                    System.out.print("전화번호 : ");
                    number = sc.next();
                    js.add_juso(file,name, age , number,list);
                    break;
                case "3":
                    //연락처 삭제
                    js.delete_juso(list, file);
                    break;                
                case "4":
                    //끝내기
                    System.out.println("프로그램이 종료되었습니다.");
                    run= false;
                    break;
                default:
                    System.out.println("\r\n잘못된 메뉴입니다.");
                }
            } 
        sc.close();
        }
}
