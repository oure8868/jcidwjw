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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	
//	class BlogServiceが使えるように宣言
	@Autowired
	private BlogService blogService;

//	Sessionが使えるように宣言
	@Autowired
	private HttpSession session;

	
//	商品画面の表示
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
//		セッションからloginしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
//		もし、account==null login画面にリダイレクトする
//		そうでない場合、ログインしている人の名前を画面に渡す
//		商品登録のHTMLを表示させる
		if (account == null) {
			return "redirect:/account/login";
		} else {
			model.addAttribute("accountName", account.getAccountName());
			return "blog_register.html";
		}
	}

//	商品の登録処理
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(
			@RequestParam String blogTitle, 
			@RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, 
			@RequestParam String article) {
//		セッションからloginしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		
//		もし、account==null login画面にリダイレクトする
//		そうでない場合、画面のファイル名を取得
//		画像のアップロード
//		もし、同じファイルの名前がなかったら保存
//		商品の一覧画面にリダイレクトする
//		そうでない場合、商品登録画面にとどまります
//		
		if (account == null) {
			return "redirect:/account/login";
		} else {
//			ファイル名を取得
			/*
			 * 現在の日時情報をもとに、ファイル名を作成しています。SimpleDateFormatクラスを使用して、日時のフォーマットを指定している。
			 * 具体的には、"yyyy-MM-dd-HH-mm-ss-"の形式でフォーマットされた文字列を取得している。
			 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入
			 */
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();

			try {
//				ファイルの保存作業
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (blogService.createBlog(blogTitle, categoryName, fileName, article,
					account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "blog_register.html";
			}
		}
	}
}
