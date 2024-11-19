package jspMVCMisoShopping.service.goods;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspMVCMisoShopping.model.dao.EmployeeDAO;
import jspMVCMisoShopping.model.dao.GoodsDAO;
import jspMVCMisoShopping.model.dto.AuthInfoDTO;
import jspMVCMisoShopping.model.dto.GoodsDTO;

public class GoodsWriteService {
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		int fileSize = 1024 * 1024 * 100;
		String realPath = request.getServletContext().getRealPath("/goods/upload");
		System.out.println(realPath);

		try {
			MultipartRequest multi = new MultipartRequest(request, realPath, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			
			String storeMainImage = multi.getFilesystemName("mainImage");
			String mainImage = multi.getOriginalFileName("mainImage");

			String storeImage1 = multi.getFilesystemName("image1");
			String image1 = multi.getOriginalFileName("image1");			
			String storeImage2 = multi.getFilesystemName("image2");
			String image2 = multi.getOriginalFileName("image2");			
			String storeImage3 = multi.getFilesystemName("image3");
			String image3 = multi.getOriginalFileName("image3");
			
			String goodsDetailStoreImage = storeImage1 + "`" + storeImage2 + "`" + storeImage3;
			String goodsDetailImage = image1 + "`" + image2 + "`" + image3;
			
			GoodsDTO dto = new GoodsDTO();
			dto.setGoodsContent(multi.getParameter("goodsContents"));
			dto.setGoodsName(multi.getParameter("goodsName")); 
			dto.setGoodsNum(multi.getParameter("goodsNum"));
			dto.setGoodsPrice(Integer.parseInt(multi.getParameter("goodsPrice")));

			HttpSession session = request.getSession();
			AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
			EmployeeDAO dao = new EmployeeDAO();
			String empNum = dao.empNumSelect(auth.getUserId());
			dto.setEmpNum(empNum);
			
			dto.setGoodsMainImage(mainImage);
			dto.setGoodsMainStoreImage(storeMainImage);
			dto.setGoodsDetailImage(goodsDetailImage);
			dto.setGoodsDetailStoreImage(goodsDetailStoreImage);

			GoodsDAO dao1 = new GoodsDAO();
			dao1.goodsInsert(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
