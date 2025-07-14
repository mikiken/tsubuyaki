package jp.kobe_u.cs.daikibo.tsubuyaki.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Tsubuyaki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // つぶやきエンティティの識別子
    String name; // 名前
    String comment; // コメント
    LocalDateTime createdAt; // 作成日時
}
