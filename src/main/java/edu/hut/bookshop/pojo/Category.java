package edu.hut.bookshop.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Category {
    @NotBlank(message = "分类代码不能为空")
    @Pattern(regexp = "[a-zA-Z]+",message = "分类代码只能为字母")
    private String categoryCode;

    @NotBlank(message = "分类名称不为空")
    private String categoryName;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}