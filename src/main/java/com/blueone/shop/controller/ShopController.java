package com.blueone.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.admin.service.IAdImgService;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.customer.service.ICustomerManageService;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.shop.domain.ShopInfo;
import com.blueone.shop.service.IShopService;

@Controller
public class ShopController {
	@Autowired private ICustomerManageService customerManageService;
	@Autowired private IShopService shopService;
	@Autowired private IProductManageService productManageService;
	@Autowired private ICategoryManageService categoryManageService;
	@Autowired private IAttachFileManageService attFileManageService;
	@Autowired private IAdImgService adImgService;
	@Autowired IBoardService boardService;
	
	@RequestMapping(value ="/worklist.do", method = RequestMethod.GET)
	public String workList(@ModelAttribute("ShopInfo") ShopInfo shopInfo, BindingResult result, Model model){
		return "worklist";
	}
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String read(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo, @ModelAttribute("productInfo") ProductInfo productInfo, @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, HttpSession session,BindingResult result, Model model){
		 CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		
		if (customerSesstion == null) {
			return "user/errorPage";
		}
		
		/*// ---------------------------------------------------
		// 세션임시셋팅 - 추후 삭제해야 함
		// ---------------------------------------------------
		CustomerInfo cust = new CustomerInfo();
		cust.setCustId("id1");
		CustomerInfo res=customerManageService.getCustomerInfo2(cust);
		session.setAttribute("customerSession", res);*/
		
		// ---------------------------------------------------
		// 상품목록조회 (메인화면에 보여줄 6개의 카테고리)
		//     - 블루투스,  해드폰, 멀티미디어, 케이스, xtc, 이어폰, sale, 브랜드샵
		// ---------------------------------------------------
		String[] srchCateCdArr = {"pdSList", "hpPrdList", "mmPrdList", "csPrdList", "xtPrdList", "epPrdList"};
		//String[] srchCateArr = {"L1601", "L3862", "L3679", "L7451", "L2022", "L9540"}; //테스트서버용 설정
		String[] srchCateArr = {"L4449", "L6123", "L6787", "L7773", "L3203", "L3432"}; //배포서버용 설정
		

		ProductInfo srchPrdInfo = new ProductInfo();
		srchPrdInfo.setSrchCateArr(srchCateArr);
		srchPrdInfo.setPrdDp("y");

		
		// ---------------------------------------------------
		// 상품리스트 조회
		// ---------------------------------------------------
		List<ProductInfo> prdList = shopService.getProdListForMain(srchPrdInfo);

		// ---------------------------------------------------
		// 조회한 상품리스트를 각카테고리별로 Map에 담는다.
		// ---------------------------------------------------
		Map<String, List<ProductInfo>> map = new HashMap<String, List<ProductInfo>>();
		for (int idx=0; idx < srchCateCdArr.length; idx++ ) {
			List<ProductInfo> list = new ArrayList<ProductInfo>();
			for (ProductInfo each : prdList) {
				if (each != null && StringUtils.isEmpty(each.getCtgLargeCode())) continue;
				
				if (srchCateArr[idx].equals(each.getCtgLargeCode())) {
					list.add(each);
				}
			}
			
			map.put(srchCateCdArr[idx], list);

		}
		
		//배너클릭
		if(productInfo.getPrdCtgL()!=null && StringUtils.isEmpty(productInfo.getPrdCtgL())){
			
		}

		// ---------------------------------------------------
		// 메인화면에 보여줄 이미지 조회
		// ---------------------------------------------------
		AdImgInfo AdImgDtl = new AdImgInfo();
		AdImgDtl = shopService.getAdImg(adImgInfo);
		

		// ---------------------------------------------------
		// 화면에 전달할 Model 객체 셋팅
		// ---------------------------------------------------
		model.addAttribute("AdImgDtl", AdImgDtl);
		model.addAllAttributes(map);
		model.addAttribute("subBannerPrd",productInfo);
		
		// ----------------------------------------------------
		// 공지사항 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(8);
		boardSrchInfo.setOrdBy("n");

		List<BoardInfo> boardList = boardService.getBrdTypBoardList(boardSrchInfo);
		
		model.addAttribute("noticeList", boardList);
		
		return "shop/main";
	}
}
