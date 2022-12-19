package kr.co.itwill.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartCont {
	
	public CartCont() {
		System.out.println("------- CartCont() 객체 생성됨 ");
	}// CartCont()

	
	@Autowired
	CartDAO cartDao;
	
	
//  [장바구니] 시작 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  //
	
	@RequestMapping("/cart")
	public ModelAndView cart(HttpServletRequest req, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		String m_id = (String) session.getAttribute("s_m_id");
		
		//System.out.println(cartDao.deliveryFee(m_id));
		
        mav.setViewName("/cart/cartList");
		
        //System.out.println(cartDao.mycartList(m_id));
        //cartDao.mycartList(m_id);
        mav.addObject("mycart", cartDao.mycartList(m_id));
        mav.addObject("total", cartDao.deliveryFee(m_id));
        return mav;
	}// concertList() end
	
	
	
}//class end
