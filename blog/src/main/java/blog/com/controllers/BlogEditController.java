package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	
	
//	class BlogServiceが使えるように宣言
	@Autowired
	private BlogService blogService;

//	Sessionが使えるように宣言
	@Autowired
	private HttpSession session;

//	編集画面を表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
//		セッションからloginしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		もし、account==null login画面にリダイレクトする
		if (account == null) {
			return "redirect:/account/login";
		} else {
//			編集画面に表示させる情報を変数に格納 BLOG
			Blog blog = blogService.blogEditCheck(blogId);
//			もし、blog=nullだったら、商品一覧ページにリダイレクトする			
//			そうでない場合編集画面に編集する内容を渡す
//			編集画面を表示
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blog", blog);
				return "blog_edit.html";
			}

		}
	}

//	更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(
			@RequestParam String blogTitle, 
			@RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, 
			@RequestParam String article,
			@RequestParam Long blogId) {
//		セッションからloginしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
//		もし、account==null login画面にリダイレクトする
//		そうでない場合、
		/*
		 *  現在の日時情報をもとに、ファイル名を作成しています。SimpleDateFormatクラスを使用して、日時のフォーマットを指定している。 
		 * 具体的には、"yyyy-MM-dd-HH-mm-ss-"の形式でフォーマットされた文字列を取得している。 
		 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入
		 */
//		ファイルの保存
//		もし、blogUpdate の結果がtrueの場合、商品一覧にリダイレクトする
//		そうでない場合、商品編集画面にリダイレクト
if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (blogService.blogUpdate(blogId, categoryName, article, fileName, blogTitle,
					account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;

			}

		}
	}
}
