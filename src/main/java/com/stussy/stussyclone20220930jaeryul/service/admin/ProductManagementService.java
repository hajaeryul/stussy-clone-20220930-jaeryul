package com.stussy.stussyclone20220930jaeryul.service.admin;

import com.stussy.stussyclone20220930jaeryul.dto.admin.CategoryResponseDto;
import com.stussy.stussyclone20220930jaeryul.dto.admin.ProductMstOptionRespDto;
import com.stussy.stussyclone20220930jaeryul.dto.admin.ProductRegisterReqDto;

import java.util.List;

public interface ProductManagementService {

    public List<CategoryResponseDto> getCategoryList() throws Exception;
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;

    public List<ProductMstOptionRespDto> getProductMstList() throws Exception;
}