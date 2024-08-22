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

//	檢查博客一覽
//	如果accountId == null 作爲null返回值
//	findAll内容發送給控制臺class
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

//	檢查商品登陸處理
//	如果findByblogTitle==null
//			保存
//			true
//			如果不是
//			false
	public boolean createBlog(
			String blogTitle, 
			String categoryName, 
			String blogImage,
			String article, 
			Long accountId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
			return true;
		} else {
			return false;
		}

	}
	
	
//	顯示編輯畫面時的檢查
//	如果blogId == null ，null
//			如果不是
//			findByBlogId的信息傳遞給控制臺class
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
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
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
