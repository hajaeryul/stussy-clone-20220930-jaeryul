package com.stussy.stussyclone20220930jaeryul.repository.admin;

import com.stussy.stussyclone20220930jaeryul.domain.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductManagementRepository {
    public List<ProductCategory> getCategoryList() throws Exception;
}
