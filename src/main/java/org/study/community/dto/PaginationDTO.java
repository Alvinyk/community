package org.study.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkai
 * @description TODO
 * @date 2019/7/8 9:28
 **/
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private List<Integer> pages = new ArrayList<>();
    private Integer currentPage;
    private Integer totalPage;
    private boolean hasPrevious;
    private boolean hasFirst;
    private boolean hasNext;
    private boolean hasEnd;

    public void setPaginationDTO(Integer page, Integer size, int totalCount) {
        totalPage = totalCount / size;
        if (totalCount % size != 0) {
            totalPage += 1;
        }

        //将输入的页码调整到数据允许的页码内
        if (page < 1) {
            currentPage = 1;
        } else if (page > totalPage){
            currentPage = totalPage;
        }else {
            currentPage = page;
        }

        for (int i = currentPage - 3; i <= currentPage + 3; i++) {
            if (i > 0 && i <= totalPage) {
                pages.add(i);
            }
        }

        //是否需要第一页和最后一页调整按钮
        if (pages.contains(1)) {
            hasFirst = false;
        } else {
            hasFirst = true;
        }

        if (pages.contains(totalPage)) {
            hasEnd = false;
        } else {
            hasEnd = true;
        }

        //是否需要上一页，下一页跳转按钮
        if (currentPage == 1) {
            hasPrevious = false;
        } else {
            hasPrevious = true;
        }

        if (currentPage == totalPage) {
            hasNext = false;
        } else {
            hasNext = true;
        }
    }
}
