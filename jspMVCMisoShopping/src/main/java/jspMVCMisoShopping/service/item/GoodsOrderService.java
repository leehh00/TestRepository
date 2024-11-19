package jspMVCMisoShopping.service.item;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import jspMVCMisoShopping.model.dao.ItemDAO;
import jspMVCMisoShopping.model.dto.PurchaseDTO;
import jspMVCMisoShopping.service.MemberAuthService;

public class GoodsOrderService extends MemberAuthService{
	public GoodsOrderService(HttpServletRequest request) {
		super(request);
	}
	
	public String execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String purchaseName = request.getParameter("purchaseName");
		String totalPaymentPrice = request.getParameter("totalPaymentPrice");
		String deliveryName = request.getParameter("deliveryName");
		String deliveryAddr = request.getParameter("deliveryAddr");
		String deliveryAddrDetail = request.getParameter("deliveryAddrDetail");
		String deliveryPost = request.getParameter("deliveryPost");
		String deliveryPhone = request.getParameter("deliveryPhone");
		String message = request.getParameter("message");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String purchaseNum = sdf.format(new Date());
		
		PurchaseDTO dto = new PurchaseDTO();
		dto.setDeliveryAddr(deliveryAddr);
		dto.setDeliveryAddrDetail(deliveryAddrDetail);
		dto.setDeliveryName(deliveryName);
		dto.setDeliveryPhone(deliveryPhone);
		dto.setDeliveryPost(deliveryPost);
		dto.setMemberNum(memberNum);
		dto.setMessage(message);
		dto.setPurchasePrice(Long.parseLong(totalPaymentPrice));
		dto.setPurchaseName(purchaseName);
		dto.setPurchaseNum(purchaseNum);
		
		ItemDAO dao = new ItemDAO();
		// 구매정보 입력
		dao.purchaseInsert(dto);
		String goodsNums[] = request.getParameter("goodsNums").split("-");
		for(String goodsNum : goodsNums) {
			dao.purchaseListInsert(purchaseNum, goodsNum, memberNum);
			dao.itemDelete(goodsNum, memberNum);
		}
		return purchaseNum;
		
		//날짜를 구매번호로 생성
		
		
	}
}
