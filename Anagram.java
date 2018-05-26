import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

public class Anagram{

	public static int tableSize;
	public static List<String>[] lists;
	public static List anagrams;

	public static int hash(String str){
		int r = 0;
		String s = sort(str);
		for(int j=0; j<s.length(); j++){
			r = (s.charAt(j) + 257*r)%tableSize;
		}

		return r;
	}

	public static String sort(String str){
		int[] arr = new int[37];
		int ascii;
		int l=0;
			for(int j=0; j<str.length(); j++){
				ascii = str.charAt(j);
				if(ascii>=97&&ascii<=122){
					l = ascii%97;
				}
				else if(ascii>=48 && ascii <= 57){
					l = 27 + ascii%48;
				}
				else if(ascii==39){
					l = 26;
				}
				arr[l]+=1;
			}
			String s = "";
			char c = '\0';
			for(int j=0; j<37; j++){

				if(j>=0&&j<26){
					while(arr[j]!=0){
						c = (char)(97+j);
						s = s + c;
						arr[j]--;
					}
				}
				if(j==26){
					while(arr[j]!=0){
						c = (char)(39);
						s = s + c;
						arr[j]--;
					}
				}
				if(j>=27&&j<=36){
					while(arr[j]!=0){
						c = (char)(48+j%27);
						s = s + c;
						arr[j]--;
					}
				}

				
			}
		return new String(s);
	}

	public static List nCtwo(String str){
		List<String> lis = new ArrayList<String>();
		for(int j=0; j<=str.length()-2; j++){
			if(j>0){
				if(str.charAt(j)==str.charAt(j-1)){
					continue;
				}
			}
			for(int k=j+1; k<=str.length()-1; k++){
				if(k>j+1){
					if(str.charAt(k)==str.charAt(k-1)){
						continue;
					}
				}
				lis.add(""+str.charAt(j)+str.charAt(k));
			}
		}
		return lis;
	}

	public static List nCthree(String str){
		String s,t;
		List<String> lis,lis1 = new ArrayList<String>();
		for(int i=0; i<=str.length()-3; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCtwo(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){

				lis1.add(""+str.charAt(i)+lis.get(j));
			}
		}

		return lis1;
	}

	public static List nCfour(String str){
		String s,t;
		List<String> lis,lis1 = new ArrayList<String>();
		for(int i=0; i<=str.length()-4; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCthree(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
				lis1.add(""+str.charAt(i)+lis.get(j));
			}
		}

		return lis1;
	}

	public static List nCfive(String str){
		String s,t;
		List<String> lis,lis1 = new ArrayList<String>();
		for(int i=0; i<=str.length()-5; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCfour(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
				lis1.add(""+str.charAt(i)+lis.get(j));
			}
		}

		return lis1;
	}

	public static List nCsix(String str){
		String s,t;
		List<String> lis,lis1 = new ArrayList<String>();
		for(int i=0; i<=str.length()-6; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCfive(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
				lis1.add(""+str.charAt(i)+lis.get(j));
			}
		}

		return lis1;
	}

	public static void nCthreefunc(String str){
		String s,t;
		List<String> lis = new ArrayList<String>();
		for(int i=0; i<=str.length()-3; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCtwo(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
					t = str;
					s = str.charAt(i)+lis.get(j);
					String sorted1 = sort(s);

					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(0)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(1)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==str.charAt(i)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					
					String sorted2 = sort(t);

					int r = hash(s);
					int z = hash(t);
					
					for(int l =0; l<lists[r].size(); l++){

						if(sorted1.equals(sort(lists[r].get(l)))){

							for(int m = 0; m<lists[z].size(); m++){
								if(sorted2.equals(sort(lists[z].get(m)))){
									anagrams.add(lists[r].get(l) + " " + lists[z].get(m));
									if(str.length()>6&&!lists[z].get(m).equals(lists[r].get(l))){
										anagrams.add(lists[z].get(m) + " " + lists[r].get(l));
										
									}
									
								}
							}
						}
					}
			}
		}
	}

	public static void nCfourfunc(String str){
		String s,t;
		List<String> lis = new ArrayList<String>();
		for(int i=0; i<=str.length()-4; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCthree(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
					t = str;
					s = str.charAt(i)+lis.get(j);
					String sorted1 = sort(s);

					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(0)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(1)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(2)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==str.charAt(i)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					
					String sorted2 = sort(t);

					int r = hash(s);
					int z = hash(t);
					
					for(int l =0; l<lists[r].size(); l++){

						if(sorted1.equals(sort(lists[r].get(l)))){

							for(int m = 0; m<lists[z].size(); m++){
								if(sorted2.equals(sort(lists[z].get(m)))){
									anagrams.add(lists[r].get(l) + " " + lists[z].get(m));
									
									if(str.length()>8&&!lists[z].get(m).equals(lists[r].get(l))){
										anagrams.add(lists[z].get(m) + " " + lists[r].get(l));
										
									}
								}
							}
						}
					}
			}
		}
	}

	public static void nCfivefunc(String str){
		String s,t;
		List<String> lis = new ArrayList<String>();
		for(int i=0; i<=str.length()-5; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCfour(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
					t = str;
					s = str.charAt(i)+lis.get(j);
					String sorted1 = sort(s);

					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(0)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(1)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(2)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(3)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==str.charAt(i)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					
					String sorted2 = sort(t);

					int r = hash(s);
					int z = hash(t);
					
					for(int l =0; l<lists[r].size(); l++){

						if(sorted1.equals(sort(lists[r].get(l)))){

							for(int m = 0; m<lists[z].size(); m++){
								if(sorted2.equals(sort(lists[z].get(m)))){
									anagrams.add(lists[r].get(l) + " " + lists[z].get(m));
									

									if(str.length()>10&&!lists[z].get(m).equals(lists[r].get(l))){
										anagrams.add(lists[z].get(m) + " " + lists[r].get(l));
									}
								}
							}
						}
					}
			}
		}
	}

	public static void nCsixfunc(String str){
		String s,t;
		List<String> lis = new ArrayList<String>();
		for(int i=0; i<=str.length()-6; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCfive(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
					t = str;
					s = str.charAt(i)+lis.get(j);
					String sorted1 = sort(s);

					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(0)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(1)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(2)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(3)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(4)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==str.charAt(i)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					
					String sorted2 = sort(t);

					int r = hash(s);
					int z = hash(t);
					
					for(int l =0; l<lists[r].size(); l++){

						if(sorted1.equals(sort(lists[r].get(l)))){

							for(int m = 0; m<lists[z].size(); m++){
								if(sorted2.equals(sort(lists[z].get(m)))){
									anagrams.add(lists[r].get(l) + " " + lists[z].get(m));
									
									if(str.length()>12&&!lists[z].get(m).equals(lists[r].get(l))){
										anagrams.add(lists[z].get(m) + " " + lists[r].get(l));
										
									}
								}
							}
						}
					}
			}
		}
	}

	public static void nCthreethreethreefunc(String str){
		List<String> lis1 = nCthreefuncList(str);
		List<String> lis2;
		String s,s1,s2,s3;

		for(int i=0;i<lis1.size();i++){
			s1 = lis1.get(i).substring(0,3);
			s1 = sort(s1);
			s = lis1.get(i).substring(4, lis1.get(i).length());
			lis2 = nCthreefuncList(s);
			for(int j=0; j< lis2.size(); j++){

			s2 = lis2.get(j).substring(0,3);
			s3 = lis2.get(j).substring(4,lis2.get(j).length());

			int r1 = hash(s1);
			int r2 = hash(s2);
			int r3 = hash(s3);
			for(int k=0;k<lists[r1].size();k++){
				if(sort(lists[r1].get(k)).equals(s1)){
					for(int l=0;l<lists[r2].size();l++){
						if(sort(lists[r2].get(l)).equals(s2)){
							for(int m=0;m<lists[r3].size();m++){
								if(sort(lists[r3].get(m)).equals(s3)){
									String z1 = lists[r1].get(k);
									String z2 = lists[r2].get(l);
									String z3 = lists[r3].get(m);
									if(str.length()==9){
										anagrams.add(z1+" "+z2+" "+z3);
										
									}
									else if(str.length()>=10){
										anagrams.add(z1+" "+z2+" "+z3);
										
										anagrams.add(z2+" "+z3+" "+z1);
										
										anagrams.add(z3+" "+z1+" "+z2);
										
									}
								}
							}
						}
					}
				}
			}
			}
		}

	}

	public static void nCthreefourfourfunc(String str){
		List<String> lis1 = nCthreefuncList(str);
		List<String> lis2;
		String s,s1,s2,s3;

		for(int i=0;i<lis1.size();i++){
			s1 = lis1.get(i).substring(0,3);
			s1 = sort(s1);
			s = lis1.get(i).substring(4, lis1.get(i).length());

			lis2 = nCfourfuncList(s);
			for(int j=0; j< lis2.size(); j++){

			s2 = lis2.get(j).substring(0,4);
			s3 = lis2.get(j).substring(5,lis2.get(j).length());

			int r1 = hash(s1);
			int r2 = hash(s2);
			int r3 = hash(s3);
			for(int k=0;k<lists[r1].size();k++){
				if(sort(lists[r1].get(k)).equals(s1)){
					for(int l=0;l<lists[r2].size();l++){
						if(sort(lists[r2].get(l)).equals(s2)){
							for(int m=0;m<lists[r3].size();m++){
								if(sort(lists[r3].get(m)).equals(s3)){
									String z1 = lists[r1].get(k);
									String z2 = lists[r2].get(l);
									String z3 = lists[r3].get(m);
									if(str.length()>=11){
										anagrams.add(z1+" "+z2+" "+z3);
										
										anagrams.add(z2+" "+z3+" "+z1);
										
										anagrams.add(z3+" "+z1+" "+z2);
										
									}
									if(str.length()>=12){
										anagrams.add(z1+" "+z3+" "+z2);
										
										anagrams.add(z2+" "+z1+" "+z3);
										
										anagrams.add(z3+" "+z2+" "+z1);
										
									}
								}
							}
						}
					}
				}
			}
			}
		}

	}

	public static void nCfourfourfourfunc(String str){
		List<String> lis1 = nCfourfuncList(str);
		List<String> lis2;
		String s,s1,s2,s3;

		for(int i=0;i<lis1.size();i++){
			s1 = lis1.get(i).substring(0,4);
			s1 = sort(s1);
			s = lis1.get(i).substring(5, lis1.get(i).length());

			lis2 = nCfourfuncList(s);
			for(int j=0; j< lis2.size(); j++){

			s2 = lis2.get(j).substring(0,4);
			s3 = lis2.get(j).substring(5, lis2.get(j).length());

			int r1 = hash(s1);
			int r2 = hash(s2);
			int r3 = hash(s3);
			for(int k=0;k<lists[r1].size();k++){
				if(sort(lists[r1].get(k)).equals(s1)){
					for(int l=0;l<lists[r2].size();l++){
						if(sort(lists[r2].get(l)).equals(s2)){
							for(int m=0;m<lists[r3].size();m++){
								if(sort(lists[r3].get(m)).equals(s3)){
									String z1 = lists[r1].get(k);
									String z2 = lists[r2].get(l);
									String z3 = lists[r3].get(m);
									anagrams.add(z1+" "+z3+" "+z2);
									
								}
							}
						}
					}
				}
			}
			}
		}

	}

	public static List nCthreefuncList(String str){
		String s,t;
		List<String> lis,lis1 = new ArrayList<String>();
		for(int i=0; i<=str.length()-3; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCtwo(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
					t = str;
					s = str.charAt(i)+lis.get(j);
					String sorted1 = sort(s);

					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(0)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(1)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==str.charAt(i)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}

					String sorted2 = sort(t);

					lis1.add(sorted1 + " " + sorted2);
			}
		}
		return lis1;
	}

	public static List nCfourfuncList(String str){
		String s,t;
		List<String> lis,lis1 = new ArrayList<String>();
		for(int i=0; i<=str.length()-4; i++){
			if(i>0){
				if(str.charAt(i)==str.charAt(i-1)){
					continue;
				}
			}
			lis = nCthree(str.substring(i+1, str.length()));

			for(int j=0; j<lis.size(); j++){
					t = str;
					s = str.charAt(i)+lis.get(j);
					String sorted1 = sort(s);

					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(0)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(1)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==lis.get(j).charAt(2)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					for(int k = 0; k< t.length(); k++){
						if(t.charAt(k)==str.charAt(i)){
							t = t.substring(0,k) + t.substring(k+1, t.length());
							break;
						}
					}
					
					String sorted2 = sort(t);
					lis1.add(sorted1+" "+sorted2);

			}
		}
		return lis1;
	}

	public static void permute(String str){
		
		int r = hash(str);

		String sorted = sort(str);

		for(int i =0; i<lists[r].size(); i++){
			if(sorted.equals(sort(lists[r].get(i)))){
				anagrams.add(lists[r].get(i));
				
			}
		}
		if(str.length()==6){
			nCthreefunc(str);
		}
		else if(str.length()==7){
			nCthreefunc(str);
		}
		else if(str.length()==8){
			nCthreefunc(str);
			nCfourfunc(str);
		}
		else if(str.length()==9){
			nCthreefunc(str);
			nCfourfunc(str);
			nCthreethreethreefunc(str);
		}
		else if(str.length()==10){
			nCthreefunc(str);
			nCfourfunc(str);
			nCfivefunc(str);
			nCthreethreethreefunc(str);
		}
		else if(str.length()==11){
			nCthreefunc(str);
			nCfourfunc(str);
			nCfivefunc(str);
			nCthreethreethreefunc(str);
			nCthreefourfourfunc(str);
		}
		else if(str.length()==12){
			nCthreefunc(str);
			nCfourfunc(str);
			nCfivefunc(str);
			nCsixfunc(str);
			nCthreethreethreefunc(str);
			nCthreefourfourfunc(str);
			nCfourfourfourfunc(str);
		}
	}

	public static void main(String args[]){
		try {
		FileInputStream fstream = new FileInputStream(args[0]);
		Scanner s = new Scanner(fstream);
		int sizeOfVocabulary = s.nextInt();
		s.nextLine();
		//tableSize = (int) 1.3*sizeOfVocabulary;
		tableSize = 40009;

		lists = new ArrayList[tableSize];
		for(int i=0;i<tableSize; i++){
			lists[i] = new ArrayList<String>();
		}

		String str;
		
		for(int i=0; i<sizeOfVocabulary; i++){	
			str = s.nextLine();
			int flag = 0;
			for(int op = 0;op<str.length();op++){
				if((str.charAt(op)>=97&&str.charAt(op)<=122)||(str.charAt(op)>=48&&str.charAt(op)<=57)||str.charAt(op)=='\''){

				}
				else{
					flag=1;
				}
			}
			if(flag==0)
			lists[hash(str)].add(str);
		}

		FileInputStream fstream1 = new FileInputStream(args[1]);
		Scanner j = new Scanner(fstream1);

		int n = j.nextInt();
		j.nextLine();
		for(int i=0; i<n; i++){
			String input = j.nextLine();

			if(input.length()>=3 && input.length()<=12){
				anagrams = new ArrayList();
				input = sort(input);
				permute(input);
				
				Collections.sort(anagrams);
				for(int k=0; k<anagrams.size(); k++){
					if(k>0){
						if(anagrams.get(k).equals(anagrams.get(k-1)))
							continue;
					}
					System.out.println(anagrams.get(k));

				}

			}
			System.out.println(-1);
		}

		}catch(FileNotFoundException e) {}
	
	}
}
