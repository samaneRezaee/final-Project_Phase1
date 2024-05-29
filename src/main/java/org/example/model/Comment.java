package org.example.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.base.entity.BaseEntity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntity<Long> {
}
