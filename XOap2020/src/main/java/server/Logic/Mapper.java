package server.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import gameModel.Board;
import gameModel.User;

public class Mapper {

	private Gson gson;
	public Mapper() {
		gson=new Gson();
	}
	public void makeProfile(User user) throws IOException {
		FileWriter f=new FileWriter(System.getProperty("user.dir")+"\\XOap2020\\src\\main\\java\\server\\usersfile\\"+user.getName());
		String se=gson.toJson(user);
		f.write(se);
		f.close();
	}
	public Boolean checkName(String name ,String pass) throws IOException {
		File file=new File(System.getProperty("user.dir")+"\\XOap2020\\src\\main\\java\\server\\user\\all.txt");
		Scanner s=new Scanner(file);
		boolean isther=false;
		while (s.hasNext()) {
			String line=s.nextLine();
			if(line.startsWith(name)) {
				String pa=s.nextLine();
				if(pa.startsWith(pass))
					isther=true;
			}
		}
		s.close();
		return isther;
	}
	public boolean checkValid(String s) throws IOException {
		boolean  re=false;
		File file=new File(System.getProperty("user.dir")+"\\XOap2020\\src\\main\\java\\server\\user\\all.txt");
		Scanner ss=new Scanner(file);
		while (ss.hasNext()) {
			String line=ss.nextLine();
			if(line.startsWith(s) ) {
				re=true;
			}
		}
		ss.close();
		return re;
	}
	public void writeName(String name ,String pass) throws IOException {
		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\XOap2020\\src\\main\\java\\server\\user\\all.txt",true);
		file.write(name+"\n");
		file.write(pass+"\n");
		file.close();
	}

	public User readPlayer(String name) throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\XOap2020\\src\\main\\java\\server\\usersfile\\"+name);
		Scanner s=new Scanner(f);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		return gson.fromJson(se, User.class);	
	}
	public User login(String name, String pass) throws IOException {
		if(checkName(name, pass)) {
			return readPlayer(name);	
		}
		return null;
	}
	public ArrayList<User> sort(ArrayList<User> user){
		try {
			for (User user2 : readallUser()) {
				Boolean y=false;
				for(User u:user) {
					if(u.getName().equalsIgnoreCase(user2.getName())) {
						y=true;
						break;
					}
				}
				if(y==false)
					user.add(user2);
			}
		} catch (FileNotFoundException e) {}
		ArrayList<User> ne= new ArrayList<>();
		for(int i=user.size()-1 ;i>=0;i--) {
			int index=i;
			int top=user.get(i).getScore();
			for (User user2 : user) {
				if(user2.getScore()>top) {
					index =user.indexOf(user2);
					top=user2.getScore();
				}
			}
			ne.add(user.get(index));
			user.remove(index);
		}
		return ne;
	}

	private ArrayList<User> readallUser() throws FileNotFoundException {
		ArrayList<User> x= new ArrayList<>();
		File fa=new File(System.getProperty("user.dir")+"\\XOap2020\\src\\main\\java\\server\\usersfile");
		File[] dirr=fa.listFiles();
		if(dirr!=null) {
			for(File ch:dirr) {
				x.add(gson.fromJson(readFileString(ch), User.class));
			}
		}		
		return x;
	}
	private String readFileString(File f) throws FileNotFoundException {
		Scanner sca=new Scanner(f);
		String t1="";
		while(sca.hasNext()) {
			t1+=sca.nextLine();
		}
		return t1;
	}
}