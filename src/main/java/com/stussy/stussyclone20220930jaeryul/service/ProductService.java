package com.stussy.stussyclone20220930jaeryul.service;

import com.stussy.stussyclone20220930jaeryul.dto.CheckoutRespDto;
import com.stussy.stussyclone20220930jaeryul.dto.CollectionListRespDto;
import com.stussy.stussyclone20220930jaeryul.dto.ProductRespDto;

import java.util.List;

public interface ProductService {
    public List<CollectionListRespDto> getProductList(String category, int page) throws Exception;
    public ProductRespDto getProduct(int pdtId) throws Exception;
    public CheckoutRespDto getCheckoutProduct(int pdtDtlId) throws Exception;
}
