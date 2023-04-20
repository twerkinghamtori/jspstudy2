package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Member;

public interface MemberMapper {
	@Insert("insert into member (id, pass, name, gender, tel, email, picture) values(#{id},#{pass},#{name},#{gender},#{tel},#{email},#{picture})")
	int insert(Member mem);

	@Select("select * from member where id=#{value}")
	Member selectOne(String id);
	
	@Update("update member set name=#{name}, gender=#{gender}, tel=#{tel}, email=#{email}, picture=#{picture} where id=#{id}")
	int update(Member mem);
	
	@Select({"<script>",
		"select * from member",
		"<if test='ids != null'>"
		+ "<foreach collection='ids' item='id' separator=',' open='where id in (' close=')'>#{id}</foreach>"
		+ "</if>",
		"</script>"		
	})
	List<Member> selectList(Map<String, Object> map);

	@Delete("delete from member where id=#{value}")
	int delete(String id);

	@Select("select id from member where email=#{param1} and tel=#{param2}")
	String idSearch(String email, String tel);
	
	@Select("select pass from member where id=#{id} and email=#{email} and tel=#{tel}")
	String pwSearch(@Param("id")String id, @Param("email")String email, @Param("tel")String tel);

	@Update("update member set pass=#{pass} where id=#{id}")
	int updatePass(@Param("id")String id, @Param("pass")String chgpass);

	

}
