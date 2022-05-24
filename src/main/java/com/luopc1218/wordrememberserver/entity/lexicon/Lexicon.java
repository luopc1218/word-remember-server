package com.luopc1218.wordrememberserver.entity.lexicon;

import lombok.Data;

@Data
public class Lexicon {
    private Integer id;
    private String name;
    private Integer onwerId;
    private String cover = "";
    private String description = "";

    public Lexicon(Integer id, String name, Integer onwerId, String cover, String description) {
        this.id = id;
        this.name = name;
        this.onwerId = onwerId;
        this.cover = cover;
        this.description = description;
    }
}
