package jspMVCMisoShopping.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jspMVCMisoShopping.model.dto.GoodsDTO;

public class GoodsDAO extends DataBaseInfo{
	public String goodsAutoNum() {
		String goodsNum = null;
		con = getConnection();
		sql = " select concat('goods_', nvl(max(substr(goods_num, 7)), 100000) + 1) from goods ";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			goodsNum = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return goodsNum;
		
	}
	
	public void goodsInsert(GoodsDTO dto) {
		con = getConnection();
		sql = " insert into goods (goods_num, goods_name, goods_price, goods_contents, visit_count ";
		sql +=" ,emp_num, goods_regist, update_emp_num, goods_update_date, goods_main_image, goods_main_store_image ";
		sql +=" ,goods_detail_image, goods_detail_store_image) ";
		sql +=" values( ?, ?, ?, ?, 0, ?, sysdate, null, null, ?, ?, ?, ? ) ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsNum());
			pstmt.setString(2, dto.getGoodsName());
			pstmt.setInt(3, dto.getGoodsPrice());
			pstmt.setString(4, dto.getGoodsContent());
			pstmt.setString(5, dto.getEmpNum());
			pstmt.setString(6, dto.getGoodsMainImage());
			pstmt.setString(7, dto.getGoodsMainStoreImage());
			pstmt.setString(8, dto.getGoodsDetailImage());
			pstmt.setString(9, dto.getGoodsDetailStoreImage());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개행이(가) 삽입되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<GoodsDTO> goodsSelectAll() {
		List<GoodsDTO> list = new ArrayList<GoodsDTO>();
		con = getConnection();
		sql = " select goods_num, goods_name, goods_price, goods_contents, visit_count "
			+ " , emp_num, goods_regist, update_emp_num, goods_update_date, goods_main_image, goods_main_store_image "
			+ " , goods_detail_image, goods_detail_store_image "
			+ " from goods "
			+ " order by goods_num desc ";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setGoodsNum(rs.getString(1));
				dto.setGoodsName(rs.getString(2));
				dto.setGoodsPrice(rs.getInt(3));
				dto.setGoodsContent(rs.getString(4));
				dto.setVisitCount(5);
				dto.setEmpNum(rs.getString(6));
				dto.setGoodsRegist(rs.getDate(7));
				dto.setUpdateEmpNum(rs.getString(8));
				dto.setGoodsUpdateDate(rs.getDate(9));
				dto.setGoodsMainImage(rs.getString("goods_main_image"));
				dto.setGoodsMainStoreImage(rs.getString("goods_main_store_image"));
				dto.setGoodsDetailImage(rs.getString("goods_detail_image"));
				dto.setGoodsDetailStoreImage(rs.getString("goods_detail_store_image"));
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public GoodsDTO goodsSelectOne(String goodsNum) {
		GoodsDTO dto = null;
		con = getConnection();
		sql = " select goods_num, goods_name, goods_price, goods_contents, visit_count "
			+ " , emp_num, goods_regist, update_emp_num, goods_update_date, goods_main_image, goods_main_store_image "
			+ " , goods_detail_image, goods_detail_store_image "
			+ " from goods "
			+ " where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new GoodsDTO();
				dto.setGoodsNum(rs.getString("goods_num"));
				dto.setGoodsName(rs.getString("goods_name"));
				dto.setGoodsPrice(rs.getInt("goods_price"));
				dto.setGoodsContent(rs.getString("goods_contents"));
				dto.setVisitCount(rs.getInt(5));
				dto.setEmpNum(rs.getString(6));
				dto.setGoodsRegist(rs.getDate(7));
				dto.setUpdateEmpNum(rs.getString(8));
				dto.setGoodsUpdateDate(rs.getDate(9));
				dto.setGoodsMainImage(rs.getString("goods_main_image"));
				dto.setGoodsMainStoreImage(rs.getString("goods_main_store_image"));
				dto.setGoodsDetailImage(rs.getString("goods_detail_image"));
				dto.setGoodsDetailStoreImage(rs.getString("goods_detail_store_image"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	public void goodsUpdate(GoodsDTO dto) {
		con = getConnection();
		sql = " update goods "
			+ " set goods_name = ?, goods_price = ? "
			+ " ,goods_contents = ?, update_emp_num = ? "
			+ " ,goods_update_date = sysdate "
			+ " where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGoodsName());
			pstmt.setInt(2, dto.getGoodsPrice());
			pstmt.setString(3, dto.getGoodsContent());
			pstmt.setString(4, dto.getUpdateEmpNum());
			pstmt.setString(5, dto.getGoodsNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 수정되었습니다"); 	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public int goodsDelete(String goodsNum) {
		con = getConnection();
		sql = " delete from goods where goods_num = ? ";
		int i = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			i = pstmt.executeUpdate();
			System.out.println(i + "개 행이(가) 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return i;
	}
	
	public void visitCount(String goodsNum) {
		con = getConnection();
		sql = " update goods "
			+ " set visit_count = visit_count + 1 "
			+ " where goods_num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsNum);
			pstmt.executeUpdate();
			System.out.println("조회수가 1증가했습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}








