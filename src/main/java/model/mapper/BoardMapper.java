package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Board;

public interface BoardMapper {
	
	@Select("select ifnull(max(num),0) maxnum from board")
	int maxnum();

	@Insert("insert into board (num, writer, pass, title, content, file1, boardid, regdate, readcnt, grp, grplevel, grpstep) "
			+ " values (#{num},#{writer},#{pass},#{title},#{content},#{file1},#{boardid},now(),0,#{grp},#{grplevel},#{grpstep})")
	int insert(Board board);

	@Select({ "<script>",
		"select count(*) from board where boardid=#{boardid}",
//		"<if test='cols != null'> and ",
//		"<foreach collection='cols' item='col' separator='or' open=' (' close=')'> #{col} like '%${find}%' </foreach>",
//		"</if>",
		
		"<if test='column != null'>",
		"<if test='cols1!=null'> and (${cols1} like '%${find}%' </if>",
		"<if test='cols2==null'> ) </if>",
		"<if test='cols2!=null'> or ${cols2} like '%${find}%' </if>",
		"<if test='cols2 != null and cols3==null'> ) </if>",
		"<if test='cols3!=null'> or ${cols3} like '%${find}%' ) </if>",
		"</if>",
		"</script>"
	})
	int boardCount(Map<String, Object> map);
	
	@Select({"<script>",
		"SELECT * ,(SELECT COUNT(*) FROM comment c WHERE c.num=b.num) commcnt FROM board b WHERE boardid=#{boardid} ",
//		"<if test='cols != null'> and ",
//		"<foreach collection='cols' item='col' separator='or' open=' (' close=')'>#{col}</foreach>",
//		"</if>",
		
		"<if test='column != null'>",
		"<if test='cols1!=null'> and (${cols1} like #{find} </if>",
		"<if test='cols2==null'> ) </if>",
		"<if test='cols2!=null'> or ${cols2} like '%${find}%' </if>",
		"<if test='cols2 != null and cols3==null'> ) </if>",
		"<if test='cols3!=null'> or ${cols3} like '%${find}%' ) </if>",
		"</if>",
		
		" ORDER BY grp DESC, grpstep ASC limit #{start},#{limit}",	
		
		"</script>"		
	})
	List<Board> selectList(Map<String, Object> map);

	@Select("select * from board where num=#{value}")
	Board selectOne(int num);

	@Update("update board set readcnt=readcnt+1 where num=#{value}")
	void readcntAdd(int num);

	@Update("update board set grpstep=grpstep+1 where grp=#{param1} and grpstep>#{param2}")
	void grpStepAdd(int grp, int grpstep);

	@Update("update board set writer=#{writer}, title=#{title}, content=#{content}, file1=#{file1} where num=#{num}")
	int update(Board b);

	@Delete("delete from board where num=#{value}")
	int delete(int num);

	@Select("SELECT writer, COUNT(*) cnt FROM board GROUP BY writer having count(*) > 1 ORDER BY cnt desc")
	List<Map<String, Object>> graph();

	@Select("SELECT date_format(regdate,'%Y-%m-%d') d, COUNT(*) cnt FROM board GROUP BY d ORDER BY d DESC LIMIT 7")
	List<Map<String, Object>> graph2();
	
	
	

}
