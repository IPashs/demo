package ru.task.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
public class SearchPage<T> {
    @JsonProperty("data")
    private final List<T> data;
    @JsonProperty("paging")
    private final Paging paging;

    @JsonCreator
    public SearchPage(@JsonProperty("data") final List<T> data, @JsonProperty("paging") final Paging paging) {
        this.data = data;
        this.paging = paging;
    }

    public static <T> SearchPage<T> fromPage(final Page<T> page) {
        List<T> data = page.getContent();
        Paging paging = new Paging(page.getPageable(), page.getNumberOfElements(), page.getTotalElements(), page.getTotalPages());
        return new SearchPage(data, paging);
    }

    public static class Paging {
        @JsonProperty("offset")
        private final Long offset;
        @JsonProperty("limit")
        private final Integer limit;
        @JsonProperty("count")
        private final Integer count;
        @JsonProperty("total")
        private final Long total;
        @JsonProperty("totalPages")
        private final Integer totalPages;

        public Paging(final Pageable pageable, final Integer count, final Long total, final Integer totalPages) {
            this.offset = pageable.isPaged() ? pageable.getOffset() + 1L : 0L;
            this.limit = pageable.isPaged() ? pageable.getPageSize() : 0;
            this.count = count;
            this.total = total;
            this.totalPages = totalPages;
        }
    }
}
