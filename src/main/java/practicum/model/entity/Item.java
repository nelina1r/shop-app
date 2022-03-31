package practicum.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Waybill waybill;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "shop_name", nullable = false)
    private String shopName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString(){
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", Status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price &&
                quantity == item.quantity &&
                Objects.equals(id, item.id) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, price, quantity);
    }
}
