package com.example.media.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.media.model.Article;

@Mapper
public interface ArticleMapper {

	@Select("  SELECT "
			+ "  a.id, "
			+ "  title, "
			+ "  body, "
			+ "  author_id, "
			+ "  u.name AS author_name, "
			+ "  a.created_at, "
			+ "  a.updated_at "
			+ "FROM "
			+ "  articles a "
			+ "INNER JOIN users u "
			+ "  ON u.id = a.author_id "
			+ "WHERE "
			+ "  a.id = #{id} "
			+ "AND "
			+ "  a.deleted_at IS NULL")
	public Article selectById(int id);
	
	@Select("  SELECT "
			+ "  a.id, "
			+ "  title, "
			+ "  body, "
			+ "  author_id, "
			+ "  u.name AS author_name, "
			+ "  a.created_at, "
			+ "  a.updated_at "
			+ "FROM "
			+ "  articles a "
			+ "INNER JOIN users u "
			+ "  ON u.id = a.author_id "
			+ "WHERE "
			+ "  a.deleted_at IS NULL")
	public List<Article> selectAll();
	
	@Select("  SELECT "
			+ "  a.id, "
			+ "  title, "
			+ "  body, "
			+ "  author_id, "
			+ "  u.name AS author_name, "
			+ "  a.created_at, "
			+ "  a.updated_at "
			+ "FROM "
			+ "  articles a "
			+ "INNER JOIN users u "
			+ "  ON u.id = a.author_id "
			+ "WHERE "
			+ "  a.deleted_at IS NULL "
			+ "ORDER BY a.created_at DESC "
			+ "LIMIT 5 OFFSET #{offset} ")
	public List<Article> selectPerPage(int offset);
	
	@Select("SELECT COUNT(id) FROM articles WHERE deleted_at IS NULL")
	public int countAll();
	
	@Insert("  INSERT INTO articles( "
			+ "  title, "
			+ "  body, "
			+ "  author_id "
			+ ") VALUES("
			+ "  #{title},"
			+ "  #{body}, "
			+ "  #{authorId}"
			+ ")")
	public int insert(Article article);
	
	@Update("  UPDATE "
			+ "  articles "
			+ "SET "
			+ "  title = #{title}, "
			+ "  body = #{body}, "
			+ "  updated_at = now() "
			+ "WHERE "
			+ "  id = #{id} "
			+ "AND "
			+ "  author_id = #{authorId}")
	public int update(Article article);

}
