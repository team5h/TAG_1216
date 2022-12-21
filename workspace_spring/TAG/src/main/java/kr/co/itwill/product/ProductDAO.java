package kr.co.itwill.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itwill.QnA.QnADTO;
import kr.co.itwill.concert.ConcertDTO;
import kr.co.itwill.coupon.CouponDTO;
import kr.co.itwill.memberGeneral.MemberGeneralDTO;
import kr.co.itwill.orderDetail.OrderDetailDTO;
import kr.co.itwill.productOrder.ProdcutOrderDTO;


@Repository
public class ProductDAO {
	
	public ProductDAO() {
		System.out.println("------- ProductDAO() 객체 생성됨 ");
	}//ProductDAO() end
	
	@Autowired
	SqlSession sqlSession;
	
	
//  ---------------------------------------------------- [상품목록 - 전체]
	public List<Map<String, Object>> list(int start, int end) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		return sqlSession.selectList("product.list", map);
	}//list() end	
	
//  ---------------------------------------------------- [상품목록 - 페이징 전체 글개수]	
	public int total() {
		return sqlSession.selectOne("product.total");
	}// list2() end

	
	
	
//  ---------------------------------------------------- [상품목록 - 페이징 카테고리별 글개수]	
	public int categoryTotal(String category) {
		return sqlSession.selectOne("product.categoryTotal", category);
	}// categoryTotal() end
	
//  ---------------------------------------------------- [상품목록 - 페이징 리스트]	
	public List<Map<String, Object>> list2(Integer start, Integer end, String category) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("start", start.toString());
		map.put("end", end.toString());
		map.put("category", category);
		
		return sqlSession.selectList("product.list2", map);
	}// list2() end
	

	
//  ---------------------------------------------------- [상품목록 - 콘서트 리스트]	
	public List<ConcertDTO> concert() {
		return sqlSession.selectList("product.concert");
	}// concert() end	
	
//  ---------------------------------------------------- [상품목록 - 콘서트 개수]	
	public int concertTotal(String c_no) {
		return sqlSession.selectOne("product.concertTotal", c_no);
	}// categoryTotal() end

//  ---------------------------------------------------- [상품목록 - 콘서트 리스트 + 페이징]
	public List<Map<String, Object>> concertList(Integer start, Integer end, String c_no) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("start", start.toString());
		map.put("end", end.toString());
		map.put("c_no", c_no.toString());
		
		return sqlSession.selectList("product.concertList", map);
	}// list2() end

	

	
//  ---------------------------------------------------- [상품검색]	
	public List<ProductDTO> search(String pro_name) {
		return sqlSession.selectList("product.search", "%" + pro_name.toUpperCase() + "%");
	}//search() end
	

	
//  ---------------------------------------------------- [상품목록 - 카테고리 전체]	
	public List<ProductDTO> categoryAll() {
		return sqlSession.selectList("product.categoryAll");
	}//search() end
	
	
	public Map<String, Object> proDetail(int pro_no){
		return sqlSession.selectOne("product.proDetail",pro_no);
	}
	
	public int pro_qnacnt (int pro_no) {
		return sqlSession.selectOne("product.pro_qnacnt",pro_no);
	}
	
	public int qnainsert (QnADTO dto) {
		return sqlSession.insert("product.qnainsert", dto);
	}
	
	public QnADTO qnadetail (int q_no, int passwd) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("q_no", q_no);
		map.put("passwd", passwd);
		
		return sqlSession.selectOne("product.qnadetail",map);
	}
	
	public List<QnADTO> qnalist (int pro_no) {
		return sqlSession.selectList("product.qnalist",pro_no);
	}
	
	public int addcart (int cnt, int pro_no, String m_id) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("cnt", cnt);
		map.put("pro_no", pro_no);
		map.put("m_id", m_id);
		
		return sqlSession.insert("product.addcart",map); 
	}
	
	public int like (int pro_no, String m_id) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("pro_no", pro_no);
		map.put("m_id", m_id);
		
		return sqlSession.insert("product.like",map);
	}//end
	
	public int unlike (int pro_no, String m_id) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("pro_no", pro_no);
		map.put("m_id", m_id);
		
		return sqlSession.delete("product.unlike",map);
	}//end
	
	public int likechk (String m_id, int pro_no) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("pro_no", pro_no);
		map.put("m_id", m_id);
		
		return sqlSession.selectOne("product.likechk", map); 
	}// end
	
	public ProductDTO order_proinfo (int pro_no) {
		return sqlSession.selectOne("product.order_proinfo",pro_no);
	}
	
	public MemberGeneralDTO order_Minfo (String m_id) {
		return sqlSession.selectOne("product.order_Minfo",m_id);
	}
	
	public List<CouponDTO> mem_coupon (int pro_no, String m_id) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("pro_no", pro_no);
		map.put("m_id", m_id);
		
		return sqlSession.selectList("product.mem_coupon",map);
	}//end
	
	public int mem_couponCNT(int pro_no, String m_id) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("pro_no", pro_no);
		map.put("m_id", m_id);
		
		return sqlSession.selectOne("product.mem_couponCNT",map);
	}
	
	public int mem_couponTOTALCNT (String m_id) {
		return sqlSession.selectOne("product.mem_couponTOTALCNT",m_id);
	}
	
	public int productorderIns (Map<String, Object> map) {
		return sqlSession.insert("product.productorderIns", map);
	}
	
	public int orderdetailIns (OrderDetailDTO dto) {
		return sqlSession.insert("product.orderdetailIns", dto);
	}
	
	
}//class end
