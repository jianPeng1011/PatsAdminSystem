package com.example.pethospitalmanagement.util;

import com.github.pagehelper.PageInfo;

public class PageUtils {

    /**
     * 将分页信息
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {

        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageRequest.getPageNum());
        pageResult.setPageSize(pageRequest.getPageSize());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setContent(pageInfo.getList());

        return pageResult;
    }
}
