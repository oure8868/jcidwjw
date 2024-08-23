package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;
@Repository

public interface BlogDao extends JpaRepository<Blog, Long> {
//	保存処理と更新取得 insertとupdate
	Blog save(Blog blog);
	
	//select * from blog where account_id = ""
	//用途：商品の一覽を表示させる時に使用
	List<Blog>findByAccountId(Long accountId);
	
	//select * from blog where blog_title = ?
	//用途：商品の登録チェックに使用（同じ商品名が登録されないようにするチェックに使用
//	Blog findByBlogTitle(String blogTitle);
	// 這裡同時使用 blogTitle 和 accountId 來查詢博客
	Blog findByBlogTitleAndAccountId(String blogTitle, Long accountId);

	
	//select * from blog where blog_id = ?
	//用途：編集画面を表示する時に使用
	Blog findByBlogId(Long blogId);
	
	//delete from blog where blog_id = ?
	//用途：削除時使用します
	@Transactional
	void deleteByBlogId(Long blogId);

}
