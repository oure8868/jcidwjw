package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
//	保存處理和更新處理 insert和update
	Blog save(Blog blog);
	
//	select * from blog
//	用途：商品一覽表示時使用
List<Blog>findAll();

//select * from blog where blog_name = ?
//用途：商品登陸檢查時使用（沒有相同的商品名登陸過
Blog findByBlogTitle(String blogTitle);

//select * from blog where blog_id = ?
//用途：編輯畫面表示時使用
Blog findByBlogId(Long blogId);

//delete from blog where blog_id = ?
//用途：刪除時使用
void deleteByBlogId(Long blogId);

}
