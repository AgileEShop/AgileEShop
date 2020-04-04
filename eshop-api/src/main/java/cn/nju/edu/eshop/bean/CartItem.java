package cn.nju.edu.eshop.bean;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CartItem  implements Serializable {
    @Id
    private String id;
    private String productId;
    private String userId;
    private BigDecimal quantity;
    // 商品价格
    private BigDecimal price;
    private String productPic;
    private String productTitle;
    private String userNickname;
    private Date createDate;
    private Date modifyDate;
    private int deleteStatus;
    private String productCatalog2Id;
    private String isChecked;
    @Transient
    private BigDecimal totalPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getProductCatalog2Id() {
        return productCatalog2Id;
    }

    public void setProductCatalog2Id(String productCatalog2Id) {
        this.productCatalog2Id = productCatalog2Id;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", productPic='" + productPic + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteStatus=" + deleteStatus +
                ", productCatalog2Id='" + productCatalog2Id + '\'' +
                ", isChecked='" + isChecked + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
