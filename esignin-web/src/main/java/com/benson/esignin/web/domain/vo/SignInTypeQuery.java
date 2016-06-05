package com.benson.esignin.web.domain.vo;

/**
 * 签到类型查询类
 *
 * @author: Benson Xu
 * @date: 2016年06月04日 16:34
 */
public class SignInTypeQuery extends BasePageQuery {

    private String typeName; // 类型名称

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
