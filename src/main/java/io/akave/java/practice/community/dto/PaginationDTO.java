package io.akave.java.practice.community.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author akave
 */
@Data
public class PaginationDTO {
    private Integer index;
    private Boolean showFirstPage;
    private Boolean showPrevious;
    private Boolean showNext;
    private Boolean showLastPage;
    private List<Integer> pageList;
    private Integer total;

    public PaginationDTO(Integer total, Integer index) {
        this.index = index;
        this.total = total;
        pageList = new LinkedList<>();
        //显示当前页的前三页码和后三页码，如果页码合法的话
        if (index <= total && index > 0) {
            pageList.add(index);

            int tempPagiration = 0;
            for (int i = 1; i < 4; i++) {
                tempPagiration = index - i;
                if (tempPagiration > 0) {
                    pageList.add(0,tempPagiration);
                }
                tempPagiration = index + i;
                if (tempPagiration <= total) {
                    pageList.add(tempPagiration);
                }
            }
        }

        if (total > 0) {
            //是否显示到第一页的按钮
            showFirstPage = !(pageList.contains(1));

            //是否显示到最后一页的按钮
            showLastPage = !pageList.contains(total);

            //是否显示前一页按钮
            showPrevious = !index.equals(1);

            //是否显示下一页按钮
            showNext = !index.equals(total);
        }
    }
}
