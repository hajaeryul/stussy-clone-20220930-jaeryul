package com.stussy.stussyclone20220930jaeryul.service.admin;

import com.stussy.stussyclone20220930jaeryul.dto.admin.CategoryResponseDto;
import com.stussy.stussyclone20220930jaeryul.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclone20220930jaeryul.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        /*
        2022-10-20일.. 카테고리 리스트 들고와서 뿌려주고 카테고리 id로 insult.
        디테일 정보도 insult해야함 !!
        */
        return null;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception {

    }
}
