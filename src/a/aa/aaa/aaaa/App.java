package a.aa.aaa.aaaa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import a.aa.aaa.aaaa.dto.Article;
import a.aa.aaa.aaaa.util.Util;

public class App {

	private List<Article> articles;
	App() {
		articles = new ArrayList<>();
	}
	
	public void run() {

		System.out.println("---프로그램 시작---");
		
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleID = articles.size();
		
		while(true) {
			
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			if(cmd.equals("exit")) {
				break;
				
			}if(cmd.equals("article write")) {
				int id = lastArticleID + 1;
				lastArticleID = id;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목) ");
				String title = sc.nextLine();
				System.out.printf("내용) ");
				String body = sc.nextLine();
			
				Article article = new Article(id ,regDate ,title, body);
				
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다\n",id);
				
			}else if(cmd.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}System.out.println("번호	|	제목	|	날짜	|	조회수");
				
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id ,article.title, article.regDate,article.viewCnt);
				}
			}else if(cmd.startsWith("article detail")) {
				String[] cmdbits = cmd.split(" ");
				int id = Integer.parseInt(cmdbits[2]);
				
				Article foundArticle = null;
				
				for(int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if(foundArticle == null){
					System.out.printf("%d번 게시물은 존재하지 않습니다\n",id);
					continue;
				}
				
				foundArticle.addViewCnt();
				System.out.printf("번호 : %d\n",foundArticle.id);
				System.out.printf("날짜 : %s\n",foundArticle.regDate);
				System.out.printf("제목 : %s\n",foundArticle.title);
				System.out.printf("내용 : %s\n",foundArticle.body);
				System.out.printf("조회수 : %s\n",foundArticle.viewCnt);
				
			}else if(cmd.startsWith("article delete")) {
				String[] cmdbits = cmd.split(" ");
				int id = Integer.parseInt(cmdbits[2]);
				
				int foundIndex = -1;
				
				for(int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundIndex = i;
						break;
					}
				}
				if(foundIndex == -1){
					System.out.printf("%d번 게시물은 존재하지 않습니다\n",id);
					continue;
				}
				
				articles.remove(foundIndex);
				
				System.out.printf("%d번 게시물이 삭제되었습니다\n",id);
				
			}else if(cmd.startsWith("article modify")) {
				String[] cmdbits = cmd.split(" ");
				int id = Integer.parseInt(cmdbits[2]);
				
				Article foundArticle = null;
				
				for(int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					
					if(article.id == id) {
						foundArticle = article;
						break;
					}
				}
				if(foundArticle == null){
					System.out.printf("%d번 게시물은 존재하지 않습니다\n",id);
					continue;
				}

				System.out.printf("수정할 제목) ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용) ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				
				System.out.printf("%d번 게시물이 수정되었습니다\n",id);
			}else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}
		
		System.out.println("---프로그램 끝---");
		
		sc.close();
	}

	private void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다");
		articles.add(new Article(1, Util.getNowDateStr(),"제목1","내용1",11));
		articles.add(new Article(2, Util.getNowDateStr(),"제목2","내용2",22));
		articles.add(new Article(3, Util.getNowDateStr(),"제목3","내용3",33));
	}

}
