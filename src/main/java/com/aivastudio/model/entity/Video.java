package com.aivastudio.model.entity;

import java.time.LocalDateTime;

import com.aivastudio.model.base.BaseEntity;
import com.aivastudio.model.enums.EstadoVideo;
import com.aivastudio.model.embeddable.ConfiguracionVideo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video extends BaseEntity {

   private String title;
   private String description;
   private double duracionMinutos;

   public Video() {
   }

   public Video(Long id, String title, String description, double duracionMinutos) {
       super(id);
       this.title = title;
       this.description = description;
       this.duracionMinutos = duracionMinutos;
   }

   public String getTitle() {
       return title;
   }

    public void setTitle(String title) {
         this.title = title;
    }

    public String getDescription() {
         return description;
    }

    public void setDescription(String description) {
         this.description = description;
    }

    public double getDuracionMinutos() {
         return duracionMinutos;
    }

    public void setDuracionMinutos(double duracionMinutos) {
         this.duracionMinutos = duracionMinutos;
    }
}