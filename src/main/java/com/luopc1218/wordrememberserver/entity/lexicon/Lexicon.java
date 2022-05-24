package com.luopc1218.wordrememberserver.entity.lexicon;

import lombok.Data;

@Data
public class Lexicon {
    private Integer id;
    private String name;
    private Integer onwerId;

    public Lexicon(Integer id, String name, Integer onwerId) {
        this.id = id;
        this.name = name;
        this.onwerId = onwerId;
    }

}
