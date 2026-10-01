package com.aivastudio.model.entity;

import com.aivastudio.model.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "generaciones_video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneracionVideo extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String modeloIA;

    @Column(length = 100)
    private String duracionGeneracion;

    @OneToOne
    @JoinColumn(name = "video_id", nullable = false, unique = true)
    private Video video;

    @OneToOne
    @JoinColumn(name = "prompt_id", nullable = false, unique = true)
    private Prompt prompt;
}