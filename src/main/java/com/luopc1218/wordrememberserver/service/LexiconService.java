package com.luopc1218.wordrememberserver.service;

import com.luopc1218.wordrememberserver.entity.lexicon.Lexicon;
import com.luopc1218.wordrememberserver.entity.request.PaginationData;
import com.luopc1218.wordrememberserver.entity.request.Pagination;
import com.luopc1218.wordrememberserver.repository.LexiconMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LexiconService {

    @Autowired
    LexiconMapper lexiconMapper;

    public PaginationData<Lexicon> getUserLexicons(Integer userId, Pagination pagination) {
        return new PaginationData<Lexicon>(lexiconMapper.getUserLexicons(userId, pagination),
                lexiconMapper.getUserLexiconsCount(userId));
    }

    public void createUserLexicon(Integer userId, String name) {
        lexiconMapper.createUserLexicon(userId, name);
    }

}
