package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

//	商品一覽のチェック
//	もしaccountId == null 戻り値としてnull
//	findAll内容をコントローラーclassに渡す
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findByAccountId(accountId);
		}
	}

//	商品の登録処理チェック
//	もし、findByblogTitleAndAccountId==null
//			処理
//			true
//			そうでない場合
//			false
	public boolean createBlog(
			String blogTitle, 
			String categoryName, 
			String blogImage,
			String article, 
			Long accountId) {
		// blogTitle と accountId に基づいてチェックする
	    if (blogDao.findByBlogTitleAndAccountId(blogTitle, accountId) == null) {
	        blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
	        return true;
	    } else {
	        return false;
	    }
//		if (blogDao.findByBlogTitle(blogTitle) == null) {
//			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
//			return true;
//		} else {
//			return false;
//		}

	}
	
	
//	編集画面を表示する時のチェック
//	もし、blogId == null ，null
//	そうでない場合、
//	findByBlogIdの情報をコントローラーclassに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
//	更新処理のチェックの
	
//	もし、blogId=nullだったら、更新処理はしない
//	false
//	そうでない場合
//	更新処理をする
//	コントローラーclassからもらった、blogIdを使って、編集する前のデータを取得
//	変更するべきところだけ、セッターを使用してデータの更新をする
//	trueを返す
	public boolean blogUpdate(
			Long blogId, 
			String categoryName,
			String article,
			String blogImage,
			String blogTitle,
			Long accountId
			) {
		if(blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setCategoryName(categoryName);
			blog.setArticle(article);
			blog.setBlogImage(blogImage);
			blog.setBlogTitle(blogTitle);
			blog.setAccountId(accountId);
			blogDao.save(blog);
			return true;
		}
	}
	
//	削除処理のチェック
//	もし、コントローラーからもたったblogIdがnullであれば
//	削除できないこと　false
//	そうでない場合
//	deleteByBlogIdを使用して商品の削除
//	true
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
