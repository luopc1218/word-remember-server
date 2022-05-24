package com.luopc1218.wordrememberserver.entity.lexicon;

import lombok.Data;

@Data
public class Word {
    private Integer id;
    private Integer lexiconId;
    private String original;
    private String phonetic;
    private String meaning;

    public Word(Integer id, Integer lexiconId, String original, String phonetic, String meaning) {
        this.id = id;
        this.lexiconId = lexiconId;
        this.original = original;
        this.phonetic = phonetic;
        this.meaning = meaning;
    }

}
